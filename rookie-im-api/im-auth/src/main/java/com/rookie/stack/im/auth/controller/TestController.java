package com.rookie.stack.im.auth.controller;

import com.rookie.stack.framework.common.domain.response.ApiResult;
import com.rookie.stack.framework.common.exception.BusinessException;
import com.rookie.stack.framework.rookielog.aspect.ApiRookieLog;
import com.rookie.stack.im.auth.domain.entity.User;
import com.rookie.stack.im.auth.exception.AuthErrorEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eumenides
 * @description
 * @date 2024/9/24
 */
@RestController
public class TestController {
    @GetMapping("/test")
    @ApiRookieLog(description = "测试接口")
    public ApiResult<String> test() {
        return ApiResult.success("Hello, Rookie IM");
    }

    @PostMapping("/localDate")
    @ApiRookieLog(description = "Java8 LocalDateTime 问题复现")
    public ApiResult<User> testLocalDate(@RequestBody User user) {
        return ApiResult.success(user);
    }

    @GetMapping("/error")
    @ApiRookieLog(description = "统一异常处理测试")
    public void testExceptionHandler(){
        throw new BusinessException(AuthErrorEnum.FORBIDDEN);
    }
}
