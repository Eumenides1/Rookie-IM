package com.rookie.stack.im.user.controller;

import com.rookie.stack.framework.common.domain.response.ApiResult;
import com.rookie.stack.im.user.domain.model.req.UpdateUserInfoReq;
import com.rookie.stack.im.user.model.req.RegisterUserReq;
import com.rookie.stack.im.user.service.UserService;
import jakarta.annotation.Resource;
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
public class UserController {


    @Resource
    private UserService userService;

    @PostMapping("updateUserInfo")
    public ApiResult<?> updateUserInfo(@RequestBody UpdateUserInfoReq req){
        userService.updateUserInfo(req);
        return ApiResult.success("用户信息更新成功");
    }

    @PostMapping("/register")
    public ApiResult<Long> register(@Validated @RequestBody RegisterUserReq req){
        return ApiResult.success(userService.register(req));
    }

}
