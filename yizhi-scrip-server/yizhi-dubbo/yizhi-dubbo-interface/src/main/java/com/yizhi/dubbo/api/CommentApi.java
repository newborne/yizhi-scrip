package com.yizhi.dubbo.api;

import com.yizhi.common.model.dto.PageInfoDTO;
import com.yizhi.common.model.pojo.mongodb.Comment;

import java.util.List;

public interface CommentApi {
    // 评论点赞喜欢
    Boolean saveComment(Long userId, String publishId, Integer type, String text);
    // 取消点赞喜欢
    Boolean removeComment(Long userId, String publishId, Integer commentType);
    // 查看评论数量
    Long queryCommentCount(String publishId, Integer type);
    // 查看评论列表
    List<Comment> queryTextCommentList(String publishId, Integer page, Integer size);
    List<Comment> queryCommentListByPublishUserId(Long publishUserId, Integer type, Integer page, Integer size);
}
