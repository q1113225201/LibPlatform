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
     * @param str
     * @return
     */
    public static byte[] strToBytes(String str) {
        int length = str.length();
        byte[] data = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            data[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4)
                    + Character.digit(str.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * 二进制转16进制字符(16位)
     *
     * @param bytes
     * @return
     */
    public static String to16Hex(byte[] bytes) {
        return to32Hex(bytes).substring(8, 24);
    }

    /**
     * 二进制转16进制字符(32位)
     *
     * @param bytes
     * @return
     */
    public static String to32Hex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bytes != null) {
            for (Byte b : bytes) {
                stringBuilder.append(String.format("%02X", b.intValue() & 0xFF));
            }
        }
        return stringBuilder.toString();
    }

}
