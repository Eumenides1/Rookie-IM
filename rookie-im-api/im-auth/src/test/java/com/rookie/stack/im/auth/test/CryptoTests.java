package com.rookie.stack.im.auth.test;

import cn.hutool.crypto.asymmetric.RSA;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Classname CryptoTests
 * @Description TODO
 * @Date 2024/9/26 16:48
 * @Created by liujiapeng
 */
@SpringBootTest
@Slf4j
public class CryptoTests {

    @Test
    void cryptoTest(){
        RSA rsa = new RSA();
        //获得私钥
        rsa.getPrivateKey();
        String privateKeyBase64 = rsa.getPrivateKeyBase64();
        System.out.println(privateKeyBase64);
        //获得公钥
        rsa.getPublicKey();
        String publicKeyBase64 = rsa.getPublicKeyBase64();
        System.out.println(publicKeyBase64);
        // 获取资源目录的路径
        URL resourceUrl = getClass().getClassLoader().getResource("rsa");
        String resourcePath;

        if (resourceUrl != null) {
            resourcePath = resourceUrl.getPath();
        } else {
            // 如果资源不存在，创建路径
            resourcePath = "src/main/resources/rsa";
            new File(resourcePath).mkdirs();  // 创建 rsa 目录
        }
        try {
            Files.writeString(Paths.get(resourcePath, "privateKeyBase64.txt"), privateKeyBase64);
            Files.writeString(Paths.get(resourcePath, "publicKeyBase64.txt"), publicKeyBase64);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
