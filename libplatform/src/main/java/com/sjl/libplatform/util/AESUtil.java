package com.sjl.libplatform.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AESUtil
 * 对称加密
 *
 * @author 沈建林
 * @date 2019/1/24
 */
public class AESUtil {
    private static final String AES = "AES";
    private static final String TRANSFORMATION = "AES/ECB/NoPadding";

    /**
     * 加密
     *
     * @param key
     * @param data
     * @return
     */
    public static String encrypt(String key, String data) {
        return ByteUtil.byte2Str(encrypt(ByteUtil.strToBytes(key), ByteUtil.strToBytes(data)));
    }

    /**
     * 加密
     *
     * @param key
     * @param data
     * @return
     */
    public static byte[] encrypt(byte[] key, byte[] data) {
        return doFinal(key, data,null, Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密
     *
     * @param key
     * @param data
     * @return
     */
    public static String decrypt(String key, String data) {
        return ByteUtil.byte2Str(decrypt(ByteUtil.strToBytes(key), ByteUtil.strToBytes(data)));
    }

    /**
     * 解密
     *
     * @param key
     * @param data
     * @return
     */
    public static byte[] decrypt(byte[] key, byte[] data) {
        return doFinal(key, data,null, Cipher.DECRYPT_MODE);
    }

    /**
     * 最终加解密
     *
     * @param key
     * @param data
     * @param type
     * @return
     */
    private static byte[] doFinal(byte[] key, byte[] data, byte[] iv, int type) {
        SecretKey secretKey = new SecretKeySpec(key, AES);
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            if (iv == null || iv.length == 0) {
                cipher.init(type, secretKey);
            } else {
                IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
                cipher.init(type, secretKey, ivParameterSpec);
            }
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException
                | NoSuchPaddingException
                | InvalidKeyException
                | InvalidAlgorithmParameterException
                | BadPaddingException
                | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

}
