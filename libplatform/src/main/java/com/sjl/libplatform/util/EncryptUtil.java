package com.sjl.libplatform.util;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    public static String get32MD5(String str) {
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
    public static String get16MD5(String str) {
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
     * 二进制转16进制字符(16位)
     *
     * @param src
     * @return
     */
    private static String to16Hex(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().substring(8, 24);
    }

    /**
     * 二进制转16进制字符(32位)
     *
     * @param src
     * @return
     */
    private static String to32Hex(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
