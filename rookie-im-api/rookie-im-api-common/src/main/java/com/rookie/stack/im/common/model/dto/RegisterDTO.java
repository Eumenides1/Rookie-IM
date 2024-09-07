package com.rookie.stack.im.common.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @author eumenides
 * @description
 * @date 2024/9/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(title = "用户注册实体类", description = "用户注册入参实体类")
public class RegisterDTO {
    @Length(max = 64,message = "用户名不能大于64字符")
    @NotEmpty(message="用户名不可为空")
    @Schema(name = "userName", description = "用户名", format = "string", example = "jaguarliu")
    private String userName;
    @Length(min=5,max = 20,message = "密码长度必须在5-20个字符之间")
    @NotEmpty(message="用户密码不可为空")
    @Schema(name = "password", description = "用户密码", format = "string")
    private String password;
    @Length(max = 64,message = "昵称不能大于64字符")
    @NotEmpty(message="用户昵称不可为空")
    @Schema(name = "nickName", description = "用户昵称", format = "string", example = "jaguarliu")
    private String nickName;
}
