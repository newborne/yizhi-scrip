package com.yizhi.server.controller.v2;

import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.server.service.v1.ArticleService;
import com.yizhi.server.service.v2.ArticleServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2/article")
public class ArticleControllerV2 {
    @Autowired
    private ArticleServiceV2 articleService;
    @PostMapping("save")
    public ResponseResult saveArticle(@RequestParam(value = "text") String text,
                                      @RequestParam(value = "title") String title,
                                      @RequestParam(value = "tags") String[] tags,
                                      @RequestParam(value = "articleType") Integer articleType) {
        return this.articleService.saveArticle(text, title, tags, articleType);
    }
    @GetMapping("recommend")
    public ResponseResult queryRecommendArticleList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                    @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return this.articleService.queryRecommendArticleList(page, size);
    }
}
