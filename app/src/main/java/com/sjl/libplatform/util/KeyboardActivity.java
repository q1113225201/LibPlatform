package com.sjl.libplatform.util;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.sjl.libplatform.R;
import com.sjl.libplatform.base.PlatformActivity;

public class KeyboardActivity extends PlatformActivity implements View.OnClickListener {
    private EditText etFocus;

    @Override
    public int getContentViewLayout() {
        return R.layout.activity_keyboard;
    }

    @Override
    public void initView() {
        etFocus = findViewById(R.id.et_focus);
        findViewById(R.id.btn_view_show).setOnClickListener(this);
        findViewById(R.id.btn_view_hide).setOnClickListener(this);
        findViewById(R.id.btn_activity_show).setOnClickListener(this);
        findViewById(R.id.btn_activity_hide).setOnClickListener(this);
        findViewById(R.id.btn_toggle).setOnClickListener(this);
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_view_show:
                KeyboardUtil.showKeyboard(etFocus);
                break;
            case R.id.btn_view_hide:
                KeyboardUtil.hideKeyboard(etFocus);
                break;
            case R.id.btn_activity_show:
                KeyboardUtil.showKeyboard(this);
                break;
            case R.id.btn_activity_hide:
                KeyboardUtil.hideKeyboard(this);
                break;
            case R.id.btn_toggle:
                KeyboardUtil.toggleKeyboard(this);
                break;
        }
    }

}
