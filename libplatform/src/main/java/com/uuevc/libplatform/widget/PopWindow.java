package com.uuevc.libplatform.widget;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.uuevc.libplatform.R;
import com.uuevc.libplatform.utils.SystemUtil;

/**
 * PopWindow
 *
 * @author 林zero
 * @date 2018/3/18
 */

public class PopWindow {
    private Activity activity;
    private int layoutId;
    private int animationStyle = R.style.PopAnim;
    private int width = ViewGroup.LayoutParams.WRAP_CONTENT;
    private int height = ViewGroup.LayoutParams.WRAP_CONTENT;
    private PopWindowListener popWindowListener;
    private PopupWindow popupWindow;

    public PopWindow(Activity activity, int layoutId, int width, int height, int animationStyle, PopWindowListener popWindowListener) {
        this.activity = activity;
        this.layoutId = layoutId;
        this.width = width;
        this.height = height;
        this.animationStyle = animationStyle;
        this.popWindowListener = popWindowListener;
        initPopupWindow();
    }

    private void initPopupWindow() {
        if (popupWindow == null) {
            View view = LayoutInflater.from(activity).inflate(layoutId, null);
            popupWindow = new PopupWindow(view, width, height);
            popupWindow.setAnimationStyle(animationStyle);
            popupWindow.setFocusable(true);
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            popupWindow.setOutsideTouchable(true);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    if (popWindowListener != null) {
                        popWindowListener.onDismiss();
                    }
                    SystemUtil.changeAlpha(activity, 1f);
                }
            });
            if (popWindowListener != null) {
                popWindowListener.onInit(PopWindow.this, view);
            }
        }
    }

    public void setAnimationStyle(int animationStyle) {
        this.animationStyle = animationStyle;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void showAsDropDown(View view) {
        showAsDropDown(view, 0.3f);
    }

    public void showAsDropDown(View view, float alpha) {
        initPopupWindow();
        popupWindow.showAsDropDown(view);
        SystemUtil.changeAlpha(activity, alpha);
    }

    public void showAtLocation(View parent, int gravity, int x, int y) {
        initPopupWindow();
        popupWindow.showAtLocation(parent, gravity, x, y);
        SystemUtil.changeAlpha(activity, 0.3f);
    }

    public void dismiss() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    public boolean isShowing() {
        return popupWindow != null && popupWindow.isShowing();
    }

    public PopupWindow getPopupWindow() {
        return popupWindow;
    }

    public interface PopWindowListener {
        void onInit(PopWindow popWindow, View view);

        void onDismiss();
    }
}
