package com.rookie.stack.im.friend.exception;

import com.rookie.stack.framework.common.exception.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author eumenides
 * @description
 * @date 2024/10/13
 */
@Getter
@AllArgsConstructor
public enum FriendErrorEnum implements ErrorEnum {

    CAN_NOT_ADD_YOURSELF(-5001,"你不能添加自己为好友～");

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
