package com.yizhi.server.service;

import com.yizhi.common.model.dto.UserInfoDTO;
import com.yizhi.common.model.request.RecommendUserRequest;
import com.yizhi.common.model.vo.PicUploadResult;
import com.yizhi.common.model.vo.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UsersService {
    ResponseResult user();
    ResponseResult register(Long userId);
    ResponseResult sendMsg(String target, String msg, String type);
    ResponseResult saveUserInfo(Map<String, String> param);
    ResponseResult saveUserLogo(MultipartFile file);
    ResponseResult queryUserInfo(Long userId);
    ResponseResult updateUserInfo(Map<String, String> param);
    ResponseResult addUsers(Long friendId);
    ResponseResult queryUsersList(Integer page, Integer size, String keyword);
    ResponseResult queryFollowCounts();
    ResponseResult queryFollowList(String type, Integer page, Integer size);
    ResponseResult follow(Long userId);
    ResponseResult unFollow(Long userId);
    ResponseResult queryTodayBest();
    ResponseResult queryRecommendUser(Long friendId);
    ResponseResult queryRecommendUserList(RecommendUserRequest param);
    ResponseResult queryNearUser(String sex, String distance);
    ResponseResult updateLocation(Map<String, Object> param);
}
