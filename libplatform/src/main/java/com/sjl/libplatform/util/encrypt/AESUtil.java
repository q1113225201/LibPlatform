package com.sjl.libplatform.util.encrypt;

import com.sjl.libplatform.util.ByteUtil;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
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

    /**
     * 加密
     *
     * @param key
     * @param data
     * @return
     */
    public static String encrypt(String key, String data) {
        return ByteUtil.byte2Str(encrypt(key.getBytes(), data.getBytes()));
    }

    /**
     * 加密
     *
     * @param key
     * @param data
     * @return
     */
    public static byte[] encrypt(byte[] key, byte[] data) {
        return doFinal(key, data, Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密
     *
     * @param key
     * @param data
     * @return
     */
    public static String decrypt(String key, String data) {
        return new String(decrypt(key.getBytes(), ByteUtil.strToBytes(data)));
    }

    /**
     * 解密
     *
     * @param key
     * @param data
     * @return
     */
    public static byte[] decrypt(byte[] key, byte[] data) {
        return doFinal(key, data, Cipher.DECRYPT_MODE);
    }

    /**
     * 最终加解密
     *
     * @param key
     * @param data
     * @param mode
     * @return
     */
    private static byte[] doFinal(byte[] key, byte[] data, int mode) {
        SecretKey secretKey = new SecretKeySpec(generateKey(key), AES);
        try {
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(mode, secretKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException
                | NoSuchPaddingException
                | InvalidKeyException
                | BadPaddingException
                | IllegalBlockSizeException e) {
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
