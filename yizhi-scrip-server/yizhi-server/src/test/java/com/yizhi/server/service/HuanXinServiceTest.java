package com.yizhi.server.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class HuanXinServiceTest {
    @Autowired
    private HuanXinService huanxinService;
    @Test
    public void register() {
        for (int i = 3; i < 11; i++) {
            this.huanxinService.register((long) i);
        }
    }
    @Test
    public void contactUsers() {
    }
    @Test
    public void sendMsg() {
    }
}