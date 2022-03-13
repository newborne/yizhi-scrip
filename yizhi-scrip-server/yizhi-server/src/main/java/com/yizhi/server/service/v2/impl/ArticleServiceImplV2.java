package com.yizhi.server.service.v2.impl;

import com.yizhi.common.model.dto.PageInfoDTO;
import com.yizhi.common.model.pojo.mongodb.Article;
import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.common.util.UserThreadLocal;
import com.yizhi.dubbo.api.v1.ArticleApi;
import com.yizhi.server.service.v1.ArticleService;
import com.yizhi.server.service.v2.ArticleServiceV2;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImplV2 implements ArticleServiceV2 {
    @DubboReference(version = "2.0.0")
    private ArticleApi articleApi;
    @Override
    public ResponseResult queryArticleList(Integer articleType, Integer page, Integer size) {
        // return ResponseResult.ok(new PageInfoDTO<>(0,
        //         page,
        //         size,
        //         this.articleApi.queryArticleList(articleType, page, size)));
        return ResponseResult.fail();
    }
    @Override
    public ResponseResult saveArticle(String text, String title, String[] tags, Integer articleType) {
        // Article article = new Article();
        // article.setText(text);
        // article.setTitle(title);
        // article.setTags(tags);
        // article.setArticleType(articleType);
        // return ResponseResult.ok(this.articleApi.saveArticle(article));
        return ResponseResult.fail();
    }
    @Override
    public ResponseResult queryRecommendArticleList(Integer page, Integer size) {
        return ResponseResult.ok(new PageInfoDTO<>(0, page, size, this.articleApi.queryRecommendArticleList(
                Long.valueOf(UserThreadLocal.get().getId()), page, size)));
    }
    @Override
    public ResponseResult queryArticleById(String id) {
        // return ResponseResult.ok(this.articleApi.queryArticleById(id));
        return ResponseResult.fail();
    }
}
