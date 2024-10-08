package com.rookie.stack.im.user.service;

import com.rookie.stack.im.user.domain.model.req.UpdateUserInfoReq;
import com.rookie.stack.im.user.model.req.GetUserByPhoneReq;
import com.rookie.stack.im.user.model.req.RegisterUserReq;
import com.rookie.stack.im.user.model.req.UpdateUserPasswordReq;
import com.rookie.stack.im.user.model.resp.GetUserInfoResp;

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
    void updateUserInfo(UpdateUserInfoReq updateUserInfoReq);

    Long register(RegisterUserReq req);

    GetUserInfoResp getUserByPhone(GetUserByPhoneReq req);

    void updatePassword(UpdateUserPasswordReq req);

    GetUserInfoResp getUserByRookieId();
}
