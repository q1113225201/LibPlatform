package com.sjl.libplatform.util.encrypt;

import com.sjl.libplatform.util.ByteUtil;

import java.security.InvalidKeyException;
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
 * DESUtil
 * 对称加密
 *
 * @author 林zero
 * @date 2019/1/24
 */
public class DESUtil {

    /**
     * DES加密
     *
     * @param key
     * @param data
     * @return
     */
    public static String encrypt(String key, String data) {
        return ByteUtil.to32Hex(encrypt(key.getBytes(), data.getBytes()));
    }

    /**
     * DES加密
     *
     * @param key
     * @param data
     * @return
     */
    public static byte[] encrypt(byte[] key, byte[] data) {
        return doFinal(key, data, Cipher.ENCRYPT_MODE);
    }

    /**
     * DES解密
     *
     * @param key
     * @param data
     * @return
     */
    public static String decrypt(String key, String data) {
        return new String(decrypt(key.getBytes(), ByteUtil.strToBytes(data)));
    }

    /**
     * DES解密
     *
     * @param key
     * @param data
     * @return
     */
    public static byte[] decrypt(byte[] key, byte[] data) {
        return doFinal(key, data, Cipher.DECRYPT_MODE);
    }

    /**
     * 真正加解密方法
     *
     * @param key
     * @param data
     * @param mode 加解密类型 Cipher.DECRYPT_MODE Cipher.ENCRYPT_MODE
     * @return
     */
    private static byte[] doFinal(byte[] key, byte[] data, int mode) {
        try {
            //生成可信任随机数源
            SecureRandom secureRandom = new SecureRandom();
            //创建DESKeySpec
            DESKeySpec desKeySpec = new DESKeySpec(key);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            //Cipher对象实际完成加解密操作
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(mode, secretKey, secureRandom);
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
}
