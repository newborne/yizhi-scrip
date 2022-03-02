package com.yizhi.user.controller.v1;

import com.yizhi.common.model.vo.Result;
import com.yizhi.user.service.ApUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("api/v1/user")
public class UserInfoController {
    @Autowired
    private ApUserInfoService userInfoService;

    @PostMapping("loginReginfo")
    public Result saveUserInfo(@RequestBody Map<String, String> param,
                               @RequestHeader("Authorization") String token) {
        try {
            Boolean bool = this.userInfoService.saveUserInfo(param, token);
            if (bool) {
                return Result.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Result Result = Result.builder().errCode("000001").errMessage("保存用户信息失败！").build();
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result);
        return Result.fail();
    }

    @PostMapping("loginReginfo/head")
    public Result saveUserLogo(@RequestParam MultipartFile file,
                               @RequestHeader("Authorization") String token) {
        try {
            Boolean bool = this.userInfoService.saveUserLogo(file, token);
            if (bool) {
                return Result.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Result Result = Result.builder().errCode("000001").errMessage("保存用户logo失败！").build();
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result);
        return Result.fail();
    }
}

