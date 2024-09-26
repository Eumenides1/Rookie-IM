package com.rookie.stack.im.gateway.enums;

import com.rookie.stack.framework.common.exception.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Classname GateErrorEnum
 * @Description TODO
 * @Date 2024/9/26 14:59
 * @Created by liujiapeng
 */
@AllArgsConstructor
@Getter
public enum GatewayErrorEnum implements ErrorEnum {
    SYSTEM_ERROR(-9002, "系统繁忙，请稍后再试"),
    UNAUTHORIZED(-9001, "权限不足"),
    NO_TOKEN(-9000, "未携带合法令牌！");

    private final Integer code;
    private final String message;

    @Override
    public Integer getErrorCode() {
        return code;
    }

    @Override
    public String getErrorMsg() {
        return message;
    }
}
