package com.yizhi.server.service.v1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The type Article service test.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;
    /**
     * Save article.
     */
    @Test
    public void saveArticle() {
        String text = "测试文章";
        String title = "测试文章标题";
        String[] tags = {"测试", "文章"};
        int articleType = 10001;
        this.articleService.saveArticle(text, title, tags, articleType);
    }
}