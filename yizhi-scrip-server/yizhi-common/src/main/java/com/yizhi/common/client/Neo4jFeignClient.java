package com.yizhi.common.client;

import com.yizhi.common.model.pojo.neo4j.node.ArticleNode;
import com.yizhi.common.model.vo.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "yizhi-neo4j")
@Repository
public interface Neo4jFeignClient {
    @GetMapping("api/v1/neo4j/queryRecommendArticleList/{userId}")
    List<ArticleNode> queryRecommendArticleList(@PathVariable("userId") Integer userId,
                                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                @RequestParam(value = "size", defaultValue = "10") Integer size);
}
