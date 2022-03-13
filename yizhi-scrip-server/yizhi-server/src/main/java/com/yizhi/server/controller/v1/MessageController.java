package com.yizhi.server.controller.v1;

import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.server.service.v1.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/message")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @GetMapping("{commentType}")
    public ResponseResult queryMessageCommentList(
            @PathVariable("commentType") Integer commentType,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return this.messageService.queryMessageCommentList(commentType, page, size);
    }
    @GetMapping("announcement")
    public ResponseResult queryMessageAnnouncementList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return this.messageService.queryMessageAnnouncementList(page, size);
    }
}
