package com.sjl.libplatform.util;

import android.text.TextUtils;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * EncryptUtil
 *
 * @author 林zero
 * @date 2019/1/9
 */
public class EncryptUtil {
    /**
     * 32位MD5加密
     *
     * @param str
     * @return
     */
    public static String encrypt32MD5(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            return to32Hex(bytes);
        } catch (NoSuchAlgorithmException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 16位MD5加密
     *
     * @param str
     * @return
     */
    public static String encrypt16MD5(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            return to16Hex(bytes);
        } catch (NoSuchAlgorithmException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * DES加密
     *
     * @param key
     * @param data
     * @return
     */
    public static String encryptDES(String key, String data) {
        return to32Hex(encryptDES(key.getBytes(), data.getBytes()));
    }

    /**
     * DES加密
     *
     * @param key
     * @param data
     * @return
     */
    public static byte[] encryptDES(byte[] key, byte[] data) {
        try {
            //生成可信任随机数源
            SecureRandom secureRandom = new SecureRandom();
            //创建DESKeySpec
            DESKeySpec desKeySpec = new DESKeySpec(key);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, secureRandom);
            return cipher.doFinal(data);
        } catch (InvalidKeyException
                | NoSuchAlgorithmException
                | InvalidKeySpecException
                | NoSuchPaddingException
                | BadPaddingException
                | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * DES解密
     *
     * @param key
     * @param data
     * @return
     */
    public static String decryptDES(String key, String data) {
        return new String(decryptDES(key.getBytes(), strToBytes(data)));
    }

    /**
     * DES解密
     *
     * @param key
     * @param data
     * @return
     */
    public static byte[] decryptDES(byte[] key, byte[] data) {
        try {
            //生成可信任随机数源
            SecureRandom secureRandom = new SecureRandom();
            //创建DESKeySpec
            DESKeySpec desKeySpec = new DESKeySpec(key);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, secureRandom);
            return cipher.doFinal(data);
        } catch (InvalidKeyException
                | NoSuchAlgorithmException
                | InvalidKeySpecException
                | NoSuchPaddingException
                | BadPaddingException
                | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 二进制转16进制字符(16位)
     *
     * @param bytes
     * @return
     */
    private static String to16Hex(byte[] bytes) {
        return to32Hex(bytes).substring(8, 24);
    }

    /**
     * 二进制转16进制字符(32位)
     *
     * @param bytes
     * @return
     */
    private static String to32Hex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bytes != null) {
            for (Byte b : bytes) {
                stringBuilder.append(String.format("%02X", b.intValue() & 0xFF));
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 字符串转二进制
     *
     * @param s
     * @return
     */
    private static byte[] strToBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        try {
            for (int i = 0; i < len; i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }
        } catch (Exception e) {
        }
        return data;
    }
}
