package com.rookie.stack.im.api.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author eumenides
 * @description
 * @date 2024/9/7
 */
@SpringBootApplication(exclude= {SecurityAutoConfiguration.class })// 禁用secrity
@MapperScan(basePackages = "com.rookie.stack.im.api.user.repository")
@ComponentScan(basePackages = {"com.rookie.stack"})
public class UserApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApiApplication.class, args);
    }
}
