package com.rookie.stack.im.common.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author eumenides
 * @description 登录dto
 * @date 2024/9/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "用户登陆DTO", description = "用户登陆入参实体类")
public class LoginDTO {
    @Max(value = 1,message = "登录终端类型取值范围:0,1")
    @Min(value = 0,message = "登录终端类型取值范围:0,1")
    @Schema(name = "terminal", description = "登录终端类型", format = "int64", example = "1")
    @NotNull(message="登录终端类型不可为空")
    private Integer terminal;
    @NotEmpty(message="用户名不可为空")
    @Schema(name = "userName", description = "用户名", format = "string", example = "jaguarliu")
    private String userName;
    @NotEmpty(message="用户密码不可为空")
    @Schema(name = "password", description = "用户密码", format = "string")
    private String password;
}
