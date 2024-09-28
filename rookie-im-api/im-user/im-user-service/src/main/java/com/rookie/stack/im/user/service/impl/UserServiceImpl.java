package com.rookie.stack.im.user.service.impl;

import com.rookie.stack.im.user.dao.ImUserDao;
import com.rookie.stack.im.user.domain.model.req.UpdateUserInfoReq;
import com.rookie.stack.im.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Classname UserServiceImpl
 * @Description 用户服务实现类
 * @Date 2024/9/27 15:50
 * @Created by liujiapeng
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private ImUserDao imUserDao;


    @Override
    public void updateUserInfo(UpdateUserInfoReq req) {
        // 获取用户 id


        return;

    }
}
