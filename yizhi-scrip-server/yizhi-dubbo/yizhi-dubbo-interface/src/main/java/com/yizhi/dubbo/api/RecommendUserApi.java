package com.yizhi.dubbo.api;

import com.yizhi.common.model.pojo.mongodb.RecommendUser;

import java.util.List;

public interface RecommendUserApi {

    RecommendUser queryWithMaxSimilarity(Long userId);

    List<RecommendUser> queryRecommendUserList(Long userId, Integer page, Integer size);

    double querySimilarity(Long friendId, Long userId);
}
