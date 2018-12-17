package com.sjl.libplatform.toast;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sjl.libplatform.R;
import com.sjl.libplatform.base.PlatformActivity;
import com.sjl.libplatform.widget.ToastView;

public class ToastActivity extends PlatformActivity {
    private static final String TAG = "ToastActivity";

    @Override
    public int getContentViewLayout() {
        return R.layout.activity_toast;
    }

    @Override
    public void initView() {
        findViewById(R.id.btn_show_text).setOnClickListener(this);
        findViewById(R.id.btn_show_custom).setOnClickListener(this);
        findViewById(R.id.btn_show_text_single).setOnClickListener(this);
        findViewById(R.id.btn_show_custom_single).setOnClickListener(this);
    }

    @Override
    public void initData(Bundle bundle) {

    }

    private int cnt = 0;

    @Override
    public void onViewClick(View view) {
        cnt++;
        Log.e(TAG, "cnt=" + cnt);
        switch (view.getId()) {
            case R.id.btn_show_text:
                showTextToast(cnt + "");
                break;
            case R.id.btn_show_custom:
                showCustom(cnt + "");
                break;
            case R.id.btn_show_text_single:
                showTextToastSingle(cnt + "");
                break;
            case R.id.btn_show_custom_single:
                showCustomSingle(cnt + "");
                break;
        }
    }

    private ToastView toastViewCustom;
    private void showCustomSingle(String msg) {
        if(toastViewCustom==null){
            toastViewCustom = new ToastView(this);
        }
        View view = LayoutInflater.from(this).inflate(R.layout.layout_toast_custom,null);
        ((TextView)view.findViewById(R.id.tv_msg)).setText(msg);
        toastViewCustom.setContentView(view);
        toastViewCustom.show();
    }

    private ToastView toastView;

    private void showTextToastSingle(String msg) {
        if (toastView == null) {
            toastView = new ToastView(this, msg, Toast.LENGTH_SHORT);
        }
        toastView.setText(msg);
        toastView.show();
    }

    private void showCustom(String msg) {
        ToastView toastView = new ToastView(this);
        toastView.setGravity(Gravity.CENTER, 0, 0);
        View view = View.inflate(this, R.layout.layout_toast_custom, null);
        ((TextView) view.findViewById(R.id.tv_msg)).setText(msg);
        toastView.setContentView(view);
        toastView.show();
    }

    private void showTextToast(String msg) {
        ToastView.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
