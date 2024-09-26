package com.rookie.stack.im.auth.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.rookie.stack.framework.common.constant.RedisKeyConstants;
import com.rookie.stack.framework.common.exception.BusinessException;
import com.rookie.stack.framework.common.exception.CommonErrorEnum;
import com.rookie.stack.im.auth.domain.model.req.SmsCodeReq;
import com.rookie.stack.im.auth.service.SmsService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SmsServiceImpl implements SmsService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 发送短信验证码
     * @param req
     */
    @Override
    public void sendVerificationCode(SmsCodeReq req) {
        // 手机号
        String phone = req.getPhone();
        // 构建验证码 redis key
        String key = RedisKeyConstants.buildVerificationCodeKey(phone);
        // 判断是否已发送验证码
        boolean isSent = Boolean.TRUE.equals(redisTemplate.hasKey(key));
        if (isSent) {
            // 若之前发送的验证码未过期，则提示发送频繁
            throw new BusinessException(CommonErrorEnum.FREQUENCY_LIMIT);
        }
        // 生成 6 位随机数字验证码
        String verificationCode = RandomUtil.randomNumbers(6);

        // todo: 调用第三方短信发送服务


        log.info("==> 手机号: {}, 已发送验证码：【{}】", phone, verificationCode);

        // 存储验证码到 redis, 并设置过期时间为 3 分钟
        redisTemplate.opsForValue().set(key, verificationCode, 3, TimeUnit.MINUTES);
    }
}
