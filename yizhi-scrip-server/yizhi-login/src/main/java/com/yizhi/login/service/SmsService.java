package com.yizhi.login.service;

import com.yizhi.common.model.vo.ResponseResult;

/**
 * The interface Sms service.
 */
public interface SmsService {
    /**
     * Send check code response result.
     *
     * @param mobile the mobile
     * @return the response result
     */
    ResponseResult sendCheckCode(String mobile);
}
