package com.yizhi.server.controller.v1;

import com.yizhi.common.model.vo.PicUploadResult;
import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.server.service.ApUserInfoService;
import com.yizhi.server.service.HuanXinService;
import com.yizhi.server.service.PicUploadService;
import com.yizhi.server.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("api/v1/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @GetMapping("add/{friendId}")
    public ResponseResult addUsers(@PathVariable("friendId") Long friendId) {
        return this.usersService.addUsers(friendId);
    }
    @GetMapping("list")
    public ResponseResult queryUsersList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size,
                                         @RequestParam(value = "keyword", required = false) String keyword) {
        return this.usersService.queryUsersList(page, size, keyword);
    }
    @GetMapping("huanxin")
    public ResponseResult user() {
        return this.usersService.user();
    }
    @PostMapping("huanxin/register/{id}")
    public ResponseResult register(@PathVariable("id") Long userId) {
        return this.usersService.register(userId);
    }
    @PostMapping("huanxin/messages")
    public ResponseResult sendMsg(@RequestParam("target") String target,
                                  @RequestParam("msg") String msg,
                                  @RequestParam(value = "type", defaultValue = "txt") String type) {
        return this.usersService.sendMsg(target, msg, type);
    }
    // @PostMapping("uploadPic")
    // public PicUploadResult uploadPic(@RequestParam("file") MultipartFile file) {
    //     return this.usersService.uploadPic(file);
    // }
    @PostMapping("saveUserInfo")
    public ResponseResult saveUserInfo(@RequestBody Map<String, String> param) {
        return this.usersService.saveUserInfo(param);
    }
    @PostMapping("saveUserLogo")
    public ResponseResult saveUserLogo(@RequestParam("logo") MultipartFile file) {
        return this.usersService.saveUserLogo(file);
    }
    @GetMapping("queryUserInfo/{userId}")
    public ResponseResult queryUserInfo(@PathVariable("userId") Long userId) {
        return this.usersService.queryUserInfo(userId);
    }
    @PostMapping("updateUserInfo")
    public ResponseResult updateUserInfo(@RequestBody Map<String, String> param) {
        return this.usersService.updateUserInfo(param);
    }
}

