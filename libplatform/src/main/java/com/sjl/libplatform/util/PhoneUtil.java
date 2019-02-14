package com.sjl.libplatform.util;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * 手机设备相关工具类
 *
 * @author 林zero
 * @date 2018/12/12
 */
public class PhoneUtil {

    /**
     * 获取手机类型
     *
     * @param context
     * @return
     * @see TelephonyManager#PHONE_TYPE_NONE
     * @see TelephonyManager#PHONE_TYPE_GSM
     * @see TelephonyManager#PHONE_TYPE_CDMA
     * @see TelephonyManager#PHONE_TYPE_SIP
     */
    public static int getPhoneType(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getPhoneType();
    }

    /**
     * 是否是手机
     *
     * @param context
     * @return
     */
    public static boolean isPhone(Context context) {
        return getPhoneType(context) != TelephonyManager.PHONE_TYPE_NONE;
    }

    /**
     * 获取设备id
     * <uses-permission android:name="android.permission.READ_PHONE_STATE" />
     *
     * @param context
     * @return
     */
    @RequiresPermission(android.Manifest.permission.READ_PHONE_STATE)
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String imei = tm.getImei();
            if (!TextUtils.isEmpty(imei)) {
                return imei;
            }
            String meid = tm.getMeid();
            return TextUtils.isEmpty(meid) ? "" : meid;
        } else {
            return tm.getDeviceId();
        }
    }

    /**
     * 获取设备序列号
     * <uses-permission android:name="android.permission.READ_PHONE_STATE" />
     *
     * @return
     */
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getSerial() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Build.getSerial();
        } else {
            return Build.SERIAL;
        }
    }

    /**
     * 获取IMEI
     * <uses-permission android:name="android.permission.READ_PHONE_STATE" />
     *
     * @param context
     * @return
     */
    @RequiresPermission(android.Manifest.permission.READ_PHONE_STATE)
    public static String getImei(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return tm.getImei();
        } else {
            return tm.getDeviceId();
        }
    }

    /**
     * 获取MEID
     * <uses-permission android:name="android.permission.READ_PHONE_STATE" />
     *
     * @param context
     * @return
     */
    @RequiresPermission(android.Manifest.permission.READ_PHONE_STATE)
    public static String getMeid(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return tm.getMeid();
        } else {
            return tm.getDeviceId();
        }
    }

}
