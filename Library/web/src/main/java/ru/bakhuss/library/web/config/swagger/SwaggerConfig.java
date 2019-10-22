package ru.bakhuss.library.web.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@EnableSwagger2
@PropertySource("classpath:swagger.properties")
@Configuration
public class SwaggerConfig {
    private static final String SWAGGER_API_VERSION = "1.0";
    private static final String LICENSE_TEXT = "License";
    private static final String title = "Library REST API";
    private static final String description = "RESTful api for Library";
    @Value("${swagger.host}")
    private String host;

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .license(LICENSE_TEXT)
                .version(SWAGGER_API_VERSION)
                .build();
    }

    @Bean
    public Docket libraryApi() {
        return new Docket(SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .host(host)
                .enableUrlTemplating(false)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("ru.bakhuss.library.web"))
                .paths(PathSelectors.regex("/api.*"))
                .build();
    }
}
