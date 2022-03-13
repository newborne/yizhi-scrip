package com.yizhi.dubbo.api;

import com.yizhi.common.model.pojo.mongodb.RecommendUser;
import com.yizhi.dubbo.api.v1.UsersApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UsersApiTest {
    @Autowired
    private UsersApi usersApi;
    @Test
    public void queryWithMaxSimilarity() {
        System.out.println("测试");
        RecommendUser recommendUser = usersApi.queryWithMaxSimilarity(1L);
        System.out.println(recommendUser);
    }
    @Test
    public void queryPageInfo() {
//        System.out.println(usersApi.queryPageInfo(1L, 1, 5));
//        System.out.println(usersApi.queryPageInfo(1L, 2, 5));
//        System.out.println(usersApi.queryPageInfo(1L, 3, 5));
    }
    @Test
    public void querySimilarity() {
    }
    @Test
    public void updateLocation() {
    }
}