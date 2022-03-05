package com.yizhi.common.client;

import com.yizhi.common.model.vo.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@FeignClient(value = "yizhi-server")
@Repository
public interface ServerFeignClient {

    @PostMapping(value = "api/v1/user/saveUserLogo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseResult saveUserLogo(@RequestPart("logo") MultipartFile file, @RequestHeader("Authorization") String token);

    @GetMapping("api/v1/user/saveUserInfo")
    ResponseResult saveUserInfo(@RequestBody Map<String, String> param, @RequestHeader("Authorization") String token);

    @PostMapping("api/v1/user/register/{id}")
    ResponseResult register(@PathVariable("id") Long userId, @RequestHeader("Authorization") String token);
}
