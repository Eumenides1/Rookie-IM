package com.rookie.stack.im.auth.service;

import com.rookie.stack.im.auth.domain.model.req.SmsCodeReq;

public interface SmsService {

    void sendVerificationCode(SmsCodeReq req);

}
