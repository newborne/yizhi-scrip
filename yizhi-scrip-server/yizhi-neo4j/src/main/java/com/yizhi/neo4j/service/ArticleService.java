package com.yizhi.neo4j.service;

import com.yizhi.common.model.pojo.mongodb.Article;
import com.yizhi.common.model.pojo.neo4j.node.ArticleNode;
import com.yizhi.common.model.vo.ResponseResult;

import java.util.List;

public interface ArticleService {
    ResponseResult saveArticleType();
    ResponseResult saveArticle();
    ResponseResult saveArticleRecommendRelationship();
    List<ArticleNode> queryRecommendArticleList(Integer userId, Integer page, Integer size);
}
