package com.rookie.stack.im.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author eumenides
 * @description 认证服务 API 入口
 * @date 2024/9/24
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.rookie.stack")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class,args);
    }
}
