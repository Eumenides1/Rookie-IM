package com.rookie.stack.im.api.auth.test;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author eumenides
 * @description
 * @date 2024/9/23
 */
@SpringBootTest
@Slf4j
public class RedisTest {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * set key value
     */
    @Test
    void testSetKeyValue() {
        // 添加一个 key 为 name, value 值为 犬小哈
        redisTemplate.opsForValue().set("name", "jaguarliu");
    }
}
