package com.yizhi.common.model.repository;

import com.yizhi.common.model.pojo.neo4j.relationship.ArticleBelongRelationship;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Article belong relationship repository.
 */
@Repository
public interface ArticleBelongRelationshipRepository extends Neo4jRepository<ArticleBelongRelationship, Long> {
}
