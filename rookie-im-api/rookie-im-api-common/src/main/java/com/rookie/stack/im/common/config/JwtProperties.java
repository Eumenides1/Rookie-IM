package com.rookie.stack.im.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author eumenides
 * @description
 * @date 2024/9/7
 */
@Component
@Data
public class JwtProperties {
    @Value("${jwt.accessToken.expireIn}")
    private Integer accessTokenExpireIn;

    @Value("${jwt.accessToken.secret}")
    private String accessTokenSecret;

    @Value("${jwt.refreshToken.expireIn}")
    private Integer refreshTokenExpireIn ;

    @Value("${jwt.refreshToken.secret}")
    private String refreshTokenSecret;
}
