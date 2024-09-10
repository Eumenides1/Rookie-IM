package com.rookie.stack.common.response.annotations;

import java.lang.annotation.*;

/**
 * @author eumenides
 * @description
 * @date 2024/9/10
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcludeFromGracefulResponse {
}
