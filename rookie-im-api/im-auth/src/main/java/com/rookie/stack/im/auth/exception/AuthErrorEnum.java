package com.rookie.stack.im.auth.exception;

import com.rookie.stack.framework.common.exception.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthErrorEnum implements ErrorEnum {
    FORBIDDEN(-3000, "访问被拒绝！"),
    UNAUTHORIZED(-3001,"未授权的请求，请提交有效身份信息"),
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
