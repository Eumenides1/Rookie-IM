package com.rookie.stack.im.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Classname Application
 * @Description TODO
 * @Date 2024/9/27 14:12
 * @Created by liujiapeng
 */
@SpringBootApplication
@MapperScan("com.rookie.stack.im.user.domain.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
