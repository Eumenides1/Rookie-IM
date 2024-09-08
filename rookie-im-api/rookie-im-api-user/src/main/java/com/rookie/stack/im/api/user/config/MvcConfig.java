package com.rookie.stack.im.api.user.config;

import com.rookie.stack.im.common.interceptor.IMInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author eumenides
 * @description
 * @date 2024/9/7
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Resource
    private IMInterceptor imInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(imInterceptor)
                .addPathPatterns("/v1/api/**/**")
                .excludePathPatterns("/v1/api/user/login","/v1/api/user/logout","/v1/api/user/register","/v1/api/user/refreshToken",
                        "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        // 使用BCrypt加密密码
        return new BCryptPasswordEncoder();
    }
}
