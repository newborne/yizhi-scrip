package com.yizhi.user.service;

public interface HuanXinService {

    boolean register(Long userId);

    boolean contactUsers(Long userId, Long friendId);

    Boolean sendMsg(String target, String msg, String type);
}

