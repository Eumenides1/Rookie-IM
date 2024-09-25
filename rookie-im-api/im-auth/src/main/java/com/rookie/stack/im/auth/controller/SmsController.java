package com.rookie.stack.im.auth.controller;

import com.rookie.stack.framework.common.domain.response.ApiResult;
import com.rookie.stack.im.auth.domain.model.req.SmsCodeReq;
import com.rookie.stack.im.auth.service.SmsService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsController {

    @Resource
    private SmsService smsService;

    @PostMapping("/sendVerifyCode")
    public ApiResult<Object> sendVerifyCode(@RequestBody @Validated SmsCodeReq req) {
        smsService.sendVerificationCode(req);
        return ApiResult.success();
    }
}
