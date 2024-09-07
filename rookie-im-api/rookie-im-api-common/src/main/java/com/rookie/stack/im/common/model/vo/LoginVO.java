package com.rookie.stack.im.common.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author eumenides
 * @description
 * @date 2024/9/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(title = "用户登录成功响应实体", description = "用户登录成功响应实体")
public class LoginVO {
    @Schema(name = "accessToken", description = "用户 Token", format = "string")
    private String accessToken;
    @Schema(name = "accessTokenExpiresIn", description = "用户 Token过期时间", format = "int32")
    private Integer accessTokenExpiresIn;
    @Schema(name = "refreshToken", description = "用户续期token", format = "string")
    private String refreshToken;
    @Schema(name = "refreshTokenExpiresIn", description = "用户续期token过期时间", format = "int32")
    private Integer refreshTokenExpiresIn;

}
