package com.yizhi.login.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yizhi.common.config.aliyun.AliyunSMSConfig;
import com.yizhi.common.model.enums.ResultCodeEnum;
import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.login.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private AliyunSMSConfig aliyunSMSConfig;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseResult sendCheckCode(String phone) {
        String redisKey = "CHECK_CODE_" + phone;
        //判断该手机号发送过来的验证码是否失效
        if (this.redisTemplate.hasKey(redisKey)) {
            return ResponseResult.build(null, ResultCodeEnum.SEND_CHECKCODE_ERROR_2);
        }
        // String code = this.sendSms(phone);
        String code = "123456";
        if (StringUtils.isEmpty(code)) {
            return ResponseResult.build(null, ResultCodeEnum.SEND_CHECKCODE_ERROR_1);
        }
        //短信发送成功后存入redis保存时间为5min
        this.redisTemplate.opsForValue().set(redisKey, code, Duration.ofMinutes(5));
        return ResponseResult.ok();
    }

    // 云之讯
    private String sendSms(String mobile) {
        String url = "https://open.ucpaas.com/ol/sms/sendsms";
        Map<String, Object> params = new HashMap<>();
        //根据需要进行修改
        params.put("sid", "1bafadef5706824e2715386ab3ed7a6d");
        params.put("token", "5c1128d340108f9826c0015e9e112893");
        params.put("appid", "b3b0dec8f97244f28293eaa7349f6b08");
        params.put("templateid", "487656");
        params.put("mobile", mobile);
        // 生成6位数验证
        params.put("param", RandomUtils.nextInt(100000, 999999));
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(url, params, String.class);
        String body = responseEntity.getBody();
        try {
            JsonNode jsonNode = MAPPER.readTree(body);
            //000000 表示发送成功
            if (StringUtils.equals(jsonNode.get("code").textValue(), "000000")) {
                return String.valueOf(params.get("param"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
//    // 阿里云SMS
//    private String sendSms(String mobile) {
//        DefaultProfile profile = DefaultProfile.getProfile(this.aliyunSMSConfig.getRegionId(),
//                this.aliyunSMSConfig.getAccessKeyId(), this.aliyunSMSConfig.getAccessKeySecret());
//        IAcsClient client = new DefaultAcsClient(profile);
//        String code = RandomUtils.nextInt(100000, 999999) + "";
//        CommonRequest request = new CommonRequest();
//        request.setSysMethod(MethodType.POST);
//        request.setSysDomain(this.aliyunSMSConfig.getDomain());
//        request.setSysVersion("2017-05-25");
//        request.setSysAction("SendSms");
//        request.putQueryParameter("RegionId", this.aliyunSMSConfig.getRegionId());
//        request.putQueryParameter("PhoneNumbers", mobile); //目标手机号
//        request.putQueryParameter("SignName", this.aliyunSMSConfig.getSignName()); //签名名称
//        request.putQueryParameter("TemplateCode", this.aliyunSMSConfig.getTemplateCode()); //短信模板code
//        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");//模板中变量替换
//        try {
//            CommonResponse response = client.getCommonResponse(request);
//            String data = response.getData();
//            if (StringUtils.contains(data, "\"Message\":\"OK\"")) {
//                return code;
//            }
//            log.info("发送短信验证码失败~ data = " + data);
//        } catch (Exception e) {
//            log.error("发送短信验证码失败~ mobile = " + mobile, e);
//        }
//        return null;
//    }
}
