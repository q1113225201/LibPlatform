package com.sjl.libplatform.util.encrypt;

import com.sjl.libplatform.util.ByteUtil;

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
 * @author 林zero
 * @date 2019/1/24
 */
public class AESUtil {
    private static final String AES = "AES";
    //算法/模式/补码方式
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    /**
     * 加密
     *
     * @param key
     * @param data
     * @return
     */
    public static String encrypt(String key, String data) {
        return encrypt(key, data, null);
    }

    /**
     * 加密
     *
     * @param key
     * @param data
     * @return
     */
    public static String encrypt(String key, String data, String iv) {
        return ByteUtil.byte2Str(encrypt(key.getBytes(), data.getBytes(), iv == null ? null : iv.getBytes()));
    }

    /**
     * 加密
     *
     * @param key
     * @param data
     * @return
     */
    public static byte[] encrypt(byte[] key, byte[] data) {
        return encrypt(key, data, null);
    }

    /**
     * 加密
     *
     * @param key
     * @param data
     * @return
     */
    public static byte[] encrypt(byte[] key, byte[] data, byte[] iv) {
        return doFinal(key, data, iv, Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密
     *
     * @param key
     * @param data
     * @return
     */
    public static String decrypt(String key, String data) {
        return decrypt(key, data, null);
    }

    /**
     * 解密
     *
     * @param key
     * @param data
     * @return
     */
    public static String decrypt(String key, String data, String iv) {
        return new String(decrypt(key.getBytes(), ByteUtil.strToBytes(data), iv == null ? null : iv.getBytes()));
    }

    /**
     * 解密
     *
     * @param key
     * @param data
     * @return
     */
    public static byte[] decrypt(byte[] key, byte[] data) {
        return doFinal(key, data, null, Cipher.DECRYPT_MODE);
    }

    /**
     * 解密
     *
     * @param key
     * @param data
     * @return
     */
    public static byte[] decrypt(byte[] key, byte[] data, byte[] iv) {
        return doFinal(key, data, iv, Cipher.DECRYPT_MODE);
    }

    /**
     * 最终加解密
     *
     * @param key
     * @param data
     * @param mode
     * @return
     */
    private static byte[] doFinal(byte[] key, byte[] data, byte[] iv, int mode) {
        SecretKey secretKey = new SecretKeySpec(generateKey(key), AES);
        try {
            Cipher cipher;
            if (iv == null || iv.length == 0) {
                cipher = Cipher.getInstance(AES);
                cipher.init(mode, secretKey);
            } else {
                cipher = Cipher.getInstance(TRANSFORMATION);
                IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
                cipher.init(mode, secretKey, ivParameterSpec);
            }
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException
                | NoSuchPaddingException
                | InvalidKeyException
                | BadPaddingException
                | IllegalBlockSizeException
                | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] generateKey(byte[] key) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(new String(key));
        while (stringBuilder.length() < 16) {
            stringBuilder.append("0");
        }
        if (stringBuilder.length() > 16) {
            stringBuilder.setLength(16);
        }
        return stringBuilder.toString().getBytes();
    }
}
