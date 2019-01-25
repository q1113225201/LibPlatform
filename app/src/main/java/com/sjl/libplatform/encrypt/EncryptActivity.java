package com.sjl.libplatform.encrypt;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sjl.libplatform.R;
import com.sjl.libplatform.base.PlatformActivity;
import com.sjl.libplatform.util.AESUtil;
import com.sjl.libplatform.util.ClipboardUtil;
import com.sjl.libplatform.util.DESUtil;
import com.sjl.libplatform.util.MD5Util;
import com.sjl.libplatform.util.ToastUtil;

public class EncryptActivity extends PlatformActivity {
    EditText etData;
    EditText etKey;
    TextView tvData;

    @Override
    public int getContentViewLayout() {
        return R.layout.activity_encrypt;
    }

    @Override
    public void initView() {
        etData = findViewById(R.id.et_data);
        etKey = findViewById(R.id.et_key);
        tvData = findViewById(R.id.tv_data);
        findViewById(R.id.tv_data).setOnClickListener(this);
        findViewById(R.id.btn_paste).setOnClickListener(this);
        findViewById(R.id.btn_md5_16_encrypt).setOnClickListener(this);
        findViewById(R.id.btn_md5_32_encrypt).setOnClickListener(this);
        findViewById(R.id.btn_des_encrypt).setOnClickListener(this);
        findViewById(R.id.btn_des_decrypt).setOnClickListener(this);
        findViewById(R.id.btn_aes_encrypt).setOnClickListener(this);
        findViewById(R.id.btn_aes_decrypt).setOnClickListener(this);
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_data:
                ClipboardUtil.copyText(this, tvData.getText());
                ToastUtil.showToast("复制成功");
                break;
            case R.id.btn_paste:
                etData.setText(ClipboardUtil.pasteText(this));
                break;
            case R.id.btn_md5_16_encrypt:
                tvData.setText(MD5Util.encrypt16MD5(etData.getText().toString()));
                break;
            case R.id.btn_md5_32_encrypt:
                tvData.setText(MD5Util.encrypt32MD5(etData.getText().toString()));
                break;
            case R.id.btn_des_encrypt:
                tvData.setText(DESUtil.encrypt(etKey.getText().toString(), etData.getText().toString()));
                break;
            case R.id.btn_des_decrypt:
                tvData.setText(DESUtil.decrypt(etKey.getText().toString(), etData.getText().toString()));
                break;
            case R.id.btn_aes_encrypt:
                tvData.setText(AESUtil.encrypt(etKey.getText().toString(), etData.getText().toString()));
                break;
            case R.id.btn_aes_decrypt:
                tvData.setText(AESUtil.decrypt(etKey.getText().toString(), etData.getText().toString()));
                break;
        }
    }

}
