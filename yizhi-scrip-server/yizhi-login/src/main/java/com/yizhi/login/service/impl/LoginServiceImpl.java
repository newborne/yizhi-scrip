package com.yizhi.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yizhi.common.client.ServerFeignClient;
import com.yizhi.common.model.mapper.ApUserMapper;
import com.yizhi.common.model.pojo.mysql.ApUser;
import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.login.service.LoginService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    private ApUserMapper userMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private ServerFeignClient serverFeignClient;

    @Override
    public ResponseResult login(String phone, String code) {
        String redisKey = "CHECK_CODE_" + phone;
        boolean isNew = false;
        String redisData = this.redisTemplate.opsForValue().get(redisKey);
        //判断输入的验证码是否正确
        if (!StringUtils.equals(code, redisData)) {
            return null;
        }
        //判断验证码是否过期
        if (StringUtils.isEmpty(redisData)) {
            return null;
        }
        //验证成功删除验证码
        this.redisTemplate.delete(redisKey);
        //判断是否是新用户
        LambdaQueryWrapper<ApUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ApUser::getMobile, phone);
        ApUser user = this.userMapper.selectOne(lambdaQueryWrapper);
        if (null == user) {
            //为空说明是新用户则需要注册该用户
            user = new ApUser();
            user.setMobile(phone);
            //对用户密码进行md5加密
            user.setPassword(DigestUtils.md5Hex("123456"));
            //是新用户
            isNew = true;
            //注册添加新用户到数据库
            this.userMapper.insert(user);
            //注册环信用户
            this.serverFeignClient.register(Long.valueOf(user.getId()));
        }
        //生成token
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("id", user.getId());
        // 生成token
        String token = Jwts.builder()
                .setClaims(claims) //payload，存放数据的位置，不能放置敏感数据，如：密码等
                .signWith(SignatureAlgorithm.HS256, secret) //设置加密方法和加密盐
                .setExpiration(new DateTime().plusHours(12).toDate()) //设置过期时间，12小时后过期
                .compact();
        //将token和是否为新用户的结果传给controller
        Map<String, Object> map = new HashMap<>();
        map.put("isNew", isNew);
        map.put("token", token);
        try {
            //发送用户登录成功消息
            Map<String, Object> msg = new HashMap<>();
            msg.put("id", user.getId());
            msg.put("date", System.currentTimeMillis());
            this.rocketMQTemplate.convertAndSend("yizhi-user-login", msg);
        } catch (Exception e) {
            log.error("发送消息失败", e);
        }
        return ResponseResult.ok(map);
    }

    @Override
    public ApUser queryUserByToken(String token) {
        ApUser user = null;
        try {
            // 通过token解析数据
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            Long id = Long.valueOf(body.get("id").toString());
            user = new ApUser();
            user.setId(Math.toIntExact(id));
            //先从redis中取出手机号,如果没有的话再从mysql中获取
            String redisKey = "YIZHI_USER_MOBILE_" + user.getId();
            //判断redis中是否有
            if (redisTemplate.hasKey(redisKey)) {
                user.setMobile(this.redisTemplate.opsForValue().get(redisKey));
            } else {
                ApUser u = userMapper.selectById(user.getId());
                //不是每一次查询都要从mysql中获取,第一次查询出来后存入redis中
                String exp = body.get("exp").toString();
                Long expLong = Long.valueOf(exp) * 1000;
                this.redisTemplate.opsForValue().set(redisKey, u.getMobile(), Duration.ofMillis(expLong - System.currentTimeMillis()));
                user.setMobile(u.getMobile());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
