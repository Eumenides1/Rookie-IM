package com.rookie.stack.im.user.exception;

import com.rookie.stack.framework.common.exception.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserErrorEnum implements ErrorEnum {
    USER_SYSTEM_ERROR(-4000, "出错啦，请稍后重试！")
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
