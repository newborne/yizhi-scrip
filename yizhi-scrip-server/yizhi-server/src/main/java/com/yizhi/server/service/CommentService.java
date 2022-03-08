package com.yizhi.server.service;

import com.yizhi.common.model.dto.PageInfoDTO;
import com.yizhi.common.model.pojo.mongodb.Comment;
import com.yizhi.common.model.vo.ResponseResult;

import java.util.List;

public interface CommentService {
    ResponseResult likeComment(String destination, String publishId);
    ResponseResult disLikeComment(String destination, String publishId);
    ResponseResult loveComment(String destination, String publishId);
    ResponseResult unLoveComment(String destination, String publishId);
    ResponseResult textComment(String destination, String publishId, String text);
    ResponseResult queryTextCommentList(String publishId, Integer page, Integer size);
}
