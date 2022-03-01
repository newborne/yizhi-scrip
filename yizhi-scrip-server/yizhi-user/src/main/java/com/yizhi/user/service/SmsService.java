package com.yizhi.user.service;

import com.yizhi.common.model.vo.ErrorResult;

public interface SmsService {

    String sendSms(String mobile);

    ErrorResult sendCheckCode(String phone);
}