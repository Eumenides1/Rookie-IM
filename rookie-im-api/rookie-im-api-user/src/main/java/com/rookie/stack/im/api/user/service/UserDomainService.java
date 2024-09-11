package com.rookie.stack.im.api.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rookie.stack.im.common.model.dto.LoginDTO;
import com.rookie.stack.im.common.model.dto.RegisterDTO;
import com.rookie.stack.im.common.model.entity.User;
import com.rookie.stack.im.common.model.vo.LoginVO;

/**
 * @author eumenides
 * @description
 * @date 2024/9/7
 */
public interface UserDomainService extends IService<User> {
    LoginVO login(LoginDTO dto);

    /**
     * 用户注册
     */
    void register(RegisterDTO dto);

    /**
     * 用refreshToken换取新 token
     */
    LoginVO refreshToken(String refreshToken);


}
