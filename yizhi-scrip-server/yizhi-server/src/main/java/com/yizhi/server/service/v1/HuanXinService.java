package com.yizhi.server.service.v1;

import com.yizhi.common.model.vo.ResponseResult;

public interface HuanXinService {
    ResponseResult user();
    ResponseResult register(Long userId);
    ResponseResult contactUsers(Long userId, Long friendId);
    ResponseResult sendMsg(String target, String msg, String type);
}
