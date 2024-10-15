package com.rookie.stack.im.auth.domain.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @Classname UserLoginReq
 * @Description 用户注册登录请求参数
 * @Date 2024/9/25 16:23
 * @Created by liujiapeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "用户登录/注册实体类")
public class UserLoginReq {

    /**
     * 手机号
     */
    @Schema(description = "用户手机号", example = "151xxxxxxxx",maxLength = 11,minLength = 11)
    @NotBlank(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号只能为11位")
    @Pattern(regexp = "^1(3[0-9]|4[01456879]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\\d{8}$"  ,message = "手机号格式有误")
    private String phone;

    /**
     * 验证码
     */
    @Schema(description = "短信验证码", example = "123456")
    private String code;

    /**
     * 密码
     */
    @Schema(description = "密码", example = "123456")
    private String password;

    /**
     * 登录类型：手机号验证码，或者是账号密码
     */
    @Schema(description = "登录类型：手机号验证码，或者是账号密码", example = "123456")
    @NotNull(message = "登录类型不能为空")
    private Integer type;

}
