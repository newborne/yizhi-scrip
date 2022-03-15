package com.yizhi.server.service.v1;

import com.yizhi.common.client.LoginFeignClient;
import com.yizhi.common.model.pojo.mysql.ApUser;
import com.yizhi.common.util.UserThreadLocal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CommentServiceTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private LoginFeignClient loginFeignClient;
    @Test
    public void likeComment() {
        // 1给3点赞
        UserThreadLocal.set(this.loginFeignClient.queryUserByToken(
                "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzMzNTQzMDAwfQ.nhNpl5Ri8fSqe8Zn1JHfe1DO-F68PFSyeP9eMVtSdTM"));
        commentService.likeComment("post", "622dcdd97c84e8540c1dc9ad");
        // 3给3点赞
        UserThreadLocal.set(this.loginFeignClient.queryUserByToken(
                "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MywiZXhwIjoxNzMzNTQ3MTExfQ.OL5pJA5_xLWEJY6hjXQ1LPWX1o1wcY-I0WUqOMQEHHI"));
        commentService.likeComment("post", "622dcdd97c84e8540c1dc9ad");
        // 3给4点赞
        UserThreadLocal.set(this.loginFeignClient.queryUserByToken(
                "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MywiZXhwIjoxNzMzNTQ3MTExfQ.OL5pJA5_xLWEJY6hjXQ1LPWX1o1wcY-I0WUqOMQEHHI"));
        commentService.likeComment("post", "622dce427c84e8540c1dc9b0");
    }
    @Test
    public void disLikeComment() {
    }
    @Test
    public void loveComment() {
    }
    @Test
    public void unLoveComment() {
    }
}