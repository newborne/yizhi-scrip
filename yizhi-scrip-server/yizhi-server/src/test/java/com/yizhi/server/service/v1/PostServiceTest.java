package com.yizhi.server.service.v1;

import com.yizhi.common.client.LoginFeignClient;
import com.yizhi.common.model.pojo.mysql.ApUser;
import com.yizhi.common.util.UserThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The type Post service test.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PostServiceTest {
    @Autowired
    private PostService postService;
    @Autowired
    private LoginFeignClient loginFeignClient;
    /**
     * Save post.
     */
    @Test
    public void savePost() {
    }
    /**
     * Query friend post list.
     */
    @Test
    public void queryFriendPostList() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNjQ2NDAyODM3fQ.kdGMuUotemQM15RiIKMrxxbRw-ZFznpTS8h94W5nZBk";
        if (StringUtils.isNotEmpty(token)) {
            ApUser user = this.loginFeignClient.queryUserByToken(token);
            if (null != user) {
                //将当前对象存储到线程中去
                UserThreadLocal.set(user);
            }
        }
        System.out.println(this.postService.queryFriendPostList(1, 10));
    }
    /**
     * Query recommend post list.
     */
    @Test
    public void queryRecommendPostList() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNjQ2NDAyODM3fQ.kdGMuUotemQM15RiIKMrxxbRw-ZFznpTS8h94W5nZBk";
        if (StringUtils.isNotEmpty(token)) {
            ApUser user = this.loginFeignClient.queryUserByToken(token);
            if (null != user) {
                //将当前对象存储到线程中去
                UserThreadLocal.set(user);
            }
        }
        System.out.println(this.postService.queryRecommendPostList(1, 10));
    }
    /**
     * Query user post list.
     */
    @Test
    public void queryUserPostList() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNjQ2NDAyODM3fQ.kdGMuUotemQM15RiIKMrxxbRw-ZFznpTS8h94W5nZBk";
        if (StringUtils.isNotEmpty(token)) {
            ApUser user = this.loginFeignClient.queryUserByToken(token);
            if (null != user) {
                //将当前对象存储到线程中去
                UserThreadLocal.set(user);
            }
        }
        System.out.println(this.postService.queryUserPostList(1L, 1, 10));
    }
}