package com.rookie.stack.im.auth.domain.model.req;

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
public class UpdatePasswordReq {

    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    @NotBlank(message = "确认密码不能为空")
    private String retryPassword;
}
