package com.yizhi.user.controller.v1;

import com.yizhi.common.model.pojo.ApUser;
import com.yizhi.common.model.vo.Result;
import com.yizhi.user.service.ApUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/user")
@Slf4j
public class UserController {

    //注入service
    @Autowired
    private ApUserService userService;

    @PostMapping("loginVerification")
    public Result login(@RequestBody Map<String, String> param) {
        //获取手机的value
        String phone = param.get("phone");
        //获取验证码
        String code = param.get("verificationCode");
        //调用service中的login
        Map<String, Object> login = userService.login(phone, code);
        //如果login不为空说明登录成功
        if (login != null) {
            return Result.ok(login);
        }
        //利用builder注解进行链式编程,给出登录失败的错误码和错误信息
//        Result Result = Result.builder().errCode("000002").errMessage("登录失败").build();
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result);
        return Result.fail();
    }

    @GetMapping("{token}")
    public ApUser queryUserByToken(@PathVariable("token") String token){
        return userService.queryUserByToken(token);
    }
}
