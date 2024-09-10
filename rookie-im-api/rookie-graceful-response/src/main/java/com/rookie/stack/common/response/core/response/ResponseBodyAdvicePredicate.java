package com.rookie.stack.common.response.core.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * @author eumenides
 * @description
 * @date 2024/9/10
 */
public interface ResponseBodyAdvicePredicate {

    /**
     * 判断是否需要处理
     *
     * @param methodParameter
     * @param clazz
     * @return
     */
    default boolean shouldApplyTo(MethodParameter methodParameter,
                                  Class<? extends HttpMessageConverter<?>> clazz) {
        return true;
    }
}
