package com.rookie.stack.im.gateway.auth.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
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
                        .notMatch("/auth/api/v1/loginOrRegister") // 排除登录接口
                        .notMatch("/auth/api/v1/sms/sendVerifyCode") // 排除验证码发送接口
                        .check(r -> StpUtil.checkLogin()) // 校验是否登录
                ;
            })
            // 异常处理方法：每次setAuth函数出现异常时进入
            .setError(e -> {
                // return SaResult.error(e.getMessage());
                // 手动抛出异常，抛给全局异常处理器
                if (e instanceof NotLoginException) { // 未登录异常
                    throw new NotLoginException(e.getMessage(), null, null);
                } else if (e instanceof NotPermissionException || e instanceof NotRoleException) { // 权限不足，或不具备角色，统一抛出权限不足异常
                    throw new NotPermissionException(e.getMessage());
                } else { // 其他异常，则抛出一个运行时异常
                    throw new RuntimeException(e.getMessage());
                }
            });
    }
}
