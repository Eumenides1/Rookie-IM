package com.rookie.stack.im.auth.common.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author eumenides
 * @description
 * @date 2024/9/27
 */
@Constraint(validatedBy = PasswordsMatchValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordsMatch {
    String message() default "密码不匹配";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
