package com.yizhi.dubbo.api;

import com.yizhi.common.model.pojo.mongodb.Material;
import com.yizhi.common.model.pojo.mongodb.Material;
import com.yizhi.common.model.vo.ResponseResult;

import java.util.List;

public interface MaterialApi {
    String saveMaterial(Material material);
    List<Material> queryRecommendMaterialList(Long userId, Integer page, Integer size);
    Material queryMaterialById(String id);
    List<Material> queryMaterialList(Integer materialType, Integer page, Integer size);
}
