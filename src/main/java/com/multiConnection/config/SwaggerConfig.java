package com.multiConnection.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Mpole")
                        .version("1.0.0")
                        .description("산업현장 작업자 안전관리 플랫폼 개발")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.multiConnection.domain"))
                .build();
    }
}
