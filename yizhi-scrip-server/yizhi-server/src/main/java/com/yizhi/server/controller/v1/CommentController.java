package com.yizhi.server.controller.v1;

import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.server.service.v1.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("{destination}/{publishId}/like")
    public ResponseResult likeComment(@PathVariable("publishId") String publishId,
                                      @PathVariable("destination") String destination) {
        return this.commentService.likeComment(destination, publishId);
    }
    @GetMapping("{destination}/{publishId}/dislike")
    public ResponseResult disLikeComment(@PathVariable("publishId") String publishId,
                                         @PathVariable("destination") String destination) {
        return this.commentService.disLikeComment(destination, publishId);
    }
    @GetMapping("{destination}/{publishId}/love")
    public ResponseResult loveComment(@PathVariable("publishId") String publishId,
                                      @PathVariable("destination") String destination) {
        return this.commentService.loveComment(destination, publishId);
    }
    @GetMapping("{destination}/{publishId}/unlove")
    public ResponseResult unLoveComment(@PathVariable("publishId") String publishId,
                                        @PathVariable("destination") String destination) {
        return this.commentService.unLoveComment(destination, publishId);
    }
    @PostMapping("{destination}/{publishId}/text")
    public ResponseResult textComment(@PathVariable("publishId") String publishId,
                                      @PathVariable("destination") String destination,
                                      @RequestParam(value = "text") String text) {
        return this.commentService.textComment(destination, publishId, text);
    }
    @GetMapping("{publishId}")
    public ResponseResult queryTextCommentList(@PathVariable("publishId") String publishId,
                                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return this.commentService.queryTextCommentList(publishId, page, size);
    }
}
