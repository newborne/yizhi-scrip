package com.yizhi.dubbo.api;

import com.yizhi.common.model.pojo.mongodb.Article;

import java.util.List;

public interface ArticleApi {
    String saveArticle(Article article);
    List<Article> queryArticleList(Integer articleType, Integer page, Integer size);
    List<Article> queryRecommendArticleList(Long userId, Integer page, Integer size);
    Article queryArticleById(String id);
}