package com.rookie.stack.im.auth.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @Classname LoginTypeEnum
 * @Description 用户状态类型枚举
 * @Date 2024/9/25 16:24
 * @Created by liujiapeng
 */
@Getter
@AllArgsConstructor
public enum UserStatusEnum {
    // 启用
    ENABLE(0),
    // 禁用
    DISABLE(1);

    private final Integer value;

    public static UserStatusEnum valueOf(Integer code) {
        for (UserStatusEnum loginTypeEnum : UserStatusEnum.values()) {
            if (Objects.equals(code, loginTypeEnum.getValue())) {
                return loginTypeEnum;
            }
        }
        return null;
    }
}
