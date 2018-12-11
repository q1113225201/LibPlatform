package com.sjl.libplatform.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * SystemUtil
 *
 * @author 沈建林
 * @date 2018/9/21
 */
public class SystemUtil {

    /**
     * 改变activity透明度
     *
     * @param activity
     * @param alpha
     */
    public static void changeAlpha(Activity activity, float alpha) {
        WindowManager.LayoutParams lp = activity.getWindow()
                .getAttributes();
        lp.alpha = alpha;
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        activity.getWindow().setAttributes(lp);
    }

    /**
     * 获取屏幕宽高
     *
     * @param context
     * @return
     */
    public static Point getScreen(Context context) {
        Point point = new Point();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getSize(point);
        return point;
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        return getScreen(context).x;
    }

}
