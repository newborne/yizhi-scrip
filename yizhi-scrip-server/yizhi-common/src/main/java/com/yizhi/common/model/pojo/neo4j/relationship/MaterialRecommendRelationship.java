package com.yizhi.common.model.pojo.neo4j.relationship;

import com.yizhi.common.model.pojo.neo4j.node.MaterialNode;
import com.yizhi.common.model.pojo.neo4j.node.MaterialTypeNode;
import com.yizhi.common.model.pojo.neo4j.node.UserNode;
import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@Builder
@RelationshipEntity(type = "material_recommend")
public class MaterialRecommendRelationship {
    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    private MaterialNode start;
    @EndNode
    private UserNode end;
    // 备注
    private String description;
}
