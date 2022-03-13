package com.yizhi.server.controller.v2;

import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.server.service.v1.MaterialService;
import com.yizhi.server.service.v2.MaterialServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2/material")
public class MaterialControllerV2 {
    @Autowired
    private MaterialServiceV2 materialService;
    @PostMapping("save")
    public ResponseResult saveMaterial(@RequestParam(value = "text") String text,
                                       @RequestParam(value = "tags") String[] tags,
                                       @RequestParam(value = "materialType") Integer materialType) {
        return this.materialService.saveMaterial(text, tags, materialType);
    }
    @GetMapping("recommend")
    public ResponseResult queryRecommendMaterialList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                     @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return this.materialService.queryRecommendMaterialList(page, size);
    }
}
