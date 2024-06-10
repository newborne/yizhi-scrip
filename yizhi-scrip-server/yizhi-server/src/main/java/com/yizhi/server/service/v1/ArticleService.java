package com.yizhi.server.service.v1;

import com.yizhi.common.model.vo.ResponseResult;

/**
 * The interface Article service.
 */
public interface ArticleService {
    /**
     * Query recommend article list response result.
     *
     * @param page the page
     * @param size the size
     * @return the response result
     */
    ResponseResult queryRecommendArticleList(Integer page, Integer size);
    /**
     * Query article by article rid response result.
     *
     * @param articleRid the article rid
     * @return the response result
     */
    ResponseResult queryArticleByArticleRid(String articleRid);
    /**
     * Query article list response result.
     *
     * @param articleType the article type
     * @param page        the page
     * @param size        the size
     * @return the response result
     */
    ResponseResult queryArticleList(Integer articleType, Integer page, Integer size);
    /**
     * Save article response result.
     *
     * @param text        the text
     * @param title       the title
     * @param tags        the tags
     * @param articleType the article type
     * @return the response result
     */
    ResponseResult saveArticle(String text, String title, String[] tags, Integer articleType);
}
