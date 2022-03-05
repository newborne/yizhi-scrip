package com.yizhi.server.config;

import com.yizhi.server.interceptor.OperatorInterceptor;
import com.yizhi.server.interceptor.RedisCacheInterceptor;
import com.yizhi.server.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RedisCacheInterceptor redisCacheInterceptor;
    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Autowired
    private OperatorInterceptor operatorInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注意拦截器的顺序
        registry.addInterceptor(this.tokenInterceptor).addPathPatterns("/**");
        registry.addInterceptor(this.operatorInterceptor).addPathPatterns("/**");
        registry.addInterceptor(this.redisCacheInterceptor).addPathPatterns("/**");
    }
}
