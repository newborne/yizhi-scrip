package com.yizhi.server.service;

import com.yizhi.common.client.LoginFeignClient;
import com.yizhi.common.model.pojo.mysql.ApUser;
import com.yizhi.common.model.request.RecommendUserRequest;
import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.common.util.UserThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RecommendUserServiceTest {
    @Autowired
    private LoginFeignClient loginFeignClient;

    @Autowired
    private RecommendUserService recommendService;

    @Test
    public void queryTodayBest() {
    }

    @Test
    public void queryRecommendUser() {
    }

    @Test
    public void queryRecommendUserList() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNjQ2NDAyODM3fQ.kdGMuUotemQM15RiIKMrxxbRw-ZFznpTS8h94W5nZBk";
        if (StringUtils.isNotEmpty(token)) {
            ApUser user = this.loginFeignClient.queryUserByToken(token);
            if (null != user) {
                //将当前对象存储到线程中去
                UserThreadLocal.set(user);
            }
        }
        ResponseResult responseResult = this.recommendService.queryRecommendUserList(new RecommendUserRequest());
        System.out.println(responseResult);
    }
}