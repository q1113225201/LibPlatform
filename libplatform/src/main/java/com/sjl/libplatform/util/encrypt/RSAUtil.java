package com.sjl.libplatform.util.encrypt;

import com.sjl.libplatform.util.Base64Util;
import com.sjl.libplatform.util.ByteUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * RSAUtil
 * 不对称加密
 *
 * @author 林zero
 * @date 2019/1/28
 */
public class RSAUtil {
    private static final String RSA = "RSA";
    private static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding";
    //加密最大长度
    private static final int MAX_LENGTH_ENCRYPT = 117;
    //解密最大长度
    private static final int MAX_LENGTH_DECRYPT = 128;

    /**
     * 公钥加密
     *
     * @param key
     * @param data
     * @return
     */
    public static byte[] encryptPublic(byte[] key, byte[] data) {
        return doFinal(key, data, true, Cipher.ENCRYPT_MODE);
    }

    /**
     * 公钥加密
     *
     * @param key
     * @param data
     * @return
     */
    public static String encryptPublic(String key, String data) {
        return Base64Util.encodeToString(encryptPublic(Base64Util.decode(key), Base64Util.encode(data)));
    }

    /**
     * 私钥加密
     *
     * @param key
     * @param data
     * @return
     */
    public static byte[] encryptPrivate(byte[] key, byte[] data) {
        return doFinal(key, data, false, Cipher.ENCRYPT_MODE);
    }

    /**
     * 私钥加密
     *
     * @param key
     * @param data
     * @return
     */
    public static String encryptPrivate(String key, String data) {
        return Base64Util.encodeToString(encryptPrivate(Base64Util.decode(key), Base64Util.encode(data)));
    }

    /**
     * 公钥解密
     *
     * @param key
     * @param data
     * @return
     */
    public static byte[] decryptPublic(byte[] key, byte[] data) {
        return doFinal(key, data, true, Cipher.DECRYPT_MODE);
    }

    /**
     * 公钥解密
     *
     * @param key
     * @param data
     * @return
     */
    public static String decryptPublic(String key, String data) {
        return new String(decryptPublic(Base64Util.decode(key), Base64Util.encode(data)));
    }

    /**
     * 私钥解密
     *
     * @param key
     * @param data
     * @return
     */
    public static byte[] decryptPrivate(byte[] key, byte[] data) {
        return doFinal(key, data, false, Cipher.DECRYPT_MODE);
    }

    /**
     * 私钥解密
     *
     * @param key
     * @param data
     * @return
     */
    public static String decryptPrivate(String key, String data) {
        return new String(decryptPrivate(Base64Util.decode(key), Base64Util.decode(data)));
    }

    /**
     * 最终加解密
     *
     * @param key
     * @param data
     * @param isPublic
     * @param mode
     * @return
     */
    private static byte[] doFinal(byte[] key, byte[] data, boolean isPublic, int mode) {
        ByteArrayOutputStream outputStream = null;
        try {
            Key rsaKey;
            if (isPublic) {
                //公钥
                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
                rsaKey = KeyFactory.getInstance(RSA).generatePublic(keySpec);
            } else {
                //私钥
                PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
                rsaKey = KeyFactory.getInstance(RSA).generatePrivate(keySpec);
            }
            if (rsaKey == null) {
                return null;
            }
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(mode, rsaKey);
            int len = data.length;
            int offset = 0;
            int maxLen = mode == Cipher.ENCRYPT_MODE ? MAX_LENGTH_ENCRYPT : MAX_LENGTH_DECRYPT;
            outputStream = new ByteArrayOutputStream();
            byte[] buffer;
            //分段加解密
            while (len - offset > 0) {
                if (len - offset > maxLen) {
                    buffer = cipher.doFinal(data, offset, maxLen);
                } else {
                    buffer = cipher.doFinal(data, offset, len - offset);
                }
                outputStream.write(buffer, 0, buffer.length);
                offset += maxLen;
            }
            return outputStream.toByteArray();
        } catch (InvalidKeySpecException
                | NoSuchAlgorithmException
                | NoSuchPaddingException
                | InvalidKeyException
                | BadPaddingException
                | IllegalBlockSizeException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
