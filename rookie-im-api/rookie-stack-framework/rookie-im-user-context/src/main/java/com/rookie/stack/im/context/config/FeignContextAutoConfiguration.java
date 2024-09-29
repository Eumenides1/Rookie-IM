package com.rookie.stack.im.context.config;

import com.rookie.stack.im.context.interceptor.FeignRequestInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @Classname FeignContextAutoConfiguration
 * @Description TODO
 * @Date 2024/9/29 10:13
 * @Created by liujiapeng
 */
@AutoConfiguration
public class FeignContextAutoConfiguration {

    @Bean
    public FeignRequestInterceptor feignRequestInterceptor() {
        return new FeignRequestInterceptor();
    }
}