package com.yizhi.user.service;

import com.yizhi.common.model.pojo.ApUser;

import java.util.Map;

public interface ApUserService {
    //定义常量
    String CHECK_CODE = "CHECK_CODE_";

    Map<String, Object> login(String phone, String code);

    ApUser queryUserByToken(String token);
}
