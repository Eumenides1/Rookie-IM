package com.rookie.stack.im.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.rookie.stack.framework.common.domain.response.ApiResult;
import com.rookie.stack.framework.common.exception.BusinessException;
import com.rookie.stack.framework.rookielog.aspect.ApiRookieLog;
import com.rookie.stack.im.auth.domain.entity.User;
import com.rookie.stack.im.auth.exception.AuthErrorEnum;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/user/doLogin")
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }
    // 查询登录状态，浏览器访问： http://localhost:8080/user/isLogin
    @RequestMapping("/user/isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }
}
