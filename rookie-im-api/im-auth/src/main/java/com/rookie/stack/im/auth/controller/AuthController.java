package com.rookie.stack.im.auth.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.rookie.stack.framework.common.domain.response.ApiResult;
import com.rookie.stack.im.auth.domain.model.req.UserLoginReq;
import com.rookie.stack.im.auth.service.ImUserService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname AuthController
 * @Description TODO
 * @Date 2024/9/25 16:44
 * @Created by liujiapeng
 */
@RestController
public class AuthController {

    @Resource
    private ImUserService imUserService;

    @PostMapping("/loginOrRegister")
    public ApiResult<String> loginAndRegister(@Validated @RequestBody UserLoginReq userLoginReq) {
        SaTokenInfo saTokenInfo = imUserService.loginOrRegister(userLoginReq);
        return ApiResult.success(saTokenInfo.tokenValue);
    }

    @PostMapping("/logout")
    public ApiResult<?> logout() {
        // todo 账号退出登录逻辑待实现
        return ApiResult.success();
    }
}
