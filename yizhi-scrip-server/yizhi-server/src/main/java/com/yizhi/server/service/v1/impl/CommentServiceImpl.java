package com.yizhi.server.service.v1.impl;

import com.yizhi.common.model.dto.CommentDTO;
import com.yizhi.common.model.dto.PageInfoDTO;
import com.yizhi.common.model.enums.CommentEnum;
import com.yizhi.common.model.enums.MsgEnum;
import com.yizhi.common.model.pojo.mongodb.Comment;
import com.yizhi.common.model.pojo.mysql.ApUserInfo;
import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.common.util.UserThreadLocal;
import com.yizhi.dubbo.api.v1.CommentApi;
import com.yizhi.dubbo.api.v1.UsersApi;
import com.yizhi.server.service.v1.ApUserInfoService;
import com.yizhi.server.service.v1.CommentService;
import com.yizhi.server.service.v1.MqService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @DubboReference(version = "1.0.0")
    private UsersApi usersApi;
    @DubboReference(version = "1.0.0")
    private CommentApi commentApi;
    @Autowired
    private MqService mqService;
    @Autowired
    private ApUserInfoService userService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public ResponseResult likeComment(String destination, String publishId) {
        String likeUserCommentKey = "COMMENT_LIKE_USER_" + UserThreadLocal.get().getId() + "_" + publishId;
        String likeCommentKey = "COMMENT_LIKE_" + publishId;
        Long likeCount = this.commentApi.queryCommentCount(publishId, CommentEnum.LIKE.getValue());
        //如果redis里面没有这个key，去查询dubbo，获取点赞数
        if (!redisTemplate.hasKey(likeCommentKey)) {
            //往redis里面存当前用户点赞信息
            this.redisTemplate.opsForValue().set(likeCommentKey, String.valueOf(likeCount));
        }
        if (!redisTemplate.hasKey(likeUserCommentKey)) {
            // 保存点赞
            this.commentApi.saveComment(Long.valueOf(UserThreadLocal.get().getId()),
                    publishId,
                    CommentEnum.LIKE.getValue(),
                    null);
            // 点赞数+1
            likeCount = this.redisTemplate.opsForValue().increment(likeCommentKey);
            // 点赞记录
            this.redisTemplate.opsForValue().set(likeUserCommentKey, CommentEnum.LIKE.getValue().toString());
            this.mqService.sendMsg(destination,
                    MsgEnum.LIKE, publishId);
        }
        return ResponseResult.ok(likeCount);
    }
    @Override
    public ResponseResult disLikeComment(String destination, String publishId) {
        if (this.commentApi.removeComment(Long.valueOf(UserThreadLocal.get().getId()),
                publishId,
                CommentEnum.LIKE.getValue())) {
            //不需要再去判断有没有对应的redisKey，因为取消就代表点过赞了
            String likeCommentKey = "COMMENT_LIKE_" + publishId;
            Long likeCount = this.redisTemplate.opsForValue().decrement(likeCommentKey);
            String likeUserCommentKey = "COMMENT_LIKE_USER_" + UserThreadLocal.get().getId() + "_" + publishId;
            this.redisTemplate.delete(likeUserCommentKey);
            this.mqService.sendMsg(destination,
                    MsgEnum.DISLIKE, publishId);
            return ResponseResult.ok(likeCount);
        }
        return ResponseResult.fail();
    }
    @Override
    public ResponseResult loveComment(String destination, String publishId) {
        String loveUserCommentKey = "COMMENT_LOVE_USER_" + UserThreadLocal.get().getId() + "_" + publishId;
        String loveCommentKey = "COMMENT_LOVE_" + publishId;
        Long loveCount = this.commentApi.queryCommentCount(publishId, CommentEnum.LOVE.getValue());
        if (!redisTemplate.hasKey(loveCommentKey)) {
            this.redisTemplate.opsForValue().set(loveCommentKey, String.valueOf(loveCount));
        }
        if (!redisTemplate.hasKey(loveUserCommentKey)) {
            this.commentApi.saveComment(Long.valueOf(UserThreadLocal.get().getId()),
                    publishId,
                    CommentEnum.LOVE.getValue(),
                    null);
            loveCount = this.redisTemplate.opsForValue().increment(loveCommentKey);
            this.redisTemplate.opsForValue().set(loveUserCommentKey, CommentEnum.LOVE.getValue().toString());
            this.mqService.sendMsg(destination,
                    MsgEnum.LOVE, publishId);
        }
        return ResponseResult.ok(loveCount);
    }
    @Override
    public ResponseResult unLoveComment(String destination, String publishId) {
        if (this.commentApi.removeComment(Long.valueOf(UserThreadLocal.get().getId()),
                publishId,
                CommentEnum.LOVE.getValue())) {
            //不需要再去判断有没有对应的redisKey，因为取消就代表点过赞了
            String loveCommentKey = "COMMENT_LOVE_" + publishId;
            Long loveCount = this.redisTemplate.opsForValue().decrement(loveCommentKey);
            String loveUserCommentKey = "COMMENT_LOVE_USER_" + UserThreadLocal.get().getId() + "_" + publishId;
            this.redisTemplate.delete(loveUserCommentKey);
            this.mqService.sendMsg(destination,
                    MsgEnum.UNLOVE, publishId);
            return ResponseResult.ok(loveCount);
        }
        return ResponseResult.fail();
    }
    @Override
    public ResponseResult textComment(String destination, String publishId, String text) {
        if (this.commentApi.saveComment(Long.valueOf(UserThreadLocal.get().getId()),
                publishId,
                CommentEnum.COMMENT.getValue(),
                text)) {
            this.mqService.sendMsg(destination,
                    MsgEnum.COMMENT, publishId);
            return ResponseResult.ok();
        }
        return ResponseResult.fail();
    }
    @Override
    public ResponseResult queryTextCommentList(String publishId, Integer page, Integer size) {
        List<CommentDTO> dtos = new ArrayList<>();
        List<Comment> comments = this.commentApi.queryTextCommentList(publishId, page, size);
        for (Comment comment : comments) {
            CommentDTO dto = new CommentDTO();
            // 填充评论信息
            dto.setId(comment.getId().toHexString());
            dto.setText(comment.getText());
            dto.setCreated(new DateTime(comment.getCreated()).toString("yyyy-MM-dd HH:m:s"));
            // 填充用户信息
            ApUserInfo userInfo = this.userService.queryUserInfoByUserId(comment.getUserId());
            dto.setLogo(userInfo.getLogo());
            dto.setNickName(userInfo.getNickName());
            // 填充点赞
            String likeUserCommentKey = "COMMENT_LIKE_USER_" + comment.getUserId() + "_" + comment.getId();
            dto.setHasLiked(this.redisTemplate.hasKey(likeUserCommentKey) ? 1 : 0);
            String likeCommentKey = "COMMENT_LIKE_" + comment.getId();
            String value = this.redisTemplate.opsForValue().get(likeCommentKey);
            if (StringUtils.isNotEmpty(value)) {
                dto.setLikeCount(Integer.valueOf(value));
            } else {
                dto.setLikeCount(0);
            }
            dtos.add(dto);
        }
        return ResponseResult.ok(new PageInfoDTO<>(0, page, size, dtos));
    }
}
