package com.yizhi.user.controller.v1;

import com.yizhi.common.model.vo.ErrorResult;
import com.yizhi.user.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1/user")
@Slf4j
public class SmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping("login")
    public ResponseEntity<ErrorResult> sendCheckCode(@RequestBody Map<String,String> param){
        ErrorResult errorResult = null;
        //接收前端传过来的手机号
        String phone = param.get("phone");
        try {
            //调用smsService中的方法得到错误的结果类型
            errorResult = this.smsService.sendCheckCode(phone);
            //如果为空则发送验证码成功
            if(null==errorResult){
                return ResponseEntity.ok(null);
            }
        } catch (Exception e) {
            log.error("发送短信验证码失败~ phone = " + phone, e);
            errorResult=ErrorResult.builder().errCode("000002").errMessage("短信验证码发送失败!").build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResult);
    }

}
