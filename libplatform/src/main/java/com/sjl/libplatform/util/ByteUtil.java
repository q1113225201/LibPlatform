package com.sjl.libplatform.util;

/**
 * ByteUtil
 *
 * @author 林zero
 * @date 2019/1/23
 */
public class ByteUtil {
    /**
     * 二进制转String
     *
     * @param bytes
     * @return
     */
    public static String byte2Str(byte[] bytes) {
        String hex = "";
        if (bytes != null) {
            for (Byte b : bytes) {
                hex += String.format("%02X", b.intValue() & 0xFF);
            }
        }
        return hex;
    }

    /**
     * 字符串转二进制
     *
     * @param s
     * @return
     */
    public static byte[] strToBytes(String s) {
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
