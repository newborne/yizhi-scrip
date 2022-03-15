package com.yizhi.neo4j.service;

import com.yizhi.common.model.vo.ResponseResult;

public interface MaterialService {
    ResponseResult saveMaterialType();
    ResponseResult saveMaterial();
    ResponseResult saveMaterialRecommendRelationship();
    ResponseResult queryRecommendMaterial(Long userId);
}
