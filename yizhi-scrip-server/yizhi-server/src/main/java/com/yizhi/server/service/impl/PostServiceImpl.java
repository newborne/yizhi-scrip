package com.yizhi.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yizhi.common.client.LoginFeignClient;
import com.yizhi.common.model.dto.PageInfoDTO;
import com.yizhi.common.model.dto.PostDTO;
import com.yizhi.common.model.enums.MsgEnum;
import com.yizhi.common.model.pojo.mongodb.Post;
import com.yizhi.common.model.pojo.mysql.ApUser;
import com.yizhi.common.model.pojo.mysql.ApUserInfo;
import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.common.util.RelativeDateFormat;
import com.yizhi.common.util.UserThreadLocal;
import com.yizhi.dubbo.api.CommentApi;
import com.yizhi.dubbo.api.PostApi;
import com.yizhi.dubbo.api.UsersApi;
import com.yizhi.server.service.ApUserService;
import com.yizhi.server.service.MqService;
import com.yizhi.server.service.PicUploadService;
import com.yizhi.server.service.PostService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class PostServiceImpl implements PostService {
    @DubboReference(version = "1.0.0")
    private PostApi postApi;
    @Autowired
    private PicUploadService picUploadService;
    @Autowired
    private MqService mqService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private ApUserService userService;
    @DubboReference(version = "1.0.0")
    private CommentApi commentApi;
    @Override
    public ResponseResult savePost(String text,
                                   String location,
                                   String longitude,
                                   String latitude,
                                   MultipartFile[] multipartFile) {
        ApUser user = UserThreadLocal.get();
        //创建新的发布对象
        Post post = new Post();
        post.setUserId(Long.valueOf(user.getId()));
        post.setText(text);
        post.setLongitude(longitude);
        post.setLatitude(latitude);
        post.setLocation(location);
        post.setSeeType(1);
        List<String> picUrls = new ArrayList<>();
        //处理上传的图片使用阿里云保存
        // for (MultipartFile file : multipartFile) {
        //     PicUploadResult picUploadResult = this.picUploadService.uploadPic(file);
        //     picUrls.add(picUploadResult.getName());
        // }
        post.setMedias(picUrls);
        String publishId = this.postApi.savePost(post);
        if (StringUtils.isNotEmpty(publishId)) {
            //发送消息
            this.mqService.sendMsg("post",
                    MsgEnum.SAVE,
                    publishId);
            return ResponseResult.ok();
        }
        return ResponseResult.fail();
    }
    @Override
    public ResponseResult queryFriendPostList(Integer page, Integer size) {
        List<Post> postList = this.postApi.queryFriendPostList(Long.valueOf(UserThreadLocal.get().getId()), page, size);
        List<PostDTO> dtos = new ArrayList<>();
        for (Post post : postList) {
            PostDTO dto = this.fillValueToPost(post);
            dtos.add(dto);
        }
        return ResponseResult.ok(new PageInfoDTO<>(0, page, size, dtos));
    }
    @Override
    public ResponseResult queryRecommendPostList(Integer page, Integer size) {
        List<Post> postList = this.postApi.queryRecommendPostList(Long.valueOf(UserThreadLocal.get().getId()),
                page,
                size);
        List<PostDTO> dtos = new ArrayList<>();
        for (Post post : postList) {
            PostDTO dto = this.fillValueToPost(post);
            dtos.add(dto);
        }
        return ResponseResult.ok(new PageInfoDTO<>(0, page, size, dtos));
    }
    @Override
    public ResponseResult queryUserPostList(Long userId, Integer page, Integer size) {
        List<Post> postList = this.postApi.queryUserPostList(userId, page, size);
        List<PostDTO> dtos = new ArrayList<>();
        for (Post post : postList) {
            PostDTO dto = this.fillValueToPost(post);
            dtos.add(dto);
        }
        return ResponseResult.ok(new PageInfoDTO<>(0, page, size, dtos));
    }
    @Override
    public ResponseResult queryPostById(String id) {
        Post post = this.postApi.queryPostById(id);
        PostDTO dto = this.fillValueToPost(post);
        return ResponseResult.ok(dto);
    }
    // 填充
    private PostDTO fillValueToPost(Post post) {
        // 填充参数
        PostDTO dto = new PostDTO();
        // 填充帖子内容
        dto.setId(post.getId().toHexString());
        dto.setMedias(post.getMedias().toArray(new String[]{}));
        dto.setText(post.getText());
        dto.setCreated(RelativeDateFormat.format(new Date(post.getCreated())));
        dto.setDistance("0.9公里");//TODO 距离
        // 填充评论相关
        ApUser user = UserThreadLocal.get();
        // 是否点赞
        String likeUserCommentKey = "COMMENT_LIKE_USER_" + user.getId() + "_" + dto.getId();
        dto.setHasLiked(this.redisTemplate.hasKey(likeUserCommentKey) ? 1 : 0);
        String likeCommentKey = "COMMENT_LIKE_" + dto.getId();
        String value = this.redisTemplate.opsForValue().get(likeCommentKey);
        if (StringUtils.isNotEmpty(value)) {
            dto.setLikeCount(Integer.valueOf(value));
        } else {
            dto.setLikeCount(0);
        }
        // 是否喜欢
        String loveUserCommentKey = "COMMENT_LOVE_USER_" + user.getId() + "_" + dto.getId();
        dto.setHasLoved(this.redisTemplate.hasKey(loveUserCommentKey) ? 1 : 0);
        String loveCommentKey = "COMMENT_LOVE_" + dto.getId();
        String loveValue = this.redisTemplate.opsForValue().get(loveCommentKey);
        if (StringUtils.isNotEmpty(loveValue)) {
            dto.setLoveCount(Integer.valueOf(loveValue));
        } else {
            dto.setLoveCount(0);
        }
        // 文字评论
        Long commentCount = this.commentApi.queryCommentCount(dto.getId(), 2);
        dto.setCommentCount(commentCount == null ? 0 : commentCount.intValue());
        // 填充UserInfo
        dto.setUserId(post.getUserId());
        ApUserInfo userInfo = this.userService.queryUserInfoByUserId(dto.getUserId());
        dto.setAge(userInfo.getAge());
        dto.setLogo(userInfo.getLogo());
        dto.setSex(userInfo.getSex().name().toLowerCase(Locale.ROOT));
        dto.setNickName(userInfo.getNickName());
        dto.setTags(StringUtils.split(userInfo.getTags(), ","));
        return dto;
    }
}
