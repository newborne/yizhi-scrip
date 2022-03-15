package com.yizhi.common.model.pojo.neo4j.node;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.io.Serializable;

@Data
@Builder
@NodeEntity(label = "MaterialTypeNode")
public class MaterialTypeNode {
    @Id
    @GeneratedValue
    private Long id;
    @Property(name = "material_type")
    private Integer materialType;
    @Property(name = "material_type_name")
    private String materialTypeName;
}
