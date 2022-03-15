package com.yizhi.server.controller.v1;

import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.server.service.v1.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * The type Post controller.
 */
@RestController
@RequestMapping("api/v1/post")
public class PostController {
    @Autowired
    private PostService postService;
    /**
     * Save post response result.
     *
     * @param text          the text
     * @param location      the location
     * @param longitude     the longitude
     * @param latitude      the latitude
     * @param multipartFile the multipart file
     * @return the response result
     */
    @PostMapping("publish")
    public ResponseResult savePost(@RequestParam(value = "text") String text,
                                   @RequestParam(value = "location") String location,
                                   @RequestParam(value = "longitude") String longitude,
                                   @RequestParam(value = "latitude") String latitude,
                                   @RequestParam(value = "medias") MultipartFile[] multipartFile) {
        return this.postService.savePost(text,
                location,
                longitude,
                latitude,
                multipartFile);
    }
    /**
     * Query friend post list response result.
     *
     * @param page the page
     * @param size the size
     * @return the response result
     */
    @GetMapping("friend")
    public ResponseResult queryFriendPostList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                              @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return this.postService.queryFriendPostList(page, size);
    }
    /**
     * Query recommend post list response result.
     *
     * @param page the page
     * @param size the size
     * @return the response result
     */
    @GetMapping("recommend")
    public ResponseResult queryRecommendPostList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                 @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return this.postService.queryRecommendPostList(page, size);
    }
    /**
     * Query user post list response result.
     *
     * @param userId the user id
     * @param page   the page
     * @param size   the size
     * @return the response result
     */
    @GetMapping("user/{userId}")
    public ResponseResult queryUserPostList(@PathVariable("userId") Long userId,
                                            @RequestParam(value = "page", defaultValue = "1") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return this.postService.queryUserPostList(userId, page, size);
    }
    /**
     * Query post by id response result.
     *
     * @param id the id
     * @return the response result
     */
    @GetMapping("{id}")
    public ResponseResult queryPostById(@PathVariable("id") String id) {
        return this.postService.queryPostById(id);
    }
}
