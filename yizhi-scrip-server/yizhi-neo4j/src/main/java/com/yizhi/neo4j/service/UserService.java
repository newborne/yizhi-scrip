package com.yizhi.neo4j.service;

import com.yizhi.common.model.vo.ResponseResult;

public interface UserService {
    ResponseResult saveUser();
    ResponseResult saveUserRecommendRelationship();
}
