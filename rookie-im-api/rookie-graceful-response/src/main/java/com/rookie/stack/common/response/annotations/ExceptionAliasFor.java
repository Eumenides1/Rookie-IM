package com.rookie.stack.common.response.annotations;

import com.rookie.stack.common.response.defaults.DefaultConstants;

import java.lang.annotation.*;

/**
 * @author eumenides
 * @description
 * @date 2024/9/10
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExceptionAliasFor {

    /**
     * 异常对应的错误码.
     *
     * @return 异常对应的错误码
     */
    String code() default DefaultConstants.DEFAULT_ERROR_CODE;

    /**
     * 异常信息.
     *
     * @return 异常对应的提示信息
     */
    String msg() default DefaultConstants.DEFAULT_ERROR_MSG;

    /**
     * 作为某些异常的别名
     * 用于指定该异常作为其他异常的别名。通过此属性，可以为多个异常类型提供统一的别名。
     * @return
     */
    Class<? extends Throwable>[] aliasFor();

    int httpStatusCode() default -1;
}
