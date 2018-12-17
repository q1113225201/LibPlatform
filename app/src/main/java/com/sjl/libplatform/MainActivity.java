package com.sjl.libplatform;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sjl.libplatform.fragment.PagerActivity;
import com.sjl.libplatform.toast.ToastActivity;
import com.sjl.libplatform.util.ActivityUtil;
import com.sjl.libplatform.util.KeyboardActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        findViewById(R.id.btn_util_keyboard).setOnClickListener(this);
        findViewById(R.id.btn_fragment).setOnClickListener(this);
        findViewById(R.id.btn_toast).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_util_keyboard:
                ActivityUtil.startActivity(this, KeyboardActivity.class);
                break;
            case R.id.btn_fragment:
                ActivityUtil.startActivity(this, PagerActivity.class);
                break;
            case R.id.btn_toast:
                ActivityUtil.startActivity(this, ToastActivity.class);
                break;
        }
    }
}
