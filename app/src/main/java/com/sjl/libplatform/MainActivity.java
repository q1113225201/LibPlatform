package com.sjl.libplatform;

import android.os.Bundle;
import android.view.View;

import com.sjl.libplatform.base.PlatformActivity;
import com.sjl.libplatform.encrypt.EncryptActivity;
import com.sjl.libplatform.fragment.FragmentTypeActivity;
import com.sjl.libplatform.toast.ToastActivity;
import com.sjl.libplatform.util.ActivityUtil;
import com.sjl.libplatform.util.KeyboardActivity;

public class MainActivity extends PlatformActivity {

    @Override
    public int getContentViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        findViewById(R.id.btn_keyboard).setOnClickListener(this);
        findViewById(R.id.btn_fragment).setOnClickListener(this);
        findViewById(R.id.btn_toast).setOnClickListener(this);
        findViewById(R.id.btn_encrypt).setOnClickListener(this);
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_keyboard:
                ActivityUtil.startActivity(this, KeyboardActivity.class);
                break;
            case R.id.btn_fragment:
                ActivityUtil.startActivity(this, FragmentTypeActivity.class);
                break;
            case R.id.btn_toast:
                ActivityUtil.startActivity(this, ToastActivity.class);
                break;
            case R.id.btn_encrypt:
                ActivityUtil.startActivity(this, EncryptActivity.class);
                break;
        }
    }

}
