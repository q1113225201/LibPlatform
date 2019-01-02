package com.sjl.libplatform.util;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;

import com.sjl.libplatform.PlatformInit;
import com.sjl.libplatform.widget.ToastView;

import java.util.HashMap;
import java.util.Map;

/**
 * ToastUtil
 *
 * @author 沈建林
 * @date 2019/1/2
 */
public class ToastUtil {
    private static Map<Activity, ToastView> toastViewMap = new HashMap<>();

    /**
     * 获取
     *
     * @param activity
     * @return
     */
    private static ToastView getToastView(Activity activity) {
        ToastView toastView = toastViewMap.get(activity);
        if (toastView == null) {
            toastView = new ToastView(activity);
            toastView.setGravity(Gravity.CENTER, 0, 0);
            toastViewMap.put(activity, toastView);
        }
        return toastView;
    }

    public static void showToast(String msg) {
        showToast(msg, Gravity.BOTTOM, 0, 200);
    }

    public static void showToast(String msg, int gravity, int offsetX, int offsetY) {
        Activity activity = PlatformInit.getInstance().getTopActivity();
        if (activity == null) {
            throw new RuntimeException("请初始化PlatformInit");
        }
        ToastView toastView = getToastView(activity);
        toastView.setText(msg);
        toastView.setGravity(gravity, offsetX, offsetY);
        toastView.show();
    }

    public static void showToast(View view) {
        showToast(view, Gravity.BOTTOM, 0, 200);
    }

    public static void showToast(View view, int gravity, int offsetX, int offsetY) {
        Activity activity = PlatformInit.getInstance().getTopActivity();
        if (activity == null) {
            throw new RuntimeException("请初始化PlatformInit");
        }
        ToastView toastView = getToastView(activity);
        toastView.setContentView(view);
        toastView.setGravity(gravity, offsetX, offsetY);
        toastView.show();
    }
}
