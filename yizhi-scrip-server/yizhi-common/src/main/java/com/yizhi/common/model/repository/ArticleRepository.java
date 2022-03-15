package com.yizhi.common.model.repository;

import com.yizhi.common.model.pojo.neo4j.node.ArticleNode;
import feign.Param;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends Neo4jRepository<ArticleNode, Long> {
    ArticleNode findByArticleRid(@Param("article_rid") Long articleRid);
}
