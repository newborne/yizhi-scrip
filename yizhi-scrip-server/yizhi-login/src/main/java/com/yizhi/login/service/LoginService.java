package com.yizhi.login.service;

import com.yizhi.common.model.pojo.mysql.ApUser;
import com.yizhi.common.model.vo.ResponseResult;

public interface LoginService {

    ResponseResult login(String phone, String code);

    ApUser queryUserByToken(String token);
}
