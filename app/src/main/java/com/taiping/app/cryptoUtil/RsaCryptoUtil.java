package com.taiping.app.cryptoUtil;


import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;



/**
 * 非对称加解密工具类
 * 
 * @author yiyuan.chen
 * @version $Id: AsymmtricCryptoUtil.java, v 0.1 2011-2-24 下午03:24:05 yiyuan.chen Exp $
 */
public class RsaCryptoUtil {

  public static String encrypt(String data, String key) {
    try {
      data = Base64.encodeToString(data.getBytes("UTF-8"),Base64.DEFAULT);
      byte[] resultBytes = asymmtricCrypto(Base64.decode(data.getBytes("UTF-8"),Base64.DEFAULT),
          Base64.decode(key.getBytes("UTF-8"),Base64.DEFAULT), "RSA", Cipher.ENCRYPT_MODE);
      return Base64.encodeToString(resultBytes,Base64.DEFAULT);
    } catch (GeneralSecurityException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
    }
    return "";
  }

  public static String encrypt(byte[] data, String key) {
    try {
      String encodeData = Base64.encodeToString(data,Base64.DEFAULT);
      byte[] resultBytes = asymmtricCrypto(Base64.decode(encodeData.getBytes("UTF-8"),Base64.DEFAULT),
          Base64.decode(key.getBytes("UTF-8"),Base64.DEFAULT), "RSA", Cipher.ENCRYPT_MODE);
      return Base64.encodeToString(resultBytes,Base64.DEFAULT);
    } catch (GeneralSecurityException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
    }
    return "";
  }

  /**
   * 
   * @param data 待解密数据
   * @param key 密钥
   * @return
   * @throws UnsupportedEncodingException
   */
  public static String decrypt(String data, String key) throws UnsupportedEncodingException {
    try {
      byte[] resultBytes = asymmtricCrypto(Base64.decode(data.getBytes("UTF-8"),Base64.DEFAULT),
          Base64.decode(key.getBytes("UTF-8"),Base64.DEFAULT), "RSA", Cipher.DECRYPT_MODE);
      String decrypt = Base64.encodeToString(resultBytes,Base64.DEFAULT);
      return new String(Base64.decode(decrypt.getBytes("UTF-8"),Base64.DEFAULT), "UTF-8");
    } catch (GeneralSecurityException e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * 非对称加解密(/ECB/PKCS1Padding模式) 本方法是RSA算法的公钥加密 私钥解密.
   * 
   * @param text 待加/解密的数据.
   * @param keyData 密钥数据.
   * @param algorithm 非对称加密算法名称。KMI目前只接受RSA.
   * @param mode 加解密标识：加密――Cipher.ENCRYPT_MODE；解密――Cipher.DECRYPT_MODE
   * @return 密文(加密)/明文（解密）.
   * @throws GeneralSecurityException
   */
  public static byte[] asymmtricCrypto(final byte[] text, final byte[] keyData,
      final String algorithm, final int mode) throws GeneralSecurityException {

    final Cipher cipher = getCipher(keyData, algorithm, mode);
    return cipher.doFinal(text);
  }

  /**
   * 非对称加解密(/ECB/PKCS1Padding模式) 本方法是RSA算法的私钥加密 公钥解密.
   * 
   * @param text 待加/解密的数据.
   * @param keyData 密钥数据.
   * @param algorithm 非对称加密算法名称。KMI目前只接受RSA.
   * @param mode 加解密标识：加密――Cipher.ENCRYPT_MODE；解密――Cipher.DECRYPT_MODE
   * @return 密文(加密)/明文（解密）.
   * @throws GeneralSecurityException
   */
  public static byte[] asymmtricCryptoReverse(final byte[] text, final byte[] keyData,
      final String algorithm, final int mode) throws GeneralSecurityException {

    final Cipher cipher = getCipherReverse(keyData, algorithm, mode);
    return cipher.doFinal(text);
  }

  /**
   * 获取非对称加解密输入流的方法
   * 
   * @param file
   * @param keyData
   * @param algorithm
   * @param mode
   * @return
   * @throws FileNotFoundException
   * @throws GeneralSecurityException
   */
  public static InputStream getInputStream(final File file, final byte[] keyData,
      final String algorithm, final int mode)
      throws FileNotFoundException, GeneralSecurityException {
    // 初始化输入流
    final FileInputStream fileInputStream = new FileInputStream(file);
    // 初始化cipher
    final Cipher cipher = getCipher(keyData, algorithm, mode);
    return new CipherInputStream(fileInputStream, cipher);
  }

  /**
   * 得到公钥
   * 
   * @param keyData 密钥数据
   * @throws GeneralSecurityException
   */
  private static PublicKey getPublicKey(final byte[] keyData) throws GeneralSecurityException {
    final X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyData);
    final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    return keyFactory.generatePublic(keySpec);
  }

  /**
   * 得到私钥
   * 
   * @param keyData 密钥数据
   * @throws GeneralSecurityException
   */
  private static PrivateKey getPrivateKey(final byte[] keyData) throws GeneralSecurityException {
    final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyData);
    final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    return keyFactory.generatePrivate(keySpec);
  }

  /**
   * 根据参数初始化cipher的方法(默认是公钥加密私钥解密)
   * 
   * @param keyData
   * @param algorithm
   * @param mode
   * @return
   * @throws GeneralSecurityException
   */
  private static Cipher getCipher(final byte[] keyData, final String algorithm, final int mode)
      throws GeneralSecurityException {
    if (mode != Cipher.ENCRYPT_MODE && mode != Cipher.DECRYPT_MODE) {
      throw new GeneralSecurityException(
          "错误的加解密标识,目前KMI只支持Cipher.ENCRYPT_MODE和Cipher.DECRYPT_MODE");
    }

    final String fullAlg = algorithm + "/ECB/PKCS1Padding";
    final Cipher cipher = Cipher.getInstance(fullAlg);

    Key rsaKey = null;
    if (mode == Cipher.ENCRYPT_MODE) {
      rsaKey = getPublicKey(keyData);
      cipher.init(mode, rsaKey);
    } else {
      rsaKey = getPrivateKey(keyData);
      cipher.init(mode, rsaKey);
    }
    return cipher;
  }

  /**
   * 根据参数初始化cipher的方法(私钥加密公钥解密)
   * 
   * @param keyData
   * @param algorithm
   * @param mode
   * @throws Exception
   * @return
   * @throws GeneralSecurityException
   */
  private static Cipher getCipherReverse(final byte[] keyData, final String algorithm,
      final int mode) throws GeneralSecurityException {
    if (mode != Cipher.ENCRYPT_MODE && mode != Cipher.DECRYPT_MODE) {
      throw new GeneralSecurityException(
          "错误的加解密标识,目前KMI只支持Cipher.ENCRYPT_MODE和Cipher.DECRYPT_MODE");
    }

    final String fullAlg = algorithm + "/ECB/PKCS1Padding";
    final Cipher cipher = Cipher.getInstance(fullAlg);

    Key rsaKey = null;
    if (mode == Cipher.ENCRYPT_MODE) {
      rsaKey = getPrivateKey(keyData);
      cipher.init(mode, rsaKey);
    } else {
      rsaKey = getPublicKey(keyData);
      cipher.init(mode, rsaKey);
    }
    return cipher;
  }
}
