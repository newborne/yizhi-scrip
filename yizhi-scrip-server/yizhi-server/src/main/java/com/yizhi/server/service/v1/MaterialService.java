package com.yizhi.server.service.v1;

import com.yizhi.common.model.vo.ResponseResult;

public interface MaterialService {
    ResponseResult queryMaterialList(Integer materialType, Integer page, Integer size);
    ResponseResult queryRecommendMaterialList(Integer page, Integer size);
    ResponseResult queryMaterialByMaterialRid(Long materialRid);
    ResponseResult saveMaterial(String text, String[] tags, Integer materialType);
}
