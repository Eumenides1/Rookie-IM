package com.rookie.stack.im.common.model.response;

import com.rookie.stack.im.common.exception.ErrorEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author eumenides
 * @description
 * @date 2024/9/7
 */
@Data
@Schema(description = "API响应返回数据对象")
public class ApiResult<T>{
    @Schema(
            title = "success",
            description = "成功标识true or false",
            format = "boolean"
    )
    private Boolean success;
    @Schema(
            title = "errCode",
            description = "响应码",
            format = "int32"
    )
    private Integer errCode;
    @Schema(
            title = "errMsg",
            description = "异常响应信息",
            format = "int32"
    )
    private String errMsg;
    @Schema(title = "data", description = "响应数据", accessMode = Schema.AccessMode.READ_ONLY)
    private T data;

    public static <T> ApiResult<T> success() {
        ApiResult<T> result = new ApiResult<>();
        result.setData(null);
        result.setSuccess(Boolean.TRUE);
        return result;
    }

    public static <T> ApiResult<T> fail(Integer code, String msg) {
        ApiResult<T> result = new ApiResult<T>();
        result.setSuccess(Boolean.FALSE);
        result.setErrCode(code);
        result.setErrMsg(msg);
        return result;
    }

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> result = new ApiResult<T>();
        result.setData(data);
        result.setSuccess(Boolean.TRUE);
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
