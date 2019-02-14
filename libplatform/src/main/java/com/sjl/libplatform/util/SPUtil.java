package com.sjl.libplatform.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * SharedPreferences工具类
 *
 * @author 林zero
 * @date 2018/9/27
 */
public class SPUtil {
    private static Application application;
    private static String name = "sp_info";

    public static void init(Application application, String name) {
        SPUtil.application = application;
        SPUtil.name = TextUtils.isEmpty(name)?SPUtil.name:name;
    }

    private static SharedPreferences getSharedPreferences() {
        if (application == null || TextUtils.isEmpty(name)) {
            throw new RuntimeException("未初始化SharedPreferences");
        }
        return application.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor() {
        return getSharedPreferences().edit();
    }

    /**
     * 获得保存的int
     */
    public static int getInt(String key) {
        return getInt(key, -1);
    }

    /**
     * 获得保存的int
     *
     * @return 默认值为-1
     */
    public static int getInt(String key, int defaultValue) {
        return getSharedPreferences().getInt(key, defaultValue);
    }

    /**
     * 保存int值
     *
     * @return 判断是否保存成功，保存成功返回true，反之返回false
     */
    public static boolean putInt(String key, int value) {
        return getEditor().putInt(key, value).commit();
    }
    /**
     * 获得保存的String
     */
    public static String getString(String key) {
        return getString(key, "");
    }

    /**
     * 获得保存的String
     *
     * @return 默认值为""
     */
    public static String getString(String key, String defaultValue) {
        return getSharedPreferences().getString(key, defaultValue);
    }

    /**
     * 保存String值
     *
     * @return 判断是否保存成功，保存成功返回true，反之返回false
     */
    public static boolean putString(String key, String value) {
        return getEditor().putString(key, value).commit();
    }

    /**
     * 获得保存的boolean值
     *
     * @return 默认值为false
     */
    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * 获得保存的boolean值
     *
     * @return 获得保存的boolean值
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        return getSharedPreferences().getBoolean(key, defaultValue);
    }

    /**
     * 保存boolean值
     *
     * @return 判断是否保存成功，保存成功返回true，反之返回false
     */
    public static boolean putBoolean(String key, boolean value) {
        return getEditor().putBoolean(key, value).commit();
    }
}
