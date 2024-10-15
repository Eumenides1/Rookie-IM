package com.rookie.stack.im.gateway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author eumenides
 * @description
 * @date 2024/10/15
 */
@Configuration
public class Knife4jConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("菜鸟 IM API 文档")
                        .description("分布式微服务即时通讯系统-菜鸟 IM")
                        .version("V1.0.0")
                        .contact(new Contact().name("Jaguarliu").email("18829526908@163.com"))
                );
    }
}
