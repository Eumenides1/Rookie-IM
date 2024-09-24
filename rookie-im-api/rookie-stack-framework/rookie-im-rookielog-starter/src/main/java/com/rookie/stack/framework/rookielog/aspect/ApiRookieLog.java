package com.rookie.stack.framework.rookielog.aspect;

import java.lang.annotation.*;

/**
 * @author eumenides
 * @description
 * @date 2024/9/24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ApiRookieLog {
    /**
     * API 功能描述
     *
     * @return
     */
    String description() default "";
}
