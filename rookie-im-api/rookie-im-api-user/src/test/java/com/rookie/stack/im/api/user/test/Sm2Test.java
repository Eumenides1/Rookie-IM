package com.rookie.stack.im.api.user.test;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.BCUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.asymmetric.SM2;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author eumenides
 * @description
 * @date 2024/9/9
 */
@SpringBootTest
public class Sm2Test {
    @Test
    public void testSm2(){
        String text = "我是一段测试aaaa";
        SM2 sm2 = SmUtil.sm2();
        //这里会自动生成对应的随机秘钥对 , 注意！ 这里一定要强转，才能得到对应有效的秘钥信息
        byte[] privateKey = BCUtil.encodeECPrivateKey(sm2.getPrivateKey());
        //这里公钥不压缩  公钥的第一个字节用于表示是否压缩  可以不要
        byte[] publicKey = ((BCECPublicKey) sm2.getPublicKey()).getQ().getEncoded(false);
        //这里得到的 压缩后的公钥   ((BCECPublicKey) sm2.getPublicKey()).getQ().getEncoded(true);
        // byte[] publicKeyEc = BCUtil.encodeECPublicKey(sm2.getPublicKey());
        //打印当前的公私秘钥
        System.out.println("私钥: " + HexUtil.encodeHexStr(privateKey));
        System.out.println("公钥: " + HexUtil.encodeHexStr(publicKey));
        String str = StrUtil.str(privateKey, CharsetUtil.UTF_8);
        // 公钥加密，私钥解密
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));
        System.out.println(decryptStr);
    }
    @Test
    public void generateRsaKey(){
        RSA rsa = new RSA();

        //获得私钥
        rsa.getPrivateKey();
        String privateKeyBase64 = rsa.getPrivateKeyBase64();
        System.out.println("私钥:"+privateKeyBase64);
        //获得公钥
        rsa.getPublicKey();
        String publicKeyBase64 = rsa.getPublicKeyBase64();
        System.out.println("公钥："+publicKeyBase64);
    }
}
