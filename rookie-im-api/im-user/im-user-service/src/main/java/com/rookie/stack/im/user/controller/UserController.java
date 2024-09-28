package com.rookie.stack.im.user.controller;

import com.rookie.stack.framework.common.domain.response.ApiResult;
import com.rookie.stack.im.user.domain.model.req.UpdateUserInfoReq;
import com.rookie.stack.im.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eumenides
 * @description
 * @date 2024/9/28
 */
@RestController
public class UserController {


    @Resource
    private UserService userService;

    @PostMapping("updateUserInfo")
    public ApiResult<?> updateUserInfo(@RequestBody UpdateUserInfoReq req){
        userService.updateUserInfo(req);
        return ApiResult.success("用户信息更新成功");
    }

}
