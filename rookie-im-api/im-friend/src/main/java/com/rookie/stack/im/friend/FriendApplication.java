package com.rookie.stack.im.friend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Classname FriendApplication
 * @Description TODO
 * @Date 2024/9/29 14:08
 * @Created by liujiapeng
 */
@SpringBootApplication
@MapperScan(basePackages = "com.rookie.stack.im.friend.domain.mapper")
public class FriendApplication {
    public static void main(String[] args) {
        SpringApplication.run(FriendApplication.class, args);
    }
}
