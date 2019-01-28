package com.sjl.libplatform.util.encrypt;

import android.support.annotation.NonNull;

import com.sjl.libplatform.util.ByteUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5Util
 *
 * @author 林zero
 * @date 2019/1/24
 */
public class MD5Util {
    /**
     * 32位MD5加密
     *
     * @param str
     * @return
     */
    public static String encrypt32MD5(@NonNull String str) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            return ByteUtil.to32Hex(bytes);
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
    public static String encrypt16MD5(@NonNull String str) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            return ByteUtil.to16Hex(bytes);
        } catch (NoSuchAlgorithmException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件加密
     *
     * @param file
     * @return
     */
    public static String encryptMD5(@NonNull File file) {
        if (!file.isFile()) {
            return null;
        }
        FileInputStream fileInputStream = null;
        byte[] buffer = new byte[1024];
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            fileInputStream = new FileInputStream(file);
            int len;
            while ((len = fileInputStream.read(buffer, 0, 1024)) != 1) {
                digest.update(buffer, 0, len);
            }
            return ByteUtil.to32Hex(digest.digest());
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
