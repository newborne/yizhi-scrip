package com.yizhi.dubbo.api;

import com.google.common.collect.Lists;
import com.yizhi.dubbo.api.v1.PostApi;
import org.bson.types.ObjectId;
import com.yizhi.common.model.pojo.mongodb.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PostApiTest {
    @Autowired
    private PostApi postApi;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Test
    public void savePost() {
        System.out.println("测试");
        Post post = new Post();
        // post.setId(new ObjectId());
        // post.setPostRid(1L);
        // post.setUserId(3L);
        // post.setText("测试3");
        // post.setMedias(Lists.newArrayList());
        // post.setLongitude("");
        // post.setLatitude("");
        // post.setLocation("上海市");
        // System.out.println(this.postApi.savePost(post));
        // System.out.println("测试");
        // post.setId(new ObjectId());
        // post.setPostRid(1L);
        // post.setUserId(3L);
        // post.setText("测试4");
        // post.setMedias(Lists.newArrayList());
        // post.setLongitude("");
        // post.setLatitude("");
        // post.setLocation("上海市");
        // System.out.println(this.postApi.savePost(post));
        System.out.println("测试");
        post.setId(new ObjectId());
        post.setPostRid(1L);
        post.setUserId(3L);
        post.setText("测试5");
        post.setMedias(Lists.newArrayList());
        post.setLongitude("");
        post.setLatitude("");
        post.setLocation("上海市");
        System.out.println(this.postApi.savePost(post));
    }
    @Test
    public void queryPostById() {
        System.out.println("测试");
        System.out.println(this.postApi.queryPostById("6224d2cc56bc6a09208d6ab4"));
    }
    @Test
    public void queryFriendPostList() {
        System.out.println(this.postApi.queryFriendPostList(2L, 1, 3));
    }
    @Test
    public void queryUserPostList() {
        System.out.println(this.postApi.queryUserPostList(1L, 1, 3));
    }
    @Test
    public void queryRecommendPostList() {
        this.redisTemplate.opsForValue().set("RECOMMEND_POST_1", "3,4,5");
        System.out.println(this.postApi.queryRecommendPostList(1L, 1, 10));
    }
}