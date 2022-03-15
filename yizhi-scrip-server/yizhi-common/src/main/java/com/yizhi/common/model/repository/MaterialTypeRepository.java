package com.yizhi.common.model.repository;

import com.yizhi.common.model.pojo.neo4j.node.MaterialTypeNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Material type repository.
 */
@Repository
public interface MaterialTypeRepository extends Neo4jRepository<MaterialTypeNode, Long> {
}
