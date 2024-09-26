package com.rookie.stack.im.auth.alarm.impl;

import com.rookie.stack.im.auth.alarm.AlarmInterface;
import lombok.extern.slf4j.Slf4j;

/**
 * @Classname SmsAlarmHelper
 * @Description TODO
 * @Date 2024/9/26 11:21
 * @Created by liujiapeng
 */
@Slf4j
public class SmsAlarmHelper implements AlarmInterface {

    /**
     * 发送告警信息
     *
     * @param message
     * @return
     */
    @Override
    public boolean send(String message) {
        log.info("==> 【短信告警】：{}", message);

        // 业务逻辑...

        return true;
    }
}
