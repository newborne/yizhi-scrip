package com.yizhi.common.model.repository;

import com.yizhi.common.model.pojo.neo4j.node.ArticleTypeNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleTypeRepository extends Neo4jRepository<ArticleTypeNode, Long> {
}
