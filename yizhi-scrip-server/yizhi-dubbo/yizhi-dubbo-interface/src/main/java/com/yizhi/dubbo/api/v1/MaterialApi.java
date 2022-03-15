package com.yizhi.dubbo.api.v1;

import com.yizhi.common.model.pojo.mongodb.Article;
import com.yizhi.common.model.pojo.mongodb.Material;

import java.util.List;

public interface MaterialApi {
    String saveMaterial(Material material);
    List<Material> queryRecommendMaterialList(Long userId, Integer page, Integer size);
    Material queryMaterialById(String id);
    Material queryMaterialByMaterialRid(Long materialRid);
    List<Material> queryMaterialList(Integer materialType, Integer page, Integer size);
}
