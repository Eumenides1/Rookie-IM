package com.rookie.stack.im.auth.test;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Classname ThreadPoolTaskExecutorTests
 * @Description TODO
 * @Date 2024/9/25 15:37
 * @Created by liujiapeng
 */
@SpringBootTest
@Slf4j
public class ThreadPoolTaskExecutorTests {

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 测试线程池
     */
    @Test
    void testSubmit() {
        threadPoolTaskExecutor.submit(() -> log.info("异步线程中说: Hi Rookie IM"));
    }
}