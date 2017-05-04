package com.taiping.app.cryptoUtil;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


/**
 * AES和RSA混合加密类
 * 发送方：
 * 第一步随机生成AES密钥；
 * 第二步用获得的RSA公钥和RSA加密算法加密AES密钥并将加密后的AES密钥写人数据的头部；
 * 第三步用AES密钥和AES加密算法给数据文件加密，并将加密后的数据文件写入数据的尾部；最
 * 后发送给接收方。
 * 接收方：
 * 第一步从返回数据中读取加密后的AES密钥；
 * 第二步用自己的私钥和RAS加密算法解密AES密钥；
 * 第三步接着从返回数据中读取加密数据，用于解密的AES密钥和ASE加密算法解密加密数据，
 * 最后得到明文。
 * @author kahn
 *
 * 2017年1月13日
 */
public class AesAndRsaMixtureCryptoUtil {


  private  static String createAesKey() throws NoSuchAlgorithmException{
   // Security.addProvider(new com.sun.crypto.provider.SunJCE());
    //实例化支持AES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)  
    KeyGenerator keygen = KeyGenerator.getInstance("AES");  
    //生成密钥  
    SecretKey deskey = keygen.generateKey(); 
    
    // 输出密匙
    System.out.println("key:");
    String key = Base64.encodeToString(deskey.getEncoded(),Base64.DEFAULT);
    System.out.println(key);
    return key;
  }
  
  /**
   * 加密
   * @param data 待加密数据
   * @param key rsa公钥
   * @return 密文
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeyException
   * @throws IllegalBlockSizeException
   * @throws BadPaddingException
   * @throws UnsupportedEncodingException
   */
  public static String encrypt(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
    // 创建aes密钥
    String aesKey = createAesKey();
    
    // ase加密明文，得到密文
    String aesEncrypt = AesCryptoUtil.encrypt(data, aesKey);
    
    // rsa加密aes的密钥，得到密文
    String rsaEncrypt = RsaCryptoUtil.encrypt(aesKey, key);

    // 组装密文
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(aesEncrypt);
    stringBuffer.append("@@@@");
    stringBuffer.append(rsaEncrypt);
    System.out.println(stringBuffer.toString());
    //System.out.println(aesEncrypt);
    return stringBuffer.toString();
  }
  
  /**
   * 解密
   * @param data 待解密数据
   * @param key rsa私钥
   * @return 明文
   * @throws UnsupportedEncodingException
   * @throws InvalidKeyException
   * @throws IllegalBlockSizeException
   * @throws BadPaddingException
   */
  public static String decrypt(String data, String key) throws UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
    // 数组中[0]是ase加密明文得到的密文，[1]是rsa加密aes的密钥，得到密文
    String[] dataArray =  data.split("@@@@");
    String aesKeyDecrypt = RsaCryptoUtil.decrypt(dataArray[1], key);
    System.out.println("aesKeyDecrypt:"+aesKeyDecrypt);
    String decrypt = AesCryptoUtil.decrypt(dataArray[0], aesKeyDecrypt);
    return decrypt;
  }
}
