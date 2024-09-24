package com.rookie.stack.im.auth.controller;

import com.rookie.stack.framework.common.domain.response.ApiResult;
import com.rookie.stack.framework.rookielog.aspect.ApiRookieLog;
import org.springframework.web.bind.annotation.GetMapping;
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
}
