package com.yizhi.common.model.repository;

import com.yizhi.common.model.pojo.neo4j.relationship.MaterialBelongRelationship;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Material belong relationship repository.
 */
@Repository
public interface MaterialBelongRelationshipRepository extends Neo4jRepository<MaterialBelongRelationship, Long> {
}
