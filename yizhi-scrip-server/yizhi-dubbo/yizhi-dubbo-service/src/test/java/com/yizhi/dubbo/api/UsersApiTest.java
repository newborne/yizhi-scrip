package com.yizhi.dubbo.api;

import com.yizhi.common.model.pojo.mongodb.RecommendUser;
import com.yizhi.dubbo.api.v1.UsersApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The type Users api test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UsersApiTest {
    @Autowired
    private UsersApi usersApi;
    /**
     * Query with max similarity.
     */
    @Test
    public void queryWithMaxSimilarity() {
        System.out.println("测试");
        RecommendUser recommendUser = usersApi.queryWithMaxSimilarity(1L);
        System.out.println(recommendUser);
    }
    /**
     * Query page info.
     */
    @Test
    public void queryPageInfo() {
//        System.out.println(usersApi.queryPageInfo(1L, 1, 5));
//        System.out.println(usersApi.queryPageInfo(1L, 2, 5));
//        System.out.println(usersApi.queryPageInfo(1L, 3, 5));
    }
    /**
     * Query similarity.
     */
    @Test
    public void querySimilarity() {
    }
    /**
     * Update location.
     */
    @Test
    public void updateLocation() {
    }
}