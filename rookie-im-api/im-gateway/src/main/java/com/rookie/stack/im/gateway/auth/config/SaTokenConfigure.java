package com.rookie.stack.im.gateway.auth.config;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @Classname SaTokenConfigure
 * @Description TODO
 * @Date 2024/9/26 14:29
 * @Created by liujiapeng
 */
@Configuration
public class SaTokenConfigure {

    @Bean
    public SaReactorFilter getSaReactorFilter(){
        return new SaReactorFilter()
            // 拦截地址
            .addInclude("/**")    /* 拦截全部path */
            // 鉴权方法：每次访问进入
            .setAuth(obj -> {
                // 登录校验
                SaRouter.match("/**") // 拦截所有路由
                        .notMatch("/auth/loginOrRegister") // 排除登录接口
                        .notMatch("/auth/sms/sendVerifyCode") // 排除验证码发送接口
                        .check(r -> StpUtil.checkLogin()) // 校验是否登录
                ;
            });
    }
}
