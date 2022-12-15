package com.hanghae.my_blog.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.util.Predicates;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;
import java.util.function.Predicate;

@Configuration
public class SwaggerConfig {

    private Docket testDocket(String groupName, Predicate<String> selector) {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .groupName("testApi")
                .select()
                .apis(RequestHandlerSelectors.
                        basePackage("com.hanghae.my_blog.controller"))
//                .paths(Predicate.not(PathSelectors.regex("/error.*")))
//                .paths(PathSelectors.ant("/api/**"))

                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Hanghae Blog BackEnd Server REST API")
                .description("항해 블로그 벡엔드 서버 swagger api 입니다.")
                .version("1.0.0")
                .build();
    }
}