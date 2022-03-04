package com.yizhi.common.client;

import com.yizhi.common.model.pojo.mysql.ApUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "yizhi-login")
@Repository
public interface LoginFeignClient {
    @GetMapping("api/v1/login/{token}")
    ApUser queryUserByToken(@PathVariable("token") String token);
}
