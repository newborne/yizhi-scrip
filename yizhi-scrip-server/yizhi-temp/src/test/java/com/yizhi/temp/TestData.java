package com.yizhi.temp;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestData {
    //生成数据代码
    @Test
    public void testMongoDBData() {
        for (int i = 2; i < 100; i++) {
            int similarity = RandomUtils.nextInt(30, 99);
            System.out.println("db.recommend_user.insert({\"friendId\":" + i + " ,\"userId\":1,\"similarity\":" + similarity + " ,\"date\":\"2021/5/4\"})");
        }
    }
    @Test
    public void testInUsers() {
        for (int i = 2; i < 11; i++) {
            String userId = i + "";
            System.out.println("类" + this.getClass().getName() + "中" + Thread.currentThread()
                    .getStackTrace()[1].getMethodName() + "方法:" + userId + "  " + DigestUtils.md5Hex(userId + "_newborne_yizhi"));
        }
    }
    @Test
    public void time() {
        System.out.println("类" + this.getClass().getName() + "中" + Thread.currentThread()
                .getStackTrace()[1].getMethodName() + "方法：" + System.currentTimeMillis());
        System.out.println("类" + this.getClass().getName() + "中" + Thread.currentThread()
                .getStackTrace()[1].getMethodName() + "方法：" + new Date(System.currentTimeMillis()));
        System.out.println("类" + this.getClass().getName() + "中" + Thread.currentThread()
                .getStackTrace()[1].getMethodName() + "方法：" + new DateTime(System.currentTimeMillis()));
        Date date = new Date(System.currentTimeMillis());
        String dateStr = date.toString();
        DateTime dateTime = new DateTime(System.currentTimeMillis());
        String dateTimeStr = dateTime.toString("yyyy-MM-dd");
        System.out.println("类" + this.getClass().getName() + "中" + Thread.currentThread()
                .getStackTrace()[1].getMethodName() + "方法：" + dateStr + "  " + dateTimeStr);
    }
}
