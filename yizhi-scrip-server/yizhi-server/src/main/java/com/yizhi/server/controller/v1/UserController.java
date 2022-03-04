package com.yizhi.server.controller.v1;

import com.yizhi.common.model.vo.PicUploadResult;
import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.server.service.ApUserService;
import com.yizhi.server.service.impl.HuanXinServiceImpl;
import com.yizhi.server.service.impl.PicUploadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private PicUploadServiceImpl picUploadService;

    @Autowired
    private HuanXinServiceImpl huanXinService;

    @Autowired
    private ApUserService apUserService;

    @PostMapping("register/{id}")
    public ResponseResult register(@PathVariable("id") Long userId) {
        return this.huanXinService.register(userId);
    }

    @PostMapping("contacts/{user_id}/{friend_id}")
    public ResponseResult contactUsers(@PathVariable("user_id") Long userId,
                                       @PathVariable("friend_id") Long friendId) {
        return this.huanXinService.contactUsers(userId, friendId);
    }

    @PostMapping("messages")
    public ResponseResult sendMsg(@RequestParam("target") String target,
                                  @RequestParam("msg") String msg,
                                  @RequestParam(value = "type", defaultValue = "txt") String type) {
        return this.huanXinService.sendMsg(target, msg, type);
    }

    @PostMapping("uploadPic")
    public PicUploadResult upload(MultipartFile file) {
        return this.picUploadService.upload(file);
    }

    @PostMapping("saveUserInfo")
    public ResponseResult saveUserInfo(@RequestBody Map<String, String> param) {
        return (this.apUserService.saveUserInfo(param)) ? ResponseResult.ok() : ResponseResult.fail();
    }

    @PostMapping("saveUserLogo")
    public ResponseResult saveUserLogo(@RequestParam MultipartFile file) {
        return (this.apUserService.saveUserLogo(file)) ? ResponseResult.ok() : ResponseResult.fail();
    }
}

