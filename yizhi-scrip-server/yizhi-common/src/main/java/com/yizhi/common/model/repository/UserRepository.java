package com.yizhi.common.model.repository;

import com.yizhi.common.model.pojo.neo4j.node.UserNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends Neo4jRepository<UserNode, Long> {
    UserNode findByUserId(@Param("user_id") Integer userId);
}
