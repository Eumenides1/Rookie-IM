package com.rookie.stack.im.auth.alarm.impl;

import com.rookie.stack.im.auth.alarm.AlarmInterface;
import lombok.extern.slf4j.Slf4j;

/**
 * @Classname MailAlarmHelper
 * @Description TODO
 * @Date 2024/9/26 11:21
 * @Created by liujiapeng
 */
@Slf4j
public class MailAlarmHelper implements AlarmInterface {
    @Override
    public boolean send(String message) {
        log.info("==> 【邮件告警】：{}", message);

        // 业务逻辑...

        return true;
    }
}
