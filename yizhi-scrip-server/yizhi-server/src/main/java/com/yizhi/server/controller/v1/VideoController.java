package com.yizhi.server.controller.v1;

import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.server.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/video")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @PostMapping("publish")
    public ResponseResult saveVideo(@RequestParam(value = "text") String text,
                                    @RequestParam(value = "picFile", required = false) MultipartFile picFile,
                                    @RequestParam(value = "videoFile", required = false) MultipartFile videoFile) throws IOException {
        return this.videoService.saveVideo(text,
                picFile, videoFile);
    }
    @GetMapping("friend")
    public ResponseResult queryFriendVideoList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return this.videoService.queryFriendVideoList(page, size);
    }
    @GetMapping("recommend")
    public ResponseResult queryRecommendVideoList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                  @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return this.videoService.queryRecommendVideoList(page, size);
    }
    @GetMapping("user/{userId}")
    public ResponseResult queryUserVideoList(
            @PathVariable("userId") Long userId, @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return this.videoService.queryUserVideoList(userId, page, size);
    }
    @GetMapping("{id}")
    public ResponseResult queryVideoById(@PathVariable("id") String id) {
        return this.videoService.queryVideoById(id);
    }
}
