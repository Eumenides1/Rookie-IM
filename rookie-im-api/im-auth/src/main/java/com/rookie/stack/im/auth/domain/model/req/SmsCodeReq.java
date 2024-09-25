package com.rookie.stack.im.auth.domain.model.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsCodeReq {
    @NotBlank(message = "手机号不能为空")
    private String phone;
}
