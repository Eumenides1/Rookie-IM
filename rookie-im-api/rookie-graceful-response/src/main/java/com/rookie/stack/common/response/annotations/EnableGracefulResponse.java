package com.rookie.stack.common.response.annotations;

import com.rookie.stack.common.response.config.AutoConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author eumenides
 * @description 注解启动全局结果处理的入口.
 * @date 2024/9/10
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(AutoConfig.class)
public @interface EnableGracefulResponse {
}
