package com.rookie.stack.framework.common.constant;

public interface RedisKeyConstants {
    /**
     * 验证码 KEY 前缀
     */
    static final String VERIFICATION_CODE_KEY_PREFIX = "verification_code:";

    /**
     * 构建验证码 KEY
     * @param phone
     * @return
     */
    public static String buildVerificationCodeKey(String phone) {
        return VERIFICATION_CODE_KEY_PREFIX + phone;
    }
}
