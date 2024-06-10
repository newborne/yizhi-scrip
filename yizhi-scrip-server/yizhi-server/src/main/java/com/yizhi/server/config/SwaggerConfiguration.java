package com.yizhi.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * The type Swagger configuration.
 */
@Configuration
@EnableSwagger2WebMvc
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {
    /**
     * Group rest api docket.
     *
     * @return the docket
     */
    @Bean(value = "serverApi")
    @Order(value = 1)
    public Docket groupRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(groupApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yizhi.server.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo groupApiInfo() {
        return new ApiInfoBuilder()
                .title("服务-API文档")
                .description("<div style='font-size:14px;color:red;'>本文档描述了服务接口定义</div>")
                .termsOfServiceUrl("http://www.newborne.com/")
                .contact("newborne@foxmail.com")
                .version("1.0")
                .build();
    }
}

