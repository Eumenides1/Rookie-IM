package com.rookie.stack.im.user.controller;

import com.rookie.stack.framework.common.domain.response.ApiResult;
import com.rookie.stack.im.user.domain.model.req.UpdateUserInfoReq;
import com.rookie.stack.im.user.model.req.GetUserByPhoneReq;
import com.rookie.stack.im.user.model.req.RegisterUserReq;
import com.rookie.stack.im.user.model.req.UpdateUserPasswordReq;
import com.rookie.stack.im.user.model.resp.GetUserInfoResp;
import com.rookie.stack.im.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eumenides
 * @description
 * @date 2024/9/28
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Tag(name = "用户信息相关 API")
public class UserController {
    @Resource
    private UserService userService;
    @Operation(summary = "更新用户信息 API")
    @PostMapping("updateUserInfo")
    public ApiResult<?> updateUserInfo(@RequestBody UpdateUserInfoReq req){
        userService.updateUserInfo(req);
        return ApiResult.success("用户信息更新成功");
    }
    @PostMapping("/register")
    @Operation(summary = "用户注册 API")
    public ApiResult<Long> register(@Validated @RequestBody RegisterUserReq req){
        return ApiResult.success(userService.register(req));
    }
    @PostMapping("/getUserByPhone")
    @Operation(summary = "根据手机号码获取用户信息 API")
    public ApiResult<GetUserInfoResp> getUserByPhone(@Validated @RequestBody GetUserByPhoneReq req){
        return ApiResult.success(userService.getUserByPhone(req));
    }
    @PostMapping("/getUserByRookieId")
    @Operation(summary = "根据ID获取用户信息 API")
    public ApiResult<GetUserInfoResp> getUserByRookieId(){
        return ApiResult.success(userService.getUserByRookieId());
    }
    @PostMapping("/updatePassword")
    @Operation(summary = "更新用户密码")
    public ApiResult<?> updatePassword(@Validated @RequestBody UpdateUserPasswordReq req){
        userService.updatePassword(req);
        return ApiResult.success("用户密码更新成功");
    }
}
