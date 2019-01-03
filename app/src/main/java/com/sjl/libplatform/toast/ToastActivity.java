package com.sjl.libplatform.toast;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sjl.libplatform.R;
import com.sjl.libplatform.base.PlatformActivity;
import com.sjl.libplatform.util.ToastUtil;

public class ToastActivity extends PlatformActivity {
    private static final String TAG = "ToastActivity";

    @Override
    public int getContentViewLayout() {
        return R.layout.activity_toast;
    }

    @Override
    public void initView() {
        findViewById(R.id.btn_show_text).setOnClickListener(this);
        findViewById(R.id.btn_show_text_gravity).setOnClickListener(this);
        findViewById(R.id.btn_show_custom).setOnClickListener(this);
        findViewById(R.id.btn_show_custom_gravity).setOnClickListener(this);
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
                ToastUtil.showToast("cnt=" + cnt);
                break;
            case R.id.btn_show_text_gravity:
                ToastUtil.showToast("cnt=" + cnt, Gravity.CENTER, 0, 0);
                break;
            case R.id.btn_show_custom:
                ToastUtil.showToast(buildCustomView("cnt=" + cnt));
                break;
            case R.id.btn_show_custom_gravity:
                ToastUtil.showToast(buildCustomView("cnt=" + cnt), Gravity.CENTER, 300, 0);
                break;
        }
    }

    private View buildCustomView(String msg) {
        View view = LayoutInflater.from(this).inflate(cnt%2==0?R.layout.layout_toast_custom:R.layout.layout_toast_custom2, null);
        ((TextView) view.findViewById(R.id.tv_msg)).setText(msg);
        return view;
    }

}
