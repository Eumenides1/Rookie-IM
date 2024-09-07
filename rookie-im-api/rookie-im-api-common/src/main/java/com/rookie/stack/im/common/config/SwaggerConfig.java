package com.rookie.stack.im.common.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author eumenides
 * @description
 * @date 2024/9/7
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI rookieIMOpenApi() {
        return new OpenAPI()
                .info(new Info().title("Rookie IM 分布式即时通讯 API 文档")
                        .contact(new Contact()
                                .name("Jaguarliu")
                                .email("jaguarliu831143@gmail.com")
                                .url("https://jaguarliu.me")
                                .extensions(new HashMap<String, Object>()))
                        .description("Rookie IM 分布式即时通讯 API 文档")
                        .version("v1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("项目API文档")
                        .url("/"));
    }
}
