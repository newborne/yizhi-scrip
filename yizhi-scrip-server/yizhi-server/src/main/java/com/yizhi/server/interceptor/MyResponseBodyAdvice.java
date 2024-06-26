package com.yizhi.server.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yizhi.server.annotation.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.concurrent.TimeUnit;

/**
 * The type My response body advice.
 */
@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Value("${yizhi.cache.enable}")
    private Boolean enable;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // 开关处于开启状态  是get请求  包含了@Cache注解
        return enable && returnType.hasMethodAnnotation(GetMapping.class)
                && returnType.hasMethodAnnotation(Cache.class);
    }
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (null == body) {
            return null;
        }
        try {
            String redisValue = null;
            if (body instanceof String) {
                redisValue = (String) body;
            } else {
                redisValue = MAPPER.writeValueAsString(body);
            }
            String redisKey = RedisCacheInterceptor.createRedisKey(((ServletServerHttpRequest) request).getServletRequest());
            Cache cache = returnType.getMethodAnnotation(Cache.class);
            //缓存的时间单位是秒
            this.redisTemplate.opsForValue().set(redisKey, redisValue, Long.parseLong(cache.time()), TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }
}

