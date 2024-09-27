package com.rookie.stack.im.user.exception;

import com.rookie.stack.framework.common.exception.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Classname UserParamsErrorEnum
 * @Description 用户参数异常枚举
 * @Date 2024/9/27 15:45
 * @Created by liujiapeng
 */
@AllArgsConstructor
@Getter
public enum UserParamsErrorEnum implements ErrorEnum {

    NICK_NAME_VALID_FAIL(-4100, "昵称请设置2-24个字符，不能使用@《/等特殊字符"),
    SEX_VALID_FAIL(-4101, "性别错误"),
    INTRODUCTION_VALID_FAIL(-4102, "个人简介请设置1-100个字符"),
    ;

    private Integer code;
    private String message;

    @Override
    public Integer getErrorCode() {
        return code;
    }

    @Override
    public String getErrorMsg() {
        return message;
    }
}
