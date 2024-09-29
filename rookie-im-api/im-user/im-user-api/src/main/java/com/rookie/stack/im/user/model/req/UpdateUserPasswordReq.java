package com.rookie.stack.im.user.model.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname UpdateUserPasswordReq
 * @Description TODO
 * @Date 2024/9/29 13:04
 * @Created by liujiapeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserPasswordReq {
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    @NotBlank(message = "密码不能为空")
    private String encodePassword;
}
