package com.backbase.kalah.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Configuration class to enable Swagger Documentation for Kalah API
 *
 * @author Chandra Ranjan
 * @since 05-12-2020
 */

@Configuration
@EnableSwagger2
public class KalahSwagger {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.backbase.kalah.controller"))
                .paths(PathSelectors.any()).build().apiInfo(getServiceDetail());
    }

    private ApiInfo getServiceDetail() {
        return new ApiInfo(
                "Backbase Kalah Game",
                "APIs for the creation and play Kalah Game",
                "1.0.0",
                null,
                null,
                null,
                null,
                Collections.emptyList()
        );
    }
}
