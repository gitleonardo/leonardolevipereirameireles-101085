package com.leonardo.musicapi.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Music API")
                        .description("API para cadastro de artistas e álbuns, com vínculo N:N e paginação.")
                        .version("v1")
                        .contact(new Contact()
                                .name("Leonardo")
                                .url("https://github.com/gitleonardo/leonardolevipereirameireles-101085"))
                        .license(new License().name("MIT")));
    }
}
