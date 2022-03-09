package com.yizhi.dubbo.api;

import com.yizhi.common.model.pojo.mongodb.Users;
import org.apache.dubbo.config.annotation.DubboReference;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UsersApiTest {
    // @Autowired
    // private UsersApi usersApi;
    // @Test
    // public void saveUsers() {
    //     this.usersApi.saveUsers(new Users(ObjectId.get(), 1L, 2L, System.currentTimeMillis()));
    //     this.usersApi.saveUsers(new Users(ObjectId.get(), 1L, 3L, System.currentTimeMillis()));
    //     this.usersApi.saveUsers(new Users(ObjectId.get(), 2L, 3L, System.currentTimeMillis()));
    //     this.usersApi.saveUsers(new Users(ObjectId.get(), 2L, 1L, System.currentTimeMillis()));
    //     this.usersApi.saveUsers(new Users(ObjectId.get(), 3L, 1L, System.currentTimeMillis()));
    //     this.usersApi.saveUsers(new Users(ObjectId.get(), 3L, 2L, System.currentTimeMillis()));
    // }
    // @Test
    // public void queryUsersList() {
    //     System.out.println(this.usersApi.queryUsersList(1L));
    //     ;
    // }
    // @Test
    // public void testQueryUsersList() {
    //     System.out.println(this.usersApi.queryUsersList(1L, 1, 1));
    // }
}