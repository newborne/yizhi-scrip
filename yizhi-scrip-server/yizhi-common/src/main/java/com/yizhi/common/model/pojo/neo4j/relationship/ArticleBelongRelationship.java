package com.yizhi.common.model.pojo.neo4j.relationship;

import com.yizhi.common.model.pojo.neo4j.node.ArticleNode;
import com.yizhi.common.model.pojo.neo4j.node.ArticleTypeNode;
import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

/**
 * The type Article belong relationship.
 */
@Data
@Builder
@RelationshipEntity(type = "article_belong")
public class ArticleBelongRelationship {
    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    private ArticleNode start;
    @EndNode
    private ArticleTypeNode end;
    // 备注
    private String description;
}
