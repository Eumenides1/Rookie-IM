package com.rookie.stack.im.user.model.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @Classname RegisterUserReq
 * @Description TODO
 * @Date 2024/9/29 10:19
 * @Created by liujiapeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserReq {
    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号只能为11位")
    @Pattern(regexp = "^1(3[0-9]|4[01456879]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\\d{8}$"  ,message = "手机号格式有误")
    private String phone;


}
