package com.rookie.stack.im.auth.test;

import cn.hutool.crypto.asymmetric.RSA;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
        try {
            Files.writeString(Paths.get("D:\\code\\Rookie-IM\\rookie-im-api\\im-auth\\src\\main\\resources\\rsa", "privateKeyBase64.txt"), privateKeyBase64);
            Files.writeString(Paths.get("D:\\code\\Rookie-IM\\rookie-im-api\\im-auth\\src\\main\\resources\\rsa", "publicKeyBase64.txt"), publicKeyBase64);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
