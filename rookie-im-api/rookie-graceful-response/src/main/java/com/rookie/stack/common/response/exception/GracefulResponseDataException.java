package com.rookie.stack.common.response.exception;

/**
 * @author eumenides
 * @description 带数据的异常响应
 * @date 2024/9/10
 */
public class GracefulResponseDataException extends GracefulResponseException{
    /**
     * 响应数据
     */
    private Object data;

    public GracefulResponseDataException() {
    }

    public GracefulResponseDataException(Object data) {
        this.data = data;
    }

    public GracefulResponseDataException(String msg, Object data) {
        super(msg);
        this.data = data;
    }

    public GracefulResponseDataException(String code, String msg, Object data) {
        super(code, msg);
        this.data = data;
    }

    public GracefulResponseDataException(String msg, Throwable cause, Object data) {
        super(msg, cause);
        this.data = data;
    }

    public GracefulResponseDataException(String code, String msg, Throwable cause, Object data) {
        super(code, msg, cause);
        this.data = data;
    }

    public Object getData() {
        return this.data;
    }
}
