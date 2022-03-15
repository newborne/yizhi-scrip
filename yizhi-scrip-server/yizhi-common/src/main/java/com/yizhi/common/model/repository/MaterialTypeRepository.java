package com.yizhi.common.model.repository;

import com.yizhi.common.model.pojo.neo4j.node.ArticleNode;
import com.yizhi.common.model.pojo.neo4j.node.MaterialTypeNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialTypeRepository extends Neo4jRepository<MaterialTypeNode, Long> {
}
