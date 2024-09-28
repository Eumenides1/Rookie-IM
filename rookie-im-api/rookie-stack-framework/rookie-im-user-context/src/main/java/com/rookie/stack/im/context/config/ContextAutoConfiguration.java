package com.rookie.stack.im.context.config;

import com.rookie.stack.im.context.filter.UserContextFilter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author eumenides
 * @description
 * @date 2024/9/28
 */
@AutoConfiguration
public class ContextAutoConfiguration {

    @Bean
    public FilterRegistrationBean<UserContextFilter> filterFilterRegistrationBean() {
        UserContextFilter filter = new UserContextFilter();
        FilterRegistrationBean<UserContextFilter> bean = new FilterRegistrationBean<>(filter);
        return bean;
    }
}