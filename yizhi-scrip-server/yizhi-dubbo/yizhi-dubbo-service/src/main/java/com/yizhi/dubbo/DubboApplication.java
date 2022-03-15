package com.yizhi.dubbo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * The type Dubbo application.
 */
@MapperScan("com.yizhi.common.model.mapper")
@ComponentScan("com.yizhi")
@SpringBootApplication
@EnableFeignClients("com.yizhi.common.client")
public class DubboApplication {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(DubboApplication.class, args);
    }
}

