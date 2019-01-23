package com.sjl.libplatform.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.view.WindowManager;

/**
 * SystemUtil
 *
 * @author 林zero
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

    /**
     * 跳转设置界面
     */
    public static void toSetting(Activity activity) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        activity.startActivity(intent);
    }

}
