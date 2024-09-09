package com.rookie.stack.im.api.user.config;

import cn.hutool.crypto.asymmetric.RSA;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author eumenides
 * @description
 * @date 2024/9/9
 */
@Configuration
public class RsaConfig {
    @Value("${rsa.public-key}")
    private String publicKey;

    @Value("${rsa.private-key}")
    private String privateKey;
    @Bean
    public RSA rsa(){
        return new RSA(privateKey, publicKey);
    }
}
