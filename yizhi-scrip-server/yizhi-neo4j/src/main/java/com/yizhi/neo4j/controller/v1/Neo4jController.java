package com.yizhi.neo4j.controller.v1;

import com.yizhi.common.model.pojo.mongodb.Article;
import com.yizhi.common.model.pojo.mysql.ApUser;
import com.yizhi.common.model.pojo.neo4j.node.ArticleNode;
import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.neo4j.service.ArticleService;
import com.yizhi.neo4j.service.MaterialService;
import com.yizhi.neo4j.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/neo4j")
public class Neo4jController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private MaterialService materialService;
    @GetMapping("saveUser")
    public ResponseResult saveUser() {
        return this.userService.saveUser();
    }
    @GetMapping("saveUserRecommend")
    public ResponseResult saveUserRecommendRelationship() {
        return this.userService.saveUserRecommendRelationship();
    }
    @GetMapping("saveArticleType")
    public ResponseResult saveArticleType() {
        return this.articleService.saveArticleType();
    }
    @GetMapping("saveArticle")
    public ResponseResult saveArticle() {
        return this.articleService.saveArticle();
    }
    @GetMapping("saveMaterialType")
    public ResponseResult saveMaterialType() {
        return this.materialService.saveMaterialType();
    }
    @GetMapping("saveMaterial")
    public ResponseResult saveMaterial() {
        return this.materialService.saveMaterial();
    }
    @GetMapping("saveArticleRecommend")
    public ResponseResult saveArticleRecommendRelationship() {
        return this.articleService.saveArticleRecommendRelationship();
    }
    @GetMapping("saveMaterialRecommend")
    public ResponseResult saveMaterialRecommendRelationship() {
        return this.materialService.saveMaterialRecommendRelationship();
    }
    @GetMapping("queryRecommendArticleList/{userId}")
    public List<ArticleNode> queryRecommendArticleList(@PathVariable("userId") Integer userId,
                                                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return this.articleService.queryRecommendArticleList(userId, page, size);
    }
}
