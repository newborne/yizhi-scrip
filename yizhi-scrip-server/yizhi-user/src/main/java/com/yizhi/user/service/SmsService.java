package com.yizhi.user.service;

import com.yizhi.common.model.vo.Result;

public interface SmsService {

    String sendSms(String mobile);

    Result sendCheckCode(String phone);
}