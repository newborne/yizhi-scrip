package com.yizhi.common.client;

import com.yizhi.common.model.vo.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@FeignClient(value = "yizhi-server")
@Repository
public interface ServerFeignClient {

    @PostMapping("api/v1/user/saveUserLogo")
    ResponseResult saveUserLogo(@RequestParam MultipartFile file);

    @GetMapping("api/v1/user/saveUserInfo")
    ResponseResult saveUserInfo(Map<String, String> param);

    @PostMapping("api/v1/user/register/{id}")
    ResponseResult register(@PathVariable("id") Long userId);
}
