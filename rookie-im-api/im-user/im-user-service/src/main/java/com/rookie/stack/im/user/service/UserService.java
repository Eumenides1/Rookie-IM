package com.rookie.stack.im.user.service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @Classname UserService
 * @Description 用户业务服务
 * @Date 2024/9/27 15:48
 * @Created by liujiapeng
 */
public interface UserService {

    /**
     * 更新用户信息接口
     */
    void updateUserInfo(Optional<String> nickname,
                        Optional<String> avatarUrl,
                        Optional<Integer> sex,
                        Optional<LocalDate> birthday,
                        Optional<String> introduction);

}
