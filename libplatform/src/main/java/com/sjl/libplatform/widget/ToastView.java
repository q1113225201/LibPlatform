package com.sjl.libplatform.widget;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.sjl.libplatform.util.Util;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ToastView
 *
 * @author 林zero
 * @date 2018/12/17
 */
public class ToastView {
    private static final int LENGTH_LONG = 3500;
    private static final int LENGTH_SHORT = 2000;
    /**
     * toast
     */
    private Toast toast;
    /**
     * 无权限时用PopupWindow
     */
    private PopupWindow popupWindow;
    /**
     * 内容容器，防止出现一跳一跳的效果
     */
    private LinearLayout contentParentView;
    /**
     * 内容视图
     */
    private View contentView;
    /**
     * 默认视图
     */
    private View defaultView;
    /**
     * toast属性
     */
    private int gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
    private int offsetX;
    private int offsetY = 200;
    private int duration;
    private CharSequence text;
    /**
     * 计时
     */
    private Timer timer;
    private TimerTask timerTask;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            ToastView.this.cancel();
            handler.removeCallbacks(runnable);
        }
    };
    /**
     * 是否显示
     */
    private boolean isShow = false;

    private Activity activity;

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

    private void initToast() {
        if (Util.isNotificationEnabled(activity)) {
            if (toast == null) {
                toast = Toast.makeText(activity, "", duration);
                toast.setDuration(duration);
                toast.setGravity(gravity, offsetX, offsetY);
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

    public void show() {
        initToast();
        startTimer();
        if (Util.isNotificationEnabled(activity)) {
            //有权限使用原生Toast
            if (contentView != null) {
                //视图
                setCustomView();
                toast.setView(contentParentView);
            } else {
                //text
                toast.setGravity(gravity, offsetX, offsetY);
                toast.setText(text);
            }
            toast.show();
        } else {
            //无权限使用PopupWindow
            if (contentView != null) {
                //视图
                setCustomView();
                popupWindow.setContentView(contentParentView);
            } else {
                //text
                ((TextView) defaultView.findViewById(R.id.tv_default_text)).setText(text);
                popupWindow.setContentView(defaultView);
            }
            if (!popupWindow.isShowing()) {
                popupWindow.showAtLocation(activity.getWindow().getDecorView(), gravity, offsetX, offsetY);
            }
        }
    }

    public boolean isShowing() {
        return isShow;
    }

    /**
     * 开始计时
     */
    private void startTimer() {
        stopTimer();
        isShow = true;
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        };
        timer.schedule(timerTask, duration == Toast.LENGTH_SHORT ? LENGTH_SHORT : LENGTH_LONG);
    }

    /**
     * 结束计时
     */
    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    /**
     * 设置View视图
     */
    private void setCustomView() {
        contentParentView.removeAllViews();
        if (contentView.getParent() != null) {
            ((ViewGroup) contentView.getParent()).removeView(contentView);
        }
        contentParentView.addView(contentView);
    }

    /**
     * 取消ToastView显示
     */
    public void cancel() {
        isShow = false;
        stopTimer();
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        if (toast != null) {
            toast.cancel();
        }
    }

    public void setText(CharSequence text) {
        this.text = text;
        this.contentView = null;
        if (this.defaultView == null) {
            this.defaultView = LayoutInflater.from(activity).inflate(R.layout.layout_toast_default, null);
        }
    }

    public void setContentView(View contentView) {
        this.contentView = contentView;
        this.defaultView = null;
        this.text = null;
    }

    public void setGravity(int gravity, int offsetX, int offsetY) {
        this.gravity = gravity;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }
}
