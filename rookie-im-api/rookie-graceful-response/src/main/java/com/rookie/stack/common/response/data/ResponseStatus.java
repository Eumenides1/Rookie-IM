package com.rookie.stack.common.response.data;

/**
 * @author eumenides
 * @description
 * @date 2024/9/10
 */
public interface ResponseStatus {
    /**
     * 设置响应码.
     *
     * @param code 设置的响应码.
     */
    default void setCode(String code) {

    }
    /**
     * 获得响应码.
     *
     * @return 状态码
     */
    String getCode();

    /**
     * 设置响应提示信息.
     *
     * @param msg 设置响应提示信息.
     */
    default void setMsg(String msg) {

    }

    /**
     * 获得响应信息.
     *
     * @return
     */
    String getMsg();
}
