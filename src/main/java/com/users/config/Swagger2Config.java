package com.users.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration for REST end-point documentation
 * 
 * @author anilKumar
 *
 */
@Configuration
@EnableSwagger2
public class Swagger2Config		{
	
	@Bean
	public Docket swaggerApi() {
		
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.users"))
                .paths(PathSelectors.any()) // regex("/users.*")
                .build()
                .apiInfo(apiInfo());

    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "User Management Services REST API Documentation",
                "REST Services API Documentation.",
                "API 1.O",
                "",
                new Contact("Anil Kumar", "https://www.linkedin.com/in/anilkumar-padasalagi/", "anilkumar81593@gmail.com"),
                "License of API", "https://github.com/anilKumar-GitHub", Collections.emptyList());
    }

}