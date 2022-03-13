package com.yizhi.server.service.v2.impl;

import com.yizhi.common.model.dto.PageInfoDTO;
import com.yizhi.common.model.pojo.mongodb.Material;
import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.common.util.UserThreadLocal;
import com.yizhi.dubbo.api.v1.MaterialApi;
import com.yizhi.server.service.v2.MaterialServiceV2;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class MaterialServiceImplV2 implements MaterialServiceV2 {
    @DubboReference(version = "2.0.0")
    private MaterialApi materialApi;
    @Override
    public ResponseResult queryMaterialList(Integer materialType, Integer page, Integer size) {
        // return ResponseResult.ok(new PageInfoDTO<>(0,
        //         page,
        //         size,
        //         this.materialApi.queryMaterialList(materialType, page, size)));
        return ResponseResult.fail();
    }
    @Override
    public ResponseResult queryRecommendMaterialList(Integer page, Integer size) {
        return ResponseResult.ok(new PageInfoDTO<>(0, page, size, this.materialApi.queryRecommendMaterialList(
                Long.valueOf(UserThreadLocal.get().getId()), page, size)));
    }
    @Override
    public ResponseResult queryMaterialById(String id) {
        // return ResponseResult.ok(this.materialApi.queryMaterialById(id));
        return ResponseResult.fail();
    }
    @Override
    public ResponseResult saveMaterial(String text, String[] tags, Integer materialType) {
        // Material material = new Material();
        // material.setText(text);
        // material.setTags(tags);
        // material.setMaterialType(materialType);
        // return ResponseResult.ok(this.materialApi.saveMaterial(material));
        return ResponseResult.fail();
    }
}
