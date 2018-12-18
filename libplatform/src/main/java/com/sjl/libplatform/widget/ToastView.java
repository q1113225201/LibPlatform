package com.sjl.libplatform.widget;

import android.app.Activity;
import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.sjl.libplatform.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ToastView
 *
 * @author 林zero
 * @date 2018/12/17
 */
public class ToastView {
    private static final int LENGTH_LONG = 4000;
    private static final int LENGTH_SHORT = 2000;
    private Toast toast;

    private PopupWindow popupWindow;
    private LinearLayout contentParentView;
    private View contentView;
    private View defaultView;
    private int gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
    private int offsetX;
    private int offsetY = 200;
    private boolean isShow;
    private int duration;
    private Handler mHandler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            isShow = false;
            cancel();
        }
    };
    private Activity activity;
    private CharSequence text;

    public ToastView(Activity activity) {
        this(activity, null, Toast.LENGTH_SHORT);
    }

    public ToastView(Activity activity, CharSequence text, int duration) {
        this.activity = activity;
        this.text = text;
        this.duration = duration;
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_toast_parent, null);
        contentParentView = view.findViewById(R.id.toast_parent);
    }

    public void setText(CharSequence text) {
        this.text = text;
    }

    private void initToast() {
        if (isNotificationEnabled(activity) && toast == null) {
            toast = Toast.makeText(activity, "", duration);
            toast.setDuration(duration);
            toast.setGravity(gravity, offsetX, offsetY);
            if (contentView != null) {
                toast.setView(contentParentView);
            }
        } else if (popupWindow == null) {
            popupWindow = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setFocusable(false);
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            popupWindow.setOutsideTouchable(false);
            popupWindow.setContentView(contentParentView);
        }
    }

    public static ToastView makeText(Activity activity, CharSequence text, int duration) {
        ToastView toastView = new ToastView(activity, text, duration);
        return toastView;
    }

    public void setContentView(View contentView) {
        this.contentView = contentView;
        this.defaultView = null;
    }

    public void setGravity(int gravity, int offsetX, int offsetY) {
        this.gravity = gravity;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public void show() {
        initToast();
        if (isShow) {
            mHandler.removeCallbacks(runnable);
        }
        isShow = true;
        if (isNotificationEnabled(activity)) {
            if (contentView != null) {
                contentParentView.removeAllViews();
                contentParentView.addView(contentView);
            } else {
                toast.setText(text);
            }
            toast.show();
        } else {
            if (contentView != null) {
                contentParentView.removeAllViews();
                contentParentView.addView(contentView);
            } else {
                if (defaultView == null) {
                    defaultView = LayoutInflater.from(activity).inflate(R.layout.layout_toast_default, null);
                }
                ((TextView) defaultView.findViewById(R.id.tv_default_text)).setText(text);
                popupWindow.setContentView(defaultView);
            }
            if (!popupWindow.isShowing()) {
                popupWindow.showAtLocation(activity.getWindow().getDecorView(), gravity, offsetX, offsetY);
            }
            mHandler.postDelayed(runnable, duration == Toast.LENGTH_LONG ? LENGTH_LONG : LENGTH_SHORT);
        }
    }

    public void cancel() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        mHandler.removeCallbacks(runnable);
    }

    /**
     * 检查通知栏权限有没有开启
     */
    public static boolean isNotificationEnabled(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).areNotificationsEnabled();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            ApplicationInfo appInfo = context.getApplicationInfo();
            String pkg = context.getApplicationContext().getPackageName();
            int uid = appInfo.uid;
            try {
                Class<?> appOpsClass = Class.forName(AppOpsManager.class.getName());
                Method checkOpNoThrowMethod = appOpsClass.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class);
                Field opPostNotificationValue = appOpsClass.getDeclaredField("OP_POST_NOTIFICATION");
                int value = (Integer) opPostNotificationValue.get(Integer.class);
                return (Integer) checkOpNoThrowMethod.invoke(appOps, value, uid, pkg) == 0;
            } catch (NoSuchMethodException | NoSuchFieldException | InvocationTargetException | IllegalAccessException | RuntimeException | ClassNotFoundException ignored) {
                return true;
            }
        } else {
            return true;
        }
    }
}
