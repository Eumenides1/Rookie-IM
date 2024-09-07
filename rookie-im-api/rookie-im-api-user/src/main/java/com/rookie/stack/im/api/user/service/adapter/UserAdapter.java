package com.rookie.stack.im.api.user.service.adapter;

import com.alibaba.fastjson.JSON;
import com.rookie.stack.im.common.config.JwtProperties;
import com.rookie.stack.im.common.model.session.UserSessionInfo;
import com.rookie.stack.im.common.model.vo.LoginVO;
import com.rookie.stack.im.common.utils.JwtUtils;
import jakarta.annotation.Resource;

/**
 * @author eumenides
 * @description User 相关实体参数封装
 * @date 2024/9/7
 */
public class UserAdapter {


    public static LoginVO userToLoginVo(Long userId,
                                        Integer terminal,
                                        String userName,
                                        String nickName,
                                        JwtProperties jwtProperties){
        //生成token
        UserSessionInfo userSessionInfo = new UserSessionInfo();
        userSessionInfo.setUserId(userId);
        userSessionInfo.setTerminal(terminal);
        userSessionInfo.setUserName(userName);
        userSessionInfo.setNickName(nickName);
        String strJson = JSON.toJSONString(userSessionInfo);

        String accessToken = JwtUtils.sign(userId, strJson, jwtProperties.getAccessTokenExpireIn(), jwtProperties.getAccessTokenSecret());
        String refreshToken = JwtUtils.sign(userId, strJson, jwtProperties.getRefreshTokenExpireIn(), jwtProperties.getRefreshTokenSecret());

        return LoginVO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpiresIn(jwtProperties.getAccessTokenExpireIn())
                .refreshTokenExpiresIn(jwtProperties.getRefreshTokenExpireIn())
                .build();
    }



}
