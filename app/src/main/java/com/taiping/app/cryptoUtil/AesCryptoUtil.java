package com.taiping.app.cryptoUtil;


import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


/**
 * 对称加解密工具类
 * 
 * @author kahn
 */
public class AesCryptoUtil {
  
  //Cipher负责完成加密或解密工作  
  private static Cipher cipher;
  

  static{
    //生成Cipher对象,指定其支持的AES算法  
    try {
      cipher = Cipher.getInstance("AES");
    } catch (NoSuchAlgorithmException e) {
      
    } catch (NoSuchPaddingException e) {
      
    } 
  }

  /**
   * 加密
   * @param data 待加密数据
   * @param key 密钥
   * @return 密文
   * @throws InvalidKeyException
   * @throws IllegalBlockSizeException
   * @throws BadPaddingException
   * @throws UnsupportedEncodingException 
   */
  public static String encrypt(String data, String key) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
    
    data = Base64.encodeToString(data.getBytes("UTF-8"),Base64.DEFAULT);
    // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式  
    cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Base64.decode(key.getBytes(),Base64.DEFAULT), "AES"));
    byte[] src = data.getBytes("UTF-8");  
    // 返回密文 
    return Base64.encodeToString(cipher.doFinal(src), Base64.DEFAULT);
  }

  /**
   * 解密
   * @param data 待解密数据
   * @param key 密钥
   * @return 明文
   */
  public static String decrypt(String data, String key) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
    // 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式
    cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Base64.decode(key.getBytes(),Base64.DEFAULT), "AES"));
    return new String(Base64.decode(cipher.doFinal(Base64.decode(data.getBytes("UTF-8"),Base64.DEFAULT)),Base64.DEFAULT));
  }
}
