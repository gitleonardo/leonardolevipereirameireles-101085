package com.leonardo.musicapi.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info(new Info()
                        .title("Music API")
                        .description("API para cadastro de artistas e álbuns, com vínculo N:N, paginação e autenticação JWT.")
                        .version("v1")
                        .contact(new Contact()
                                .name("Leonardo")
                                .url("https://github.com/gitleonardo/leonardolevipereirameireles-101085"))
                        .license(new License().name("MIT")));
    }
}
