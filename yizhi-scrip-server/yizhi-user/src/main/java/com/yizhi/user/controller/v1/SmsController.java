package com.yizhi.user.controller.v1;

import com.yizhi.common.model.vo.Result;
import com.yizhi.user.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Result sendCheckCode(@RequestBody Map<String, String> param) {
        //接收前端传过来的手机号
        String phone = param.get("phone");
        //调用smsService中的方法得到错误的结果类型
        return smsService.sendCheckCode(phone);
    }

}
