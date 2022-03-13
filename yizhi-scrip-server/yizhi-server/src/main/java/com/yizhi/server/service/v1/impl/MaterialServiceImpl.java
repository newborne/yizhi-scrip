package com.yizhi.server.service.v1.impl;

import com.yizhi.common.model.dto.PageInfoDTO;
import com.yizhi.common.model.pojo.mongodb.Material;
import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.common.util.UserThreadLocal;
import com.yizhi.dubbo.api.v1.MaterialApi;
import com.yizhi.server.service.v1.MaterialService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class MaterialServiceImpl implements MaterialService {
    @DubboReference(version = "1.0.0")
    private MaterialApi materialApi;
    @Override
    public ResponseResult queryMaterialList(Integer materialType, Integer page, Integer size) {
        return ResponseResult.ok(new PageInfoDTO<>(0,
                page,
                size,
                this.materialApi.queryMaterialList(materialType, page, size)));
    }
    @Override
    public ResponseResult queryRecommendMaterialList(Integer page, Integer size) {
        return ResponseResult.ok(new PageInfoDTO<>(0, page, size, this.materialApi.queryRecommendMaterialList(
                Long.valueOf(UserThreadLocal.get().getId()), page, size)));
    }
    @Override
    public ResponseResult queryMaterialById(String id) {
        return ResponseResult.ok(this.materialApi.queryMaterialById(id));
    }
    @Override
    public ResponseResult saveMaterial(String text, String[] tags, Integer materialType) {
        Material material = new Material();
        material.setText(text);
        material.setTags(tags);
        material.setMaterialType(materialType);
        return (this.materialApi.saveMaterial(material) != null) ? ResponseResult.ok() : ResponseResult.fail();
    }
}
