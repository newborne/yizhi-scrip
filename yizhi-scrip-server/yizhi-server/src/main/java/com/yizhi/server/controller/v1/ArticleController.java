package com.yizhi.server.controller.v1;

import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.server.service.v1.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @PostMapping("save")
    public ResponseResult saveArticle(@RequestParam(value = "text") String text,
                                      @RequestParam(value = "title") String title,
                                      @RequestParam(value = "tags") String[] tags,
                                      @RequestParam(value = "articleType") Integer articleType) {
        return this.articleService.saveArticle(text, title, tags, articleType);
    }
    @GetMapping("list/{articleType}")
    public ResponseResult queryArticleList(@PathVariable("articleType") Integer articleType,
                                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return this.articleService.queryArticleList(articleType, page, size);
    }
    @GetMapping("recommend")
    public ResponseResult queryRecommendArticleList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                    @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return this.articleService.queryRecommendArticleList(page, size);
    }
    @GetMapping("{articleRid}")
    public ResponseResult queryArticleByArticleRid(@PathVariable("articleRid") String articleRid) {
        return this.articleService.queryArticleByArticleRid(articleRid);
    }
}
