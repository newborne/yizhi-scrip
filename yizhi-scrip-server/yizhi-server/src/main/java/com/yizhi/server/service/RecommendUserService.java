package com.yizhi.server.service;

import com.yizhi.common.model.request.RecommendUserRequest;
import com.yizhi.common.model.vo.ResponseResult;

public interface RecommendUserService {

    ResponseResult queryTodayBest();

    ResponseResult queryRecommendUser(Long friendId);

    ResponseResult queryRecommendUserList(RecommendUserRequest param);
}
