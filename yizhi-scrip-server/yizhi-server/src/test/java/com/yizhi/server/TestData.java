package com.yizhi.server;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestData {
    //生成数据代码
    @Test
    public void testMongoDBData() {
        for (int i = 2; i < 100; i++) {
            int similarity = RandomUtils.nextInt(30, 99);
            System.out.println("db.recommend_user.insert({\"userId\":" + i + " ,\"toUserId\":1,\"similarity\":" + similarity + " ,\"date\":\"2021/5/4\"})");
        }
    }
}
