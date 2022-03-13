package com.yizhi.server.service.v1;

import com.yizhi.common.model.enums.MsgEnum;

public interface MqService {
    Boolean sendMsg(String destination, MsgEnum type, String publishId);
}
