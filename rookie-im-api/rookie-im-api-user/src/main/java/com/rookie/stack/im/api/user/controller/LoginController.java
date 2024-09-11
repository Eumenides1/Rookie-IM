package com.rookie.stack.im.api.user.controller;

import com.rookie.stack.im.api.user.service.UserDomainService;
import com.rookie.stack.im.common.model.dto.LoginDTO;
import com.rookie.stack.im.common.model.dto.RegisterDTO;
import com.rookie.stack.im.common.model.response.ApiResult;
import com.rookie.stack.im.common.model.vo.LoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @author eumenides
 * @description
 * @date 2024/9/7
 */
@RestController
@RequestMapping("/user")
@Tag(name = "用户登录&注册相关接口")
public class LoginController {

    @Resource
    private UserDomainService userDomainService;
    @PostMapping("/login")
    @Operation(
            summary = "用户登录接口"
    )
    public ApiResult<LoginVO> register(@Valid @RequestBody LoginDTO dto){
        LoginVO vo = userDomainService.login(dto);
        return ApiResult.success(vo);
    }
    @PostMapping("/register")
    @Operation(
            summary = "用户注册接口"
    )
    public ApiResult<String> register(@Valid @RequestBody RegisterDTO dto){
        userDomainService.register(dto);
        return ApiResult.success("用户注册成功");
    }

    @PutMapping("/refreshToken")
    @Operation(summary="用refreshtoken换取新的token")
    public ApiResult<LoginVO> refreshToken(@RequestHeader("refreshToken")String refreshToken){
        LoginVO vo = userDomainService.refreshToken(refreshToken);
        return ApiResult.success(vo);
    }
}
