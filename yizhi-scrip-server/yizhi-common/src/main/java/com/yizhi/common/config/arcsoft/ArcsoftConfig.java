package com.yizhi.common.config.arcsoft;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:arcsoft.properties")
@ConfigurationProperties(prefix = "arcsoft")
@Data
public class ArcsoftConfig {
    private String appid;
    private String sdkKey;
    private String libPath;
}
