package com.yizhi.server.controller.v1;

import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.server.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;
    @PostMapping("save")
    public ResponseResult saveMaterial(@RequestParam(value = "text") String text,
                                       @RequestParam(value = "tags") String[] tags,
                                       @RequestParam(value = "materialType") Integer materialType) {
        return this.materialService.saveMaterial(text, tags, materialType);
    }
    @GetMapping("list/{materialType}")
    public ResponseResult queryMaterialList(@PathVariable("materialType") Integer materialType,
                                            @RequestParam(value = "page", defaultValue = "1") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return this.materialService.queryMaterialList(materialType, page, size);
    }
    @GetMapping("recommend")
    public ResponseResult queryRecommendMaterialList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                     @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return this.materialService.queryRecommendMaterialList(page, size);
    }
    @GetMapping("{id}")
    public ResponseResult queryMaterialById(@PathVariable("id") String id) {
        return this.materialService.queryMaterialById(id);
    }
}
