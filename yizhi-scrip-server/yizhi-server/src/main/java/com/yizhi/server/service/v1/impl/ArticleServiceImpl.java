package com.yizhi.server.service.v1.impl;

import com.yizhi.common.model.dto.PageInfoDTO;
import com.yizhi.common.model.pojo.mongodb.Article;
import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.common.util.UserThreadLocal;
import com.yizhi.dubbo.api.v1.ArticleApi;
import com.yizhi.server.service.v1.ArticleService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @DubboReference(version = "1.0.0")
    private ArticleApi articleApi;
    @Override
    public ResponseResult queryArticleList(Integer articleType, Integer page, Integer size) {
        return ResponseResult.ok(new PageInfoDTO<>(0,
                page,
                size,
                this.articleApi.queryArticleList(articleType, page, size)));
    }
    @Override
    public ResponseResult saveArticle(String text, String title, String[] tags, Integer articleType) {
        Article article = new Article();
        article.setText(text);
        article.setTitle(title);
        article.setTags(tags);
        article.setArticleType(articleType);
        return (this.articleApi.saveArticle(article) != null) ? ResponseResult.ok() : ResponseResult.fail();
    }
    @Override
    public ResponseResult queryRecommendArticleList(Integer page, Integer size) {
        return ResponseResult.ok(new PageInfoDTO<>(0, page, size, this.articleApi.queryRecommendArticleList(
                Long.valueOf(UserThreadLocal.get().getId()), page, size)));
    }
    @Override
    public ResponseResult queryArticleById(String id) {
        return ResponseResult.ok(this.articleApi.queryArticleById(id));
    }
}
