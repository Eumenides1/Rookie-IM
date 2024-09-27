package com.rookie.stack.im.user.exception;

import com.rookie.stack.framework.common.domain.response.ApiResult;
import com.rookie.stack.framework.common.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.rookie.stack.framework.common.exception.CommonErrorEnum.PARAM_INVALID;

@RestControllerAdvice
@Slf4j
public class UserGlobalExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult<Object> handlerAuthException(HttpServletRequest request, BusinessException e) {
        log.warn("{} request fail, errorCode: {}, errorMessage: {}", request.getRequestURI(), e.getErrorCode(), e.getErrorCode());
        return ApiResult.fail(e.getErrorCode(), e.getErrorMsg());
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ApiResult<?> exceptionHandler(Exception e) {
        log.info("business exception！The reason is：{}", e.getMessage(), e);
        return ApiResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult<?> methodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
        StringBuilder errMsg = new StringBuilder();
        e.getBindingResult().getFieldErrors()
                .forEach(x -> errMsg.append(x.getField()).append(x.getDefaultMessage()).append(","));
        String message = errMsg.toString();

        return ApiResult.fail(PARAM_INVALID.getCode(), message.substring(0, message.length() - 1));
    }
}
