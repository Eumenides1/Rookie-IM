package com.rookie.stack.common.response.defaults;

import com.rookie.stack.common.response.api.ResponseStatusFactory;
import com.rookie.stack.common.response.config.GracefulResponseProperties;
import com.rookie.stack.common.response.data.ResponseStatus;
import jakarta.annotation.Resource;

/**
 * @author eumenides
 * @description 默认的DefaultResponseStatusFactory实现.
 * @date 2024/9/10
 */
public class DefaultResponseStatusFactory implements ResponseStatusFactory {
    @Resource
    private GracefulResponseProperties properties;

    @Override
    public ResponseStatus defaultSuccess() {
        DefaultResponseStatus defaultResponseStatus = new DefaultResponseStatus();
        defaultResponseStatus.setCode(properties.getDefaultSuccessCode());
        defaultResponseStatus.setMsg(properties.getDefaultSuccessMsg());
        return defaultResponseStatus;
    }

    @Override
    public ResponseStatus defaultError() {
        DefaultResponseStatus defaultResponseStatus = new DefaultResponseStatus();
        defaultResponseStatus.setCode(properties.getDefaultErrorCode());
        defaultResponseStatus.setMsg(properties.getDefaultErrorMsg());
        return defaultResponseStatus;
    }

    @Override
    public ResponseStatus newInstance(String code, String msg) {
        return new DefaultResponseStatus(code, msg);
    }
}
