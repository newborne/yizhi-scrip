package com.yizhi.server.service.v2;

import com.yizhi.common.model.vo.ResponseResult;

public interface ArticleServiceV2 {
    ResponseResult queryRecommendArticleList(Integer page, Integer size);
    ResponseResult queryArticleById(String id);
    ResponseResult queryArticleList(Integer articleType, Integer page, Integer size);
    ResponseResult saveArticle(String text, String title, String[] tags, Integer articleType);
}
