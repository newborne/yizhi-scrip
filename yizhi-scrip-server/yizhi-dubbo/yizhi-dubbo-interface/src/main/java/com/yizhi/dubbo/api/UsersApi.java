package com.yizhi.dubbo.api;

import com.yizhi.common.model.dto.FollowCountDTO;
import com.yizhi.common.model.dto.PageInfoDTO;
import com.yizhi.common.model.dto.UserLocationDTO;
import com.yizhi.common.model.pojo.mongodb.FollowUser;
import com.yizhi.common.model.pojo.mongodb.RecommendUser;
import com.yizhi.common.model.pojo.mongodb.Users;

import java.util.List;

public interface UsersApi {
    // 好友
    String saveUsers(Users users);
    List<Users> queryUsersList(Long userId);
    List<Users> queryUsersList(Long userId, Integer page, Integer size);
    // 关注
    String followUser(Long userId, Long friendId);
    Boolean isMutualFollow(Long userId, Long friendId);
    Boolean deleteFollowUser(Long userId, Long friendId);
    List<FollowUser> queryMutualFollowList(Long userId, Integer page, Integer size);
    List<FollowUser> queryFollowList(Long userId, Integer page, Integer size);
    List<FollowUser> queryFansList(Long userId, Integer page, Integer size);
    FollowCountDTO queryFollowCounts(Long userId);
    // 最佳推荐
    RecommendUser queryWithMaxSimilarity(Long userId);
    PageInfoDTO<RecommendUser> queryRecommendUserList(Long userId, Integer page, Integer size);
    Double querySimilarity(Long friendId, Long userId);
    // 地理位置
    Boolean updateLocation(Long userId, Double longitude, Double latitude, String address);
    UserLocationDTO queryLocationByUserId(Long userId);
    List<UserLocationDTO> queryUserFromLocation(Double longitude, Double latitude, Integer range);
}
