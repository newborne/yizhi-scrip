package com.yizhi.dubbo.api;

import com.yizhi.common.model.pojo.mongodb.Post;

import java.util.List;

public interface PostApi {
    // 发帖
    String savePost(Post post);
    // 查询帖子
    Post queryPostById(String id);
    // 查好友帖子-时间线
    List<Post> queryFriendPostList(Long userId, Integer page, Integer size);
    // 查自己帖子列表
    List<Post> queryUserPostList(Long userId, Integer page, Integer size);
    // 查推荐帖子列表
    List<Post> queryRecommendPostList(Long userId, Integer page, Integer size);
}
