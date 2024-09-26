package com.rookie.stack.im.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eumenides
 * @description
 * @date 2024/9/25
 */
@RestController
@Slf4j
@RefreshScope
public class TestController {
    @Value(value = "${rate-limit.api.limit}")
    private Integer limit;


    @GetMapping("/test")
    public String test() {
        return "当前限流阈值为: " + limit;
    }
}
