package com.yizhi.common.handler;

import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.common.model.vo.YizhiException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The type Global exception handler.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Error response result.
     *
     * @param e the e
     * @return the response result
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult error(Exception e) {
        e.printStackTrace();
        return ResponseResult.fail();
    }
    /**
     * Error response result.
     *
     * @param e the e
     * @return the response result
     */
    @ExceptionHandler(YizhiException.class)
    @ResponseBody
    public ResponseResult error(YizhiException e) {
        e.printStackTrace();
        return ResponseResult.fail();
    }
}
