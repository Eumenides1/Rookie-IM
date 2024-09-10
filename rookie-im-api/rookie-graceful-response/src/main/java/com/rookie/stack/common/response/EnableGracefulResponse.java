package com.rookie.stack.common.response;

import java.lang.annotation.*;

/**
 * @author eumenides
 * @description
 * @date 2024/9/10
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EnableGracefulResponse {
}
