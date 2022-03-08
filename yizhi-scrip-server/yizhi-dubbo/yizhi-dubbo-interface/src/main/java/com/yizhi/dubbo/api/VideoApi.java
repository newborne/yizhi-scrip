package com.yizhi.dubbo.api;

import com.yizhi.common.model.pojo.mongodb.Video;
import com.yizhi.common.model.pojo.mongodb.Video;
import com.yizhi.common.model.pojo.mongodb.Video;

import java.util.List;

public interface VideoApi {
    // 发帖
    String saveVideo(Video video);
    // 查询帖子
    Video queryVideoById(String id);
    // 查好友帖子-时间线
    List<Video> queryFriendVideoList(Long userId, Integer page, Integer size);
    // 查自己帖子列表
    List<Video> queryUserVideoList(Long userId, Integer page, Integer size);
    // 查推荐帖子列表
    List<Video> queryRecommendVideoList(Long userId, Integer page, Integer size);
}
