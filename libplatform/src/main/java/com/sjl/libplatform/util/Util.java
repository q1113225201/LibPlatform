package com.sjl.libplatform.util;

/**
 * Util
 *
 * @author 林zero
 * @date 2018/12/14
 */
public class Util {
    private static long lastClick = 0;

    /**
     * 是否快速点击
     *
     * @return
     */
    public static boolean isFastClick() {
        long current = System.currentTimeMillis();
        if (current - lastClick >= 100) {
            lastClick = current;
            return false;
        }
        return true;
    }
}
