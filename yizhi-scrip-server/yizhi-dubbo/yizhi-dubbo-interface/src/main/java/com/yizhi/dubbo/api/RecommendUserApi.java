package com.yizhi.dubbo.api;

import com.yizhi.common.model.dto.PageInfoDTO;
import com.yizhi.common.model.pojo.mongodb.RecommendUser;

import java.util.List;

public interface RecommendUserApi {
    RecommendUser queryWithMaxSimilarity(Long userId);
    PageInfoDTO<RecommendUser> queryRecommendUserList(Long userId, Integer page, Integer size);
    double querySimilarity(Long friendId, Long userId);
}
