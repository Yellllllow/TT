package com.taiping.app.cryptoUtil;


import android.util.Base64;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
//import sun.misc.BASE64Encoder;

public class RsaKeyGenerator {

  public static void main( ) throws NoSuchAlgorithmException, IOException {
    KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA"); // 创建‘密匙对’生成器
    kpg.initialize(2048); // 指定密匙长度（取值范围：512～2048）
    KeyPair kp = kpg.genKeyPair(); // 生成‘密匙对’，其中包含着一个公匙和一个私匙的信息
    PublicKey public_key = kp.getPublic(); // 获得公匙
    PrivateKey private_key = kp.getPrivate(); // 获得私匙

    // 输出公匙
    System.out.println("public key:");
    String publicKey = Base64.encodeToString(public_key.getEncoded(),Base64.DEFAULT);
    System.out.println(publicKey);

    // 输出私匙
    System.out.println("private key:");
    String privateKey =  Base64.encodeToString(private_key.getEncoded(),Base64.DEFAULT);
    System.out.println(privateKey);
    
    /**
     * 验证
     */
    String data = "1234";

    String encrypt = RsaCryptoUtil.encrypt(data, publicKey);
    String decrypt = RsaCryptoUtil.decrypt(encrypt, privateKey);
    System.out.println(decrypt);
    System.out.println(decrypt.equals(data));
    
    
  }
}
