package com.taiping.app.cryptoUtil;


import android.util.Base64;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class AesKeyGenerator {

  public static void main( ) throws NoSuchAlgorithmException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
    //Security.addProvider(new com.sun.crypto.provider.SunJCE());
    //实例化支持AES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)  
    KeyGenerator keygen = KeyGenerator.getInstance("AES");  
    //生成密钥  
    SecretKey deskey = keygen.generateKey(); 
    
    // 输出密匙
    System.out.println("key:");
    String key = Base64.encodeToString(deskey.getEncoded(),Base64.DEFAULT);
    System.out.println(key);

    String data = "测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试";
    String encrypt = AesCryptoUtil.encrypt(data, key);
    System.out.println(encrypt);
    String decrypt = AesCryptoUtil.decrypt("XztqBw3/xS3Pd326z3EOCF87agcN/8Utz3d9us9xDghfO2oHDf/FLc93fbrPcQ4IXztqBw3/xS3Pd326z3EOCF87agcN/8Utz3d9us9xDghfO2oHDf/FLc93fbrPcQ4IXztqBw3/xS3Pd326z3EOCF87agcN/8Utz3d9us9xDghfO2oHDf/FLc93fbrPcQ4IXztqBw3/xS3Pd326z3EOCF87agcN/8Utz3d9us9xDghfO2oHDf/FLc93fbrPcQ4IXztqBw3/xS3Pd326z3EOCF87agcN/8Utz3d9us9xDghfO2oHDf/FLc93fbrPcQ4IXztqBw3/xS3Pd326z3EOCF87agcN/8Utz3d9us9xDghfO2oHDf/FLc93fbrPcQ4IKu+hhmUEzBprnOjMOpfmVA==", "X1LlzMdSAlIpHQPOQMtvwg==");
    System.out.println(data.equals(decrypt));

  }
}
