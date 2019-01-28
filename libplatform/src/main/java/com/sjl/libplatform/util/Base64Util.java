package com.sjl.libplatform.util;

import android.util.Base64;

/**
 * Base64Util
 *
 * @author 林zero
 * @date 2019/1/28
 */
public class Base64Util {
    public static byte[] encode(String input) {
        return Base64.encode(input.getBytes(), Base64.NO_WRAP);
    }

    public static byte[] encode(byte[] input) {
        return Base64.encode(input, Base64.NO_WRAP);
    }

    public static String encodeToString(String input) {
        return Base64.encodeToString(input.getBytes(), Base64.NO_WRAP);
    }

    public static String encodeToString(byte[] input) {
        return Base64.encodeToString(input, Base64.NO_WRAP);
    }

    public static byte[] decode(String input) {
        return Base64.decode(input, Base64.NO_WRAP);
    }

    public static byte[] decode(byte[] input) {
        return Base64.decode(input, Base64.NO_WRAP);
    }
}
