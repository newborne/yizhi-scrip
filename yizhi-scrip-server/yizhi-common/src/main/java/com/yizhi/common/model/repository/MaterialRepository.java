package com.yizhi.common.model.repository;

import com.yizhi.common.model.pojo.neo4j.node.ArticleNode;
import com.yizhi.common.model.pojo.neo4j.node.MaterialNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends Neo4jRepository<MaterialNode, Long> {
    MaterialNode findByMaterialRid(@Param("material_rid") Long materialRid);
}
