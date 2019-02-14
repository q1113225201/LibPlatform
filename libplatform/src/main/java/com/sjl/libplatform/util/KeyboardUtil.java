package com.sjl.libplatform.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 软键盘工具类
 *
 * @author 林zero
 * @date 2018/12/11
 */
public class KeyboardUtil {

    /**
     * 显示软键盘
     */
    public static void showKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            View view = activity.getCurrentFocus();
            if (view == null) {
                view = new View(activity);
                view.setFocusable(true);
                view.setFocusableInTouchMode(true);
                view.requestFocus();
            }
            showKeyboard(view);
        }
    }

    /**
     * 显示软键盘
     */
    public static void showKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
            }
        }
    }

    /**
     * 关闭软键盘
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            View view = activity.getCurrentFocus();
            if (view == null) {
                view = new View(activity);
            }
            hideKeyboard(view);
        }
    }

    /**
     * 关闭软键盘
     */
    public static void hideKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

}
