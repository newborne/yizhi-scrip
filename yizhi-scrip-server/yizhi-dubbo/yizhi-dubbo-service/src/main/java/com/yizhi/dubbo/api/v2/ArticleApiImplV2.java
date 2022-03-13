package com.yizhi.dubbo.api.v2;

import com.yizhi.common.model.pojo.mongodb.Article;
import com.yizhi.common.util.IdGenerator;
import com.yizhi.dubbo.api.v1.ArticleApi;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@DubboService(version = "2.0.0")
public class ArticleApiImplV2 implements ArticleApi {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public String saveArticle(Article article) {
        article.setId(ObjectId.get());
        article.setArticleRid(this.idGenerator.createId("article", article.getId().toHexString()));
        return this.mongoTemplate.save(article).getId().toHexString();
    }
    @Override
    public List<Article> queryArticleList(Integer articleType, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Query query = Query.query(Criteria.where("articleType").is(articleType)).with(pageRequest);
        return this.mongoTemplate.find(query, Article.class);
    }
    @Override
    public List<Article> queryRecommendArticleList(Long userId, Integer page, Integer size) {
        // redis命中Rid,mongodb查Article
        String key = "RECOMMEND_ARTICLE_" + userId;
        String value = this.redisTemplate.opsForValue().get(key);
        List<Article> articleList = null;
        if (StringUtils.isNotEmpty(value)) {
            //命中了数据
            String[] articleRids = StringUtils.split(value, ",");
            int startIndex = (page - 1) * size;
            if (startIndex < articleRids.length) {
                int endIndex = startIndex + size - 1;
                if (endIndex >= articleRids.length) {
                    endIndex = articleRids.length - 1;
                }
                List<Long> articleRidList = new ArrayList<>();
                for (int i = startIndex; i <= endIndex; i++) {
                    articleRidList.add(Long.valueOf(articleRids[i]));
                }
                Query query = Query.query(Criteria.where("articleRid").in(articleRidList));
                articleList = this.mongoTemplate.find(query, Article.class);
            }
        }
        return articleList;
    }
    @Override
    public Article queryArticleById(String id) {
        return this.mongoTemplate.findById(id, Article.class);
    }
}
