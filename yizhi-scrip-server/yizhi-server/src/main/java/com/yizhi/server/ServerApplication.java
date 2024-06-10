package com.yizhi.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * The type Server application.
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.yizhi.common.model.mapper")
@ComponentScan("com.yizhi")
@EnableFeignClients("com.yizhi.common.client")
public class ServerApplication {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
