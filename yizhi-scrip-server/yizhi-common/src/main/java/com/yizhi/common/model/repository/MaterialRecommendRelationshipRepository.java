package com.yizhi.common.model.repository;

import com.yizhi.common.model.pojo.neo4j.relationship.MaterialRecommendRelationship;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRecommendRelationshipRepository extends Neo4jRepository<MaterialRecommendRelationship, Long> {
}
