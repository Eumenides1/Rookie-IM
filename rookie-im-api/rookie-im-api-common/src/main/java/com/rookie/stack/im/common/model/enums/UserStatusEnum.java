package com.rookie.stack.im.common.model.enums;

/**
 * @author eumenides
 * @description
 * @date 2024/9/7
 */
public enum UserStatusEnum {
    NORMAL(0, "正常"),
    DISABLED(-1, "被禁用");
    private final Integer code;
    private final String description;
    UserStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
    public Integer getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }

}
