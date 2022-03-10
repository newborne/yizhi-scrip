package com.yizhi.login.controller.v1;

import com.yizhi.common.model.pojo.mysql.ApUser;
import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.login.service.LoginService;
import com.yizhi.login.service.impl.SmsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("send")
    public ResponseResult sendCheckCode(@RequestBody Map<String, String> param) {
        return smsService.sendCheckCode(param.get("mobile"));
    }
    @PostMapping("login")
    public ResponseResult login(@RequestBody Map<String, String> param) {
        return loginService.login(param.get("mobile"), param.get("verificationCode"));
    }
    @GetMapping("{token}")
    public ApUser queryUserByToken(@PathVariable("token") String token) {
        return loginService.queryUserByToken(token);
    }
    @PostMapping("saveUserInfo")
    public ResponseResult saveUserInfo(@RequestBody Map<String, String> param,
                                       @RequestHeader("Authorization") String token) {
        return this.loginService.saveUserInfo(param, token);
    }
    @PostMapping("saveUserLogo")
    public ResponseResult saveUserLogo(@RequestParam("logo") MultipartFile file,
                                       @RequestHeader("Authorization") String token) {
        return this.loginService.saveUserLogo(file, token);
    }
    @PostMapping("mobile/send")
    public ResponseResult sendToOldMobile(@RequestHeader("Authorization") String token) {
        return this.loginService.sendToOldMobile(token);
    }
    @PostMapping("mobile/new")
    public ResponseResult saveNewMobile(@RequestBody Map<String, String> param,
                                        @RequestHeader("Authorization") String token) {
        return this.loginService.updateNewMobile(token, param.get("mobile"));
    }
}
