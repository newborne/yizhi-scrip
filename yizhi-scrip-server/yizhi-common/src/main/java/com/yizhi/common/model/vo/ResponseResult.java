package com.yizhi.common.model.vo;

import com.yizhi.common.model.enums.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "全局统一返回结果")
public class ResponseResult<T> {

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public ResponseResult() {
    }

    protected static <T> ResponseResult<T> build(T data) {
        ResponseResult<T> result = new ResponseResult<T>();
        if (data != null)
            result.setData(data);
        return result;
    }

    public static <T> ResponseResult<T> build(T body, ResultCodeEnum resultCodeEnum) {
        ResponseResult<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    public static <T> ResponseResult<T> build(Integer code, String message) {
        ResponseResult<T> result = build(null);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> ResponseResult<T> ok() {
        return ResponseResult.ok(null);
    }

    public static <T> ResponseResult<T> ok(T data) {
        ResponseResult<T> result = build(data);
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static <T> ResponseResult<T> fail() {
        return ResponseResult.fail(null);
    }

    public static <T> ResponseResult<T> fail(T data) {
        ResponseResult<T> responseResult = build(data);
        return build(data, ResultCodeEnum.FAIL);
    }

    public ResponseResult<T> message(String msg) {
        this.setMessage(msg);
        return this;
    }

    public ResponseResult<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

    public boolean isOk() {
        if (this.getCode().intValue() == ResultCodeEnum.SUCCESS.getCode().intValue()) {
            return true;
        }
        return false;
    }
}