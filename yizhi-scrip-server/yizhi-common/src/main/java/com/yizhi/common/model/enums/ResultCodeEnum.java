package com.yizhi.common.model.enums;

import io.swagger.annotations.ApiModel;
import lombok.Getter;

@Getter
@ApiModel
public enum ResultCodeEnum {

    // 成功段
    SUCCESS(200, "成功"),
    FAIL(201, "失败"),
    // 登录段1~50
    SEND_CHECKCODE_ERROR_1(001, "发送短信验证码失败"),
    SEND_CHECKCODE_ERROR_2(002, "上一次发送的验证码还未失效"),
    NEED_LOGIN(1, "需要登录后操作"),
    LOGIN_PASSWORD_ERROR(2, "密码错误"),
    // TOKEN50~100
    TOKEN_INVALID(50, "无效的TOKEN"),
    TOKEN_EXPIRE(51, "TOKEN已过期"),
    TOKEN_REQUIRE(52, "TOKEN是必须的"),
    // SIGN验签 100~120
    SIGN_INVALID(100, "无效的SIGN"),
    SIG_TIMEOUT(101, "SIGN已过期"),
    // 参数错误 500~1000
    PARAM_REQUIRE(500, "缺少参数"),
    PARAM_INVALID(501, "无效参数"),
    PARAM_IMAGE_FORMAT_ERROR(502, "图片格式有误"),
    SERVER_ERROR(503, "服务器内部错误"),
    // 数据错误 1000~2000
    DATA_EXIST(1000, "数据已经存在"),
    AP_USER_DATA_NOT_EXIST(1001, "ApUser数据不存在"),
    DATA_NOT_EXIST(1002, "数据不存在"),
    // 数据错误 3000~3500
    NO_OPERATOR_AUTH(3000, "无权限操作");

    private Integer code;
    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
