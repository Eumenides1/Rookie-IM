package com.rookie.stack.im.gateway.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rookie.stack.framework.common.domain.response.ApiResult;
import com.rookie.stack.im.gateway.enums.GatewayErrorEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Classname GatewayExceptionHandler
 * @Description 网关统一异常处理
 * @Date 2024/9/26 15:04
 * @Created by liujiapeng
 */
@Component
@Slf4j
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {

    @Resource
    private ObjectMapper objectMapper;


    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        // 获取响应对象
        ServerHttpResponse response = exchange.getResponse();
        log.error("==> 全局异常捕获: ", ex);
        ApiResult<?> result;
        // 根据捕获的异常类型，设置不同的响应状态码和响应消息
        if (ex instanceof NotLoginException) { // 未登录异常
            // 设置 401 状态码
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 构建响应结果
            result = ApiResult.fail(GatewayErrorEnum.UNAUTHORIZED.getErrorCode(), ex.getMessage());
        } else if (ex instanceof NotPermissionException) { // 无权限异常
            // 权限认证失败时，设置 401 状态码
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 构建响应结果
            result = ApiResult.fail(GatewayErrorEnum.UNAUTHORIZED.getErrorCode(), GatewayErrorEnum.UNAUTHORIZED.getErrorMsg());
        } else { // 其他异常，则统一提示 “系统繁忙” 错误
            result = ApiResult.fail(GatewayErrorEnum.SYSTEM_ERROR);
        }

        // 设置响应头的内容类型为 application/json;charset=UTF-8，表示响应体为 JSON 格式
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        // 设置 body 响应体
        return response.writeWith(Mono.fromSupplier(() -> { // 使用 Mono.fromSupplier 创建响应体
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                // 使用 ObjectMapper 将 result 对象转换为 JSON 字节数组
                return bufferFactory.wrap(objectMapper.writeValueAsBytes(result));
            } catch (Exception e) {
                // 如果转换过程中出现异常，则返回空字节数组
                return bufferFactory.wrap(new byte[0]);
            }
        }));
    }
}
