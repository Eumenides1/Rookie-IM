package com.rookie.stack.im.auth.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Classname DeletedEnum
 * @Description TODO
 * @Date 2024/9/25 16:48
 * @Created by liujiapeng
 */
@Getter
@AllArgsConstructor
public enum DeletedEnum {

    YES(true),
    NO(false);

    private final Boolean value;
}
