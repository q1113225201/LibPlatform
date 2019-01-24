package com.sjl.libplatform.util;

import android.os.Build;

import com.blankj.ALog;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * AESUtil
 * 对称加密
 *
 * @author 沈建林
 * @date 2019/1/24
 */
public class AESUtil {
    private static final String TAG = "AESUtil";
    private static final String AES = "AES";
    private static final String SHA1PRNG = "SHA1PRNG";

    /**
     * 加密
     *
     * @param seed
     * @param data
     * @return
     */
    public static String encrypt(String seed, String data) {
        return ByteUtil.to32Hex(encrypt(seed.getBytes(), data.getBytes()));
    }

    /**
     * 加密
     *
     * @param seed
     * @param data
     * @return
     */
    public static byte[] encrypt(byte[] seed, byte[] data) {
        return doFinal(seed, data, Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密
     *
     * @param seed
     * @param data
     * @return
     */
    public static String decrypt(String seed, String data) {
        return new String(decrypt(seed.getBytes(), data.getBytes()));
    }

    /**
     * 解密
     *
     * @param seed
     * @param data
     * @return
     */
    public static byte[] decrypt(byte[] seed, byte[] data) {
        return doFinal(seed, data, Cipher.DECRYPT_MODE);
    }

    /**
     * 最终加解密
     *
     * @param seed
     * @param data
     * @param type
     * @return
     */
    private static byte[] doFinal(byte[] seed, byte[] data, int type) {
        try {
            byte[] key = generateKey(seed);
            ALog.e(TAG, type+"--------"+ByteUtil.byte2Str(key));
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, AES);
            //Cipher对象实际完成加解密操作
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(type, secretKeySpec);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException
                | NoSuchProviderException
                | NoSuchPaddingException
                | InvalidKeyException
                | BadPaddingException
                | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 秘钥处理
     *
     * @param seed
     * @return
     * @throws Exception
     */
    private static byte[] generateKey(byte[] seed) throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyGenerator generator = KeyGenerator.getInstance(AES);
        SecureRandom secureRandom;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            secureRandom = SecureRandom.getInstance(SHA1PRNG, new CryptoProvider());
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            secureRandom = SecureRandom.getInstance(SHA1PRNG, "Crypto");
        } else {
            //java直接用这个
            secureRandom = SecureRandom.getInstance(SHA1PRNG);
        }
        secureRandom.setSeed(seed);
        generator.init(128, secureRandom);
        return generator.generateKey().getEncoded();

    }

    public static class CryptoProvider extends Provider {
        /**
         * Creates a Provider and puts parameters
         */
        public CryptoProvider() {
            super("Crypto", 1.0, "HARMONY (SHA1 digest; SecureRandom; SHA1withDSA signature)");
            put("SecureRandom.SHA1PRNG",
                    "org.apache.harmony.security.provider.crypto.SHA1PRNG_SecureRandomImpl");
            put("SecureRandom.SHA1PRNG ImplementedIn", "Software");
        }
    }
}
