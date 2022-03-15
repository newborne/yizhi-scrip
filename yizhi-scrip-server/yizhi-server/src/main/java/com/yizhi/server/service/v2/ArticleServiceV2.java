package com.yizhi.server.service.v2;

import com.yizhi.common.model.pojo.mongodb.Article;
import com.yizhi.common.model.vo.ResponseResult;

public interface ArticleServiceV2 {
    ResponseResult queryRecommendArticleList(Integer page, Integer size);
    ResponseResult queryArticleByArticleRid(String articleRid);
}
