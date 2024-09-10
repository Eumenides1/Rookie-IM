package com.rookie.stack.common.response.defaults;

/**
 * @author eumenides
 * @description 默认的响应码和提示信息
 * @date 2024/9/10
 */
public class DefaultConstants {

    private DefaultConstants() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 默认的成功响应码
     */
    public static final String DEFAULT_SUCCESS_CODE = "0";

    /**
     * 默认的成功提示信息
     */
    public static final String DEFAULT_SUCCESS_MSG = "ok";

    /**
     * 默认的错误码
     */
    public static final String DEFAULT_ERROR_CODE = "1";

    /**
     * 默认的错误提示
     */
    public static final String DEFAULT_ERROR_MSG = "error";

}
