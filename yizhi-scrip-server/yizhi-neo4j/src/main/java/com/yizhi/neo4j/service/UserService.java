package com.yizhi.neo4j.service;

import com.yizhi.common.model.vo.ResponseResult;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Save user response result.
     *
     * @return the response result
     */
    ResponseResult saveUser();
    /**
     * Save user recommend relationship response result.
     *
     * @return the response result
     */
    ResponseResult saveUserRecommendRelationship();
}
