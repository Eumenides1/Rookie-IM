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
public enum HttpErrorEnum implements ErrorEnum{
    SUCCESS(200,"成功"),
    NO_LOGIN(400,"未登录"),
    INVALID_TOKEN(401,"token无效或已过期"),
    PARAMS_ERROR(402,"参数错误"),
    PROGRAM_ERROR(500,"系统繁忙，请稍后再试"),
    PASSWOR_ERROR(10001,"密码不正确"),
    USERNAME_ALREADY_REGISTER(10003,"该用户名已注册"),
    XSS_PARAM_ERROR(10004,"请不要输入非法内容");
    ;
    private Integer code;
    private String msg;

    @Override
    public Integer getErrorCode() {
        return code;
    }

    @Override
    public String getErrorMsg() {
        return msg;
    }
}
