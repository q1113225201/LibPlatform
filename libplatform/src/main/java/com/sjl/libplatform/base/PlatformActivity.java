package com.sjl.libplatform.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.sjl.libplatform.util.KeyboardUtil;
import com.sjl.libplatform.util.PermissionUtil;
import com.sjl.libplatform.util.Util;

/**
 * PlatformActivity
 *
 * @author 林zero
 * @date 2018/9/21
 */
public abstract class PlatformActivity extends AppCompatActivity implements IPlatformView, View.OnClickListener {
    public final String TAG = getClass().getSimpleName();
    /**
     * 内容视图
     */
    private View contentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(inflate());
        initView();
        initData(getIntent().getExtras());
    }

    private View inflate() {
        return contentView = LayoutInflater.from(this).inflate(getContentViewLayout(), null);
    }

    @Override
    public void onClick(View v) {
        if (!Util.isFastClick()) {
            onViewClick(v);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {
        KeyboardUtil.hideKeyboard(this);
        super.onBackPressed();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                KeyboardUtil.hideKeyboard(v);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    public Activity getActivity() {
        return this;
    }

    public View getContentView() {
        return contentView;
    }
}
