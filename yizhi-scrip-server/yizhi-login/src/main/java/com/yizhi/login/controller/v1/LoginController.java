package com.yizhi.login.controller.v1;

import com.yizhi.common.client.ServerFeignClient;
import com.yizhi.common.model.pojo.mysql.ApUser;
import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.login.service.LoginService;
import com.yizhi.login.service.impl.SmsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("api/v1/login")
@Slf4j
public class LoginController {

    //注入service
    @Autowired
    private LoginService loginService;

    @Autowired
    private SmsServiceImpl smsService;

    @Autowired
    private ServerFeignClient serverFeignClient;

    @PostMapping("send")
    public ResponseResult sendCheckCode(@RequestBody Map<String, String> param) {
        return smsService.sendCheckCode(param.get("phone"));
    }

    @PostMapping("login")
    public ResponseResult login(@RequestBody Map<String, String> param) {
        return loginService.login(param.get("phone"), param.get("verificationCode"));
    }

    @GetMapping("{token}")
    public ApUser queryUserByToken(@PathVariable("token") String token) {
        return loginService.queryUserByToken(token);
    }

    @PostMapping("saveUserInfo")
    public ResponseResult saveUserInfo(@RequestBody Map<String, String> param) {
        return this.serverFeignClient.saveUserInfo(param);
    }

    @PostMapping("saveUserLogo")
    public ResponseResult saveUserLogo(@RequestParam MultipartFile file) {
        return this.serverFeignClient.saveUserLogo(file);
    }
}
