package com.yizhi.common.handler;

import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.common.model.vo.YizhiException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult error(Exception e) {
        e.printStackTrace();
        return ResponseResult.fail();
    }

    @ExceptionHandler(YizhiException.class)
    @ResponseBody
    public ResponseResult error(YizhiException e) {
        e.printStackTrace();
        return ResponseResult.fail();
    }
}
