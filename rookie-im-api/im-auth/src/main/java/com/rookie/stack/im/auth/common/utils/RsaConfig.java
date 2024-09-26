package com.rookie.stack.im.auth.common.utils;

import cn.hutool.crypto.asymmetric.RSA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.nio.file.Files;

/**
 * @Classname Rsa
 * @Description TODO
 * @Date 2024/9/26 17:05
 * @Created by liujiapeng
 */
@Configuration
public class RsaConfig {

    private final String privateKeyPath = "rsa/privateKeyBase64.txt";
    private final String publicKeyPath = "rsa/publicKeyBase64.txt";

    @Bean
    public RSA rsa() throws Exception {
        // 读取私钥和公钥
        Resource privateKeyResource = new ClassPathResource(privateKeyPath);
        Resource publicKeyResource = new ClassPathResource(publicKeyPath);

        String privateKey = new String(Files.readAllBytes(privateKeyResource.getFile().toPath()));
        String publicKey = new String(Files.readAllBytes(publicKeyResource.getFile().toPath()));

        // 创建 RSA 实例
        RSA rsa = new RSA("RSA",privateKey,publicKey);
        // 返回单例实例
        return rsa;
    }

}
