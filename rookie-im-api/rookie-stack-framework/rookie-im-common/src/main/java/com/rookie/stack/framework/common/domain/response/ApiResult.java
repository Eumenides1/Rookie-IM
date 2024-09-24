package com.rookie.stack.framework.common.domain.response;

import com.rookie.stack.framework.common.exception.ErrorEnum;
import lombok.Data;

/**
 * @author eumenides
 * @description
 * @date 2024/9/24
 */
@Data
public class ApiResult<T> {
    // 是否成功，默认为 true
    private Boolean success = true;
    // 响应消息
    private Integer errCode;
    // 异常码
    private String errMsg;
    // 响应数据
    private T data;
    public static <T> ApiResult<T> success() {
        return new ApiResult<>();
    }
    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> result = new ApiResult<T>();
        result.setData(data);
        return result;
    }
    public static <T> ApiResult<T> fail(Integer code, String msg) {
        ApiResult<T> result = new ApiResult<T>();
        result.setSuccess(Boolean.FALSE);
        result.setErrCode(code);
        result.setErrMsg(msg);
        return result;
    }
    public static <T> ApiResult<T> fail(ErrorEnum errorEnum) {
        ApiResult<T> result = new ApiResult<T>();
        result.setSuccess(Boolean.FALSE);
        result.setErrCode(errorEnum.getErrorCode());
        result.setErrMsg(errorEnum.getErrorMsg());
        return result;
    }
}
