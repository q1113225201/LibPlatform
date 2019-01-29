package com.sjl.libplatform.toast;

import android.os.Bundle;
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
        findViewById(R.id.btn_show_view).setOnClickListener(this);
        findViewById(R.id.btn_show_view_gravity).setOnClickListener(this);
        findViewById(R.id.btn_show).setOnClickListener(this);
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public void onViewClick(View view) {

    }

    private int cnt = 0;

    @Override
    public void onClick(View view) {
        cnt++;
        switch (view.getId()) {
            case R.id.btn_show_text:
                ToastUtil.showToast("cnt=" + cnt);
                break;
            case R.id.btn_show_text_gravity:
                ToastUtil.showToast("cnt=" + cnt, Gravity.CENTER, 0, 0);
                break;
            case R.id.btn_show_view:
                ToastUtil.showToast(buildCustomView("cnt=" + cnt));
                break;
            case R.id.btn_show_view_gravity:
                ToastUtil.showToast(buildCustomView("cnt=" + cnt), Gravity.CENTER, 300, 0);
                break;
            case R.id.btn_show:
                ToastUtils.showToast("cnt=" + cnt);
                break;
        }
    }

    private View buildCustomView(String msg) {
        View view = LayoutInflater.from(this).inflate(cnt % 2 == 0 ? R.layout.layout_toast_view1 : R.layout.layout_toast_view2, null);
        ((TextView) view.findViewById(R.id.tv_msg)).setText(msg);
        return view;
    }

}
