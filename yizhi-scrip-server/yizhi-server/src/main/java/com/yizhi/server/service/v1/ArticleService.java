package com.yizhi.server.service.v1;

import com.yizhi.common.model.vo.ResponseResult;

public interface ArticleService {
    ResponseResult queryRecommendArticleList(Integer page, Integer size);
    ResponseResult queryArticleByArticleRid(String articleRid);
    ResponseResult queryArticleList(Integer articleType, Integer page, Integer size);
    ResponseResult saveArticle(String text, String title, String[] tags, Integer articleType);
}
