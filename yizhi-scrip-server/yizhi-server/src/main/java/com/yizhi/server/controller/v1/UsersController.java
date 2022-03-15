package com.yizhi.server.controller.v1;

import com.yizhi.common.model.request.RecommendUserRequest;
import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.server.annotation.Cache;
import com.yizhi.server.service.v1.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("follow/counts")
    public ResponseResult queryFollowCounts() {
        return this.usersService.queryFollowCounts();
    }
    @GetMapping("follow/list/{type}")
    public ResponseResult queryFollowList(@PathVariable("type") String type,
                                          @RequestParam(value = "page", defaultValue = "1") Integer page,
                                          @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return this.usersService.queryFollowList(type, page, size);
    }
    @GetMapping("follow/{friendId}")
    public ResponseResult follow(@PathVariable("friendId") Long friendId) {
        return this.usersService.follow(friendId);
    }
    @GetMapping("unfollow/{friendId}")
    public ResponseResult unFollow(@PathVariable("friendId") Long friendId) {
        return this.usersService.unFollow(friendId);
    }
    @Cache
    @GetMapping("todayBest")
    public ResponseResult queryTodayBest() {
        return this.usersService.queryTodayBest();
    }
    @Cache
    @GetMapping("{id}")
    public ResponseResult queryRecommendUser(@PathVariable("id") Long friendId) {
        return this.usersService.queryRecommendUser(friendId);
    }
    @Cache
    @GetMapping("recommendUserList")
    public ResponseResult queryRecommendUserList(RecommendUserRequest param) {
        return this.usersService.queryRecommendUserList(param);
    }
    @PostMapping("updateLocation")
    public ResponseResult updateLocation(@RequestBody Map<String, Object> param) {
        return this.usersService.updateLocation(param);
    }
    @GetMapping("near")
    public ResponseResult queryNearUser(@RequestParam(value = "sex", required = false) String sex,
                                        @RequestParam(value = "distance", defaultValue = "5000") String distance) {
        return this.usersService.queryNearUser(sex, distance);
    }
}

