package com.rookie.stack.im.auth.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.rookie.stack.im.auth.domain.model.req.UserLoginReq;

/**
* @author liujiapeng
* @description 针对表【im_user(IM用户表)】的数据库操作Service
* @createDate 2024-09-25 16:17:37
*/
public interface ImUserService  {

    SaTokenInfo loginOrRegister(UserLoginReq req);

    Long logout();

}
