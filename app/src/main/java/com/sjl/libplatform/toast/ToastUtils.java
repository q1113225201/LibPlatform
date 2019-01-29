package com.sjl.libplatform.toast;

import android.widget.Toast;

import com.sjl.libplatform.App;
import com.sjl.libplatform.util.Util;

/**
 * ToastUtil
 *
 * @author 林zero
 * @date 2019/1/29
 */
public class ToastUtils {
    private static Toast toast;

    private synchronized static void initToast() {
        toast = Toast.makeText(App.getApp(), "", Toast.LENGTH_SHORT);
    }

    public static void showToast(String msg) {
        if (toast == null) {
            initToast();
        }
        toast.setText(msg);
        Util.show(App.getApp(), toast);
    }
}
