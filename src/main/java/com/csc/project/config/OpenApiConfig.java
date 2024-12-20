package com.csc.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("CSC Application API Specifications")
                    .version("1.0.0")
                    .description("This document provides detailed information on the CSC application's API and its schemas."))
//                    .addSecurityItem(new SecurityRequirement().addList("JWT"))
                .components(new io.swagger.v3.oas.models.Components());
////                    .addSecuritySchemes("JWT", new SecurityScheme()
//                        .type(Type.HTTP)
//                        .scheme("bearer")
//                        .bearerFormat("JWT")
//                        .in(In.HEADER)
//                        .name("Authorization")));
    }
}
