package com.yizhi.dubbo.api.v1;

import com.yizhi.common.model.pojo.mongodb.Material;
import com.yizhi.common.util.IdGenerator;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Material api.
 */
@DubboService(version = "1.0.0")
public class MaterialApiImpl implements MaterialApi {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public String saveMaterial(Material material) {
        material.setId(ObjectId.get());
        material.setMaterialRid(this.idGenerator.createId("material", material.getId().toHexString()));
        return this.mongoTemplate.save(material).getId().toHexString();
    }
    @Override
    public List<Material> queryRecommendMaterialList(Long userId, Integer page, Integer size) {
        // redis命中Rid,mongodb查Material
        String key = "RECOMMEND_MATERIAL_" + userId;
        String value = this.redisTemplate.opsForValue().get(key);
        List<Material> materialList = null;
        if (StringUtils.isNotEmpty(value)) {
            //命中了数据
            String[] materialRids = StringUtils.split(value, ",");
            int startIndex = (page - 1) * size;
            if (startIndex < materialRids.length) {
                int endIndex = startIndex + size - 1;
                if (endIndex >= materialRids.length) {
                    endIndex = materialRids.length - 1;
                }
                List<Long> materialRidList = new ArrayList<>();
                for (int i = startIndex; i <= endIndex; i++) {
                    materialRidList.add(Long.valueOf(materialRids[i]));
                }
                Query query = Query.query(Criteria.where("materialRid").in(materialRidList));
                materialList = this.mongoTemplate.find(query, Material.class);
            }
        }
        return materialList;
    }
    @Override
    public Material queryMaterialById(String id) {
        return this.mongoTemplate.findById(id, Material.class);
    }
    @Override
    public Material queryMaterialByMaterialRid(Long materialRid) {
        Query query = Query.query(Criteria.where("materialRid").is(Long.valueOf(materialRid)));
        return this.mongoTemplate.findOne(query, Material.class);
    }
    @Override
    public List<Material> queryMaterialList(Integer materialType, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Query query = Query.query(Criteria.where("materialType").is(materialType)).with(pageRequest);
        return this.mongoTemplate.find(query, Material.class);
    }
}
