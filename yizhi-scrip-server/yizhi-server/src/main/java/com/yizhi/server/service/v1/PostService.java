package com.yizhi.server.service.v1;

import com.yizhi.common.model.vo.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    ResponseResult savePost(String text,
                            String location,
                            String longitude,
                            String latitude,
                            MultipartFile[] multipartFile);
    ResponseResult queryFriendPostList(Integer page, Integer size);
    ResponseResult queryRecommendPostList(Integer page, Integer size);
    ResponseResult queryUserPostList(Long userId, Integer page, Integer size);
    ResponseResult queryPostById(String publishId);
    // ResponseResult queryRecommendPostList(Integer page, Integer size);
}
