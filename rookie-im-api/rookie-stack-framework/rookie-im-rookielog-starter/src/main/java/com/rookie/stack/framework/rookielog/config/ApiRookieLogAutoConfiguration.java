package com.rookie.stack.framework.rookielog.config;

import com.rookie.stack.framework.rookielog.aspect.ApiRookieLogAspect;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author eumenides
 * @description
 * @date 2024/9/24
 */
@AutoConfiguration
public class ApiRookieLogAutoConfiguration {

    @Bean
    public ApiRookieLogAspect apiRookieLogAspect() {
        return new ApiRookieLogAspect();
    }

}
