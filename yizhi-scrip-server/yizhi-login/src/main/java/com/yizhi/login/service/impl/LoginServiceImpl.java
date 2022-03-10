package com.yizhi.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yizhi.common.client.ServerFeignClient;
import com.yizhi.common.model.mapper.ApUserMapper;
import com.yizhi.common.model.pojo.mysql.ApUser;
import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.login.service.LoginService;
import com.yizhi.login.service.SmsService;
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
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    private SmsService smsService;
    @Override
    public ResponseResult login(String mobile, String code) {
        String redisKey = "CHECK_CODE_" + mobile;
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
        lambdaQueryWrapper.eq(ApUser::getMobile, mobile);
        ApUser user = this.userMapper.selectOne(lambdaQueryWrapper);
        if (null == user) {
            //为空说明是新用户则需要注册该用户
            user = new ApUser();
            user.setMobile(mobile);
            //对用户密码进行md5加密
            user.setPassword(DigestUtils.md5Hex("123456"));
            //是新用户
            isNew = true;
            //注册添加新用户到数据库
            this.userMapper.insert(user);
        }
        //生成token
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("id", user.getId());
        String token = Jwts.builder()
                .setClaims(claims) //payload，存放数据的位置，不能放置敏感数据，如：密码等
                .signWith(SignatureAlgorithm.HS256, secret) //设置加密方法和加密盐
                .setExpiration(new DateTime().plusHours(1000).toDate()) //设置过期时间，1000小时后过期
                .compact();
        if (isNew) {
            //注册环信用户
            this.serverFeignClient.register(Long.valueOf(user.getId()), token);
        }
        //将token和是否为新用户的结果传给controller
        Map<String, Object> map = new HashMap<>();
        map.put("isNew", isNew);
        map.put("token", token);
        try {
            //发送用户登录成功消息
            Map<String, Object> msg = new HashMap<>();
            msg.put("id", user.getId());
            msg.put("date", System.currentTimeMillis());
            this.rocketMQTemplate.convertAndSend("login", msg);
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
            String redisKey = "USER_MOBILE_" + user.getId();
            //判断redis中是否有
            if (redisTemplate.hasKey(redisKey)) {
                user.setMobile(this.redisTemplate.opsForValue().get(redisKey));
            } else {
                ApUser u = userMapper.selectById(user.getId());
                //不是每一次查询都要从mysql中获取,第一次查询出来后存入redis中
                String exp = body.get("exp").toString();
                Long expLong = Long.valueOf(exp);
                System.out.println("类" + this.getClass().getName() + "中" + Thread.currentThread()
                        .getStackTrace()[1].getMethodName() + "方法:" + expLong + exp);
                this.redisTemplate.opsForValue()
                        .set(redisKey, u.getMobile(), Duration.ofMillis(expLong - System.currentTimeMillis()));
                user.setMobile(u.getMobile());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public ResponseResult saveUserInfo(Map<String, String> param, String token) {
        return this.serverFeignClient.saveUserInfo(param, token);
    }
    @Override
    public ResponseResult saveUserLogo(MultipartFile file, String token) {
        return this.serverFeignClient.saveUserLogo(file, token);
    }
    @Override
    public ResponseResult sendToOldMobile(String token) {
        String mobile = this.queryUserByToken(token).getMobile();
        return this.smsService.sendCheckCode(mobile);
    }
    @Override
    public ResponseResult updateNewMobile(String token, String mobile) {
        ApUser user = this.queryUserByToken(token);
        if (null == user) {
            return ResponseResult.fail();
        }
        QueryWrapper<ApUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        ApUser oldUser = this.userMapper.selectOne(queryWrapper);
        if (null != oldUser) {
            // 该手机号已经注册
            return ResponseResult.fail();
        }
        user.setMobile(mobile);
        return (this.userMapper.updateById(user) > 0) ? ResponseResult.ok() : ResponseResult.fail();
    }
}
