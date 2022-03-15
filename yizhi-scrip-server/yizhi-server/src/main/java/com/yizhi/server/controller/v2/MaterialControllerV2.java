package com.yizhi.server.controller.v2;

import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.server.service.v2.MaterialServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2/material")
public class MaterialControllerV2 {
    @Autowired
    private MaterialServiceV2 materialService;
    @GetMapping("{materialRid}")
    public ResponseResult queryMaterialByMaterialRid(@PathVariable("materialRid") Long materialRid) {
        return this.materialService.queryMaterialByMaterialRid(materialRid);
    }
    @GetMapping("recommend")
    public ResponseResult queryRecommendMaterialList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                     @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return this.materialService.queryRecommendMaterialList(page, size);
    }
}
