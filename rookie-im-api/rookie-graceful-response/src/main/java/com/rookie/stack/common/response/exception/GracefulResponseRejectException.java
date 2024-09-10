package com.rookie.stack.common.response.exception;

import com.rookie.stack.common.response.data.ResponseStatus;

/**
 * @author eumenides
 * @description 拒绝处理的异常
 * @date 2024/9/10
 */
public class GracefulResponseRejectException extends GracefulResponseException{
    public GracefulResponseRejectException() {
    }

    public GracefulResponseRejectException(String msg) {
        super(msg);
    }

    public GracefulResponseRejectException(String code, String msg) {
        super(code, msg);
    }

    public GracefulResponseRejectException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public GracefulResponseRejectException(String code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

    public GracefulResponseRejectException(ResponseStatus responseStatus, Throwable cause) {
        super(responseStatus, cause);
    }
}
