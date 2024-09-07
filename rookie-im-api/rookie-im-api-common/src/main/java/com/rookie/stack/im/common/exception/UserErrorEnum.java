package com.rookie.stack.im.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author eumenides
 * @description
 * @date 2024/9/7
 */
@AllArgsConstructor
@Getter
public enum UserErrorEnum implements ErrorEnum {
    USER_NOT_FOUND(-1001, "用户不存在！"),
    USER_EXIST(-1002, "该用户已存在")
    ;

    private final Integer code;
    private final String msg;

    @Override
    public Integer getErrorCode() {
        return code;
    }

    @Override
    public String getErrorMsg() {
        return msg;
    }
}
