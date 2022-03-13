package com.yizhi.server.service.v1;

import com.yizhi.common.model.vo.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VideoService {
    ResponseResult saveVideo(String text, MultipartFile picFile, MultipartFile videoFile) throws IOException;
    ResponseResult queryFriendVideoList(Integer page, Integer size);
    ResponseResult queryRecommendVideoList(Integer page, Integer size);
    ResponseResult queryUserVideoList(Long userId, Integer page, Integer size);
    ResponseResult queryVideoById(String publishId);
}
