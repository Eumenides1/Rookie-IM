package com.rookie.stack.im.auth.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.rookie.stack.framework.common.domain.response.ApiResult;
import com.rookie.stack.im.auth.domain.model.req.UpdatePasswordReq;
import com.rookie.stack.im.auth.domain.model.req.UserLoginReq;
import com.rookie.stack.im.auth.service.ImUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Tag(name = "授权相关 API")
public class AuthController {

    @Resource
    private ImUserService imUserService;
    @Operation(summary = "登录/注册 API")
    @PostMapping("/loginOrRegister")
    public ApiResult<String> loginAndRegister(@Validated @RequestBody UserLoginReq userLoginReq) {
        SaTokenInfo saTokenInfo = imUserService.loginOrRegister(userLoginReq);
        return ApiResult.success(saTokenInfo.tokenValue);
    }
    @Operation(summary = "更新用户密码")
    @PostMapping("/updatePassword")
    public ApiResult<?> updatePassword(@Validated @RequestBody UpdatePasswordReq req){
        imUserService.updatePassword(req);
        return ApiResult.success();
    }
    @Operation(summary = "用户退出登录")
    @PostMapping("/logout")
    public ApiResult<?> logout() {
        Long logout = imUserService.logout();
        return ApiResult.success(logout);
    }
}
