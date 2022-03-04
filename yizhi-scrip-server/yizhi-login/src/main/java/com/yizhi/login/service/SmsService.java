package com.yizhi.login.service;

import com.yizhi.common.model.vo.ResponseResult;

public interface SmsService {

    ResponseResult sendCheckCode(String phone);
}
