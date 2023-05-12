package com.gdsc.frjns.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("FRJNS API Document")
                .version("v0.0.1")
                .description("뉴진스 팬페이지 FRJNS의 API 문서입니다.")
                .contact(new Contact().name("FRJNS-Project").url("https://github.com/FRJNS/FRJNS_BACKEND"))
                .license(new License().name("MIT License").url("https://github.com/FRJNS/FRJNS_BACKEND/blob/main/LICENSE.md"));

        String authName = "JWT token";

        SecurityRequirement securityRequirement = new SecurityRequirement().addList(authName);
        Components components = new Components()
                .addSecuritySchemes(
                        authName,
                        new SecurityScheme()
                                .name(authName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("Bearer")
                                .bearerFormat("JWT")
                                .description("Access Token(JWT) 토큰을 입력해주세요.")
                );

        Server prodServer = new Server();

        prodServer.description("Production Server")
                .url("https://frjns.duckdns.org");

        Server devServer = new Server();

        devServer.description("Development Server")
                .url("http://localhost:8080");

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components)
                .servers(Arrays.asList(prodServer, devServer));
    }
}
