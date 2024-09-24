package com.rookie.stack.im.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author eumenides
 * @description 认证服务 API 入口
 * @date 2024/9/24
 */
@SpringBootApplication
@MapperScan(basePackages = "com.rookie.stack.im.auth.domain.mapper")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class,args);
    }
}
