package com.yizhi.server.service.v2;

import com.yizhi.common.model.vo.ResponseResult;

public interface MaterialServiceV2 {
    ResponseResult queryMaterialList(Integer materialType, Integer page, Integer size);
    ResponseResult queryRecommendMaterialList(Integer page, Integer size);
    ResponseResult queryMaterialById(String id);
    ResponseResult saveMaterial(String text, String[] tags, Integer materialType);
}
