package com.yizhi.server.service.v1;

import com.yizhi.common.model.vo.ResponseResult;

public interface MessageService {
    ResponseResult queryMessageCommentList(Integer commentType, Integer page, Integer size);
    ResponseResult queryMessageAnnouncementList(Integer page, Integer size);
}
