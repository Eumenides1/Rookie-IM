package com.rookie.stack.im.auth.domain.model.req;

import com.rookie.stack.im.auth.common.validate.PasswordsMatch;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname UpdatePasswordReq
 * @Description 修改密码请求
 * @Date 2024/9/26 16:23
 * @Created by liujiapeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@PasswordsMatch
@Schema(description = "更新密码请求实体")
public class UpdatePasswordReq {
    @Schema(description = "新密码")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
    @Schema(description = "确认密码")
    @NotBlank(message = "确认密码不能为空")
    private String retryPassword;
}
