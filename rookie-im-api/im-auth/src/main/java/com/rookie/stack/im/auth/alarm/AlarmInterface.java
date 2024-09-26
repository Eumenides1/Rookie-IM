package com.rookie.stack.im.auth.alarm;

/**
 * @Classname AlarmInterface
 * @Description TODO
 * @Date 2024/9/26 11:20
 * @Created by liujiapeng
 */
public interface AlarmInterface {
    /**
     * 发送告警信息
     *
     * @param message
     * @return
     */
    boolean send(String message);
}
