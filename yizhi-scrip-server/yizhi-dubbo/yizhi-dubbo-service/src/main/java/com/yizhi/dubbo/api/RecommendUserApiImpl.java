package com.yizhi.dubbo.api;

import com.yizhi.common.model.pojo.mongodb.RecommendUser;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

// alibaba.dubbo
@DubboService(version = "1.0.0")
public class RecommendUserApiImpl implements RecommendUserApi {

    //注入mongodb
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public RecommendUser queryWithMaxSimilarity(Long userId) {
        Query query = Query.query(Criteria.where("userId").is(userId)).with(Sort.by(Sort.Order.desc("similarity"))).limit(1);
        return mongoTemplate.findOne(query, RecommendUser.class);
    }

    @Override
    public List<RecommendUser> queryRecommendUserList(Long userId, Integer page, Integer size) {
        //先进行分页
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Order.desc("similarity")));
        //查询到最高分然后倒序
        Query query = Query.query(Criteria.where("userId").is(userId)).with(pageRequest);
        List<RecommendUser> recommendUserList = mongoTemplate.find(query, RecommendUser.class);
        //暂时不提供总数
        return recommendUserList;
    }

    @Override
    public double querySimilarity(Long friendId, Long userId) {
        Query query = Query.query(Criteria
                .where("userId").is(userId)
                .and("friendId").is(friendId));
        RecommendUser recommendUser = this.mongoTemplate.findOne(query, RecommendUser.class);
        if (null == recommendUser) {
            return 0;
        }
        return recommendUser.getSimilarity();
    }
}
