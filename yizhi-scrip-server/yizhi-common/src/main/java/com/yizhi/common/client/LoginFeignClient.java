package com.yizhi.common.client;

import com.yizhi.common.model.pojo.mysql.ApUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * The interface Login feign client.
 */
@FeignClient(value = "yizhi-login")
@Repository
public interface LoginFeignClient {
    /**
     * Query user by token ap user.
     *
     * @param token the token
     * @return the ap user
     */
    @GetMapping("api/v1/login/{token}")
    ApUser queryUserByToken(@PathVariable("token") String token);
}
