package com.yizhi.common.model.pojo.neo4j.relationship;

import com.yizhi.common.model.pojo.neo4j.node.MaterialNode;
import com.yizhi.common.model.pojo.neo4j.node.MaterialTypeNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

@Data
@Builder
@RelationshipEntity(type = "material_belong")
public class MaterialBelongRelationship {
    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    private MaterialNode start;
    @EndNode
    private MaterialTypeNode end;
    // 备注
    private String description;
}
