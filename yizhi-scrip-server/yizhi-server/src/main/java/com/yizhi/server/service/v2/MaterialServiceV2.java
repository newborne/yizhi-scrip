package com.yizhi.server.service.v2;

import com.yizhi.common.model.vo.ResponseResult;

public interface MaterialServiceV2 {
    ResponseResult queryRecommendMaterialList(Integer page, Integer size);
    ResponseResult queryMaterialByMaterialRid(Long materialRid);
}
