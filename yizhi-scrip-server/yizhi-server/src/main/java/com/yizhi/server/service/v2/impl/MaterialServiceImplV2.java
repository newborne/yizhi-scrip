package com.yizhi.server.service.v2.impl;

import com.yizhi.common.model.dto.MaterialDTO;
import com.yizhi.common.model.dto.PageInfoDTO;
import com.yizhi.common.model.pojo.mongodb.Material;
import com.yizhi.common.model.pojo.mysql.ApUser;
import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.common.util.UserThreadLocal;
import com.yizhi.dubbo.api.v1.MaterialApi;
import com.yizhi.server.service.v2.MaterialServiceV2;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Material service impl v 2.
 */
@Service
public class MaterialServiceImplV2 implements MaterialServiceV2 {
    @DubboReference(version = "2.0.0")
    private MaterialApi materialApi;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public ResponseResult queryRecommendMaterialList(Integer page, Integer size) {
        List<Material> materialList = this.materialApi.queryRecommendMaterialList(
                Long.valueOf(UserThreadLocal.get().getId()), page, size);
        List<MaterialDTO> dtos = new ArrayList<>();
        for (Material material : materialList) {
            dtos.add(this.fillValueToMaterial(material));
        }
        return ResponseResult.ok(new PageInfoDTO<>(0, page, size, dtos));
    }
    @Override
    public ResponseResult queryMaterialByMaterialRid(Long materialRid) {
        return ResponseResult.ok(this.fillValueToMaterial(this.materialApi.queryMaterialByMaterialRid(materialRid)));
    }
    private MaterialDTO fillValueToMaterial(Material material) {
        MaterialDTO dto = new MaterialDTO();
        dto.setId(String.valueOf(material.getId()));
        dto.setMaterialRid(String.valueOf(material.getMaterialRid()));
        dto.setText(material.getText());
        dto.setTags(material.getTags());
        // 填充评论相关
        ApUser user = UserThreadLocal.get();
        // 是否喜欢
        String loveUserCommentKey = "COMMENT_LOVE_USER_" + user.getId() + "_" + dto.getId();
        dto.setHasLoved(this.redisTemplate.hasKey(loveUserCommentKey) ? 1 : 0);
        String loveCommentKey = "COMMENT_LOVE_" + dto.getId();
        String loveValue = this.redisTemplate.opsForValue().get(loveCommentKey);
        if (StringUtils.isNotEmpty(loveValue)) {
            dto.setLoveCount(Integer.valueOf(loveValue));
        } else {
            dto.setLoveCount(0);
        }
        return dto;
    }
}
