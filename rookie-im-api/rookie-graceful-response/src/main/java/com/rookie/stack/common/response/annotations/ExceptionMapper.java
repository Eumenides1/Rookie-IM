package com.rookie.stack.common.response.annotations;

/**
 * @author eumenides
 * @description
 * @date 2024/9/10
 */
public @interface ExceptionMapper {
    /**
     * 异常对应的错误码.
     *
     * @return 异常对应的错误码
     */
    String code() default "ERROR";

    /**
     * 异常信息.
     *
     * @return 异常对应的提示信息
     */
    String msg() default "Poor network quality!";

    /**
     * 异常信息是否支持替换
     * 仅当msgReplaceable==ture，且异常实例的message不为空时才能替换
     *
     * @return
     */
    boolean msgReplaceable() default false;
}
