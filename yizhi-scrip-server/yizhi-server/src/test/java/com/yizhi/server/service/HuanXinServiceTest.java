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
        this.huanxinService.register(1l);
    }

    @Test
    public void contactUsers() {
    }

    @Test
    public void sendMsg() {
    }
}