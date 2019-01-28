package com.sjl.libplatform.encrypt;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sjl.libplatform.R;
import com.sjl.libplatform.base.PlatformActivity;
import com.sjl.libplatform.util.Base64Util;
import com.sjl.libplatform.util.ClipboardUtil;
import com.sjl.libplatform.util.ToastUtil;
import com.sjl.libplatform.util.encrypt.AESUtil;
import com.sjl.libplatform.util.encrypt.DESUtil;
import com.sjl.libplatform.util.encrypt.MD5Util;
import com.sjl.libplatform.util.encrypt.RSAUtil;

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
        findViewById(R.id.tv_des_init).setOnClickListener(this);
        findViewById(R.id.btn_des_encrypt).setOnClickListener(this);
        findViewById(R.id.btn_des_decrypt).setOnClickListener(this);
        findViewById(R.id.tv_aes_init).setOnClickListener(this);
        findViewById(R.id.btn_aes_encrypt).setOnClickListener(this);
        findViewById(R.id.btn_aes_decrypt).setOnClickListener(this);
        findViewById(R.id.btn_rsa_encrypt_public).setOnClickListener(this);
        findViewById(R.id.btn_rsa_decrypt_private).setOnClickListener(this);
        findViewById(R.id.btn_rsa_encrypt_private).setOnClickListener(this);
        findViewById(R.id.btn_rsa_decrypt_public).setOnClickListener(this);
    }

    private String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCW//vGM9O+nkIuexZgwuSu15U4PnP9CI4ozgP9aMPAua1u/m5i9EPpL1Ihc+Rwq7OGWRm0VBrnzkWpuBXd1JHa0DBouX+FpgPyW77GJRXjHhbXNNbljfq1723QUQufW8CIYrT56aTqt2Ou3OGzWynfu2EXZHY+7L+sAly/yDuflQIDAQAB";
    private String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJb/+8Yz076eQi57FmDC5K7XlTg+c/0IjijOA/1ow8C5rW7+bmL0Q+kvUiFz5HCrs4ZZGbRUGufORam4Fd3UkdrQMGi5f4WmA/JbvsYlFeMeFtc01uWN+rXvbdBRC59bwIhitPnppOq3Y67c4bNbKd+7YRdkdj7sv6wCXL/IO5+VAgMBAAECgYBClaCNVt9BSAIBAw/sHSTAIJpRUeDNrE8bmTTTMrZXLslWj2VXx0SG9/HshHEwadZhILsf9JJks2WuoFksF4c023nj9Uo1dlqC2IWODzPHEb0Hv7R+5vAbzrYQ0AGAkLbVoyZFLLqHlcYQ5p1TfhwfgBSWvWK2x07kEHpJ1B2jgQJBAMg+ZnVq8P+UqFPD9ZMr1U/ayoYAOZ9rNBuRxHmw6uonlFSpQY2wfG0yJVgks8Jzdcxg0uS06hyyyn0r1QdbWiECQQDBC23ArD+IhR+bmiDrDKBB8U58XGt3FjOyn7Kr6ynH236r/hfPEqTog7zNhqsuPM8BrsWDeMLzD9GSYxRibp71AkEAtAqO+vG9YD8cHbyqV7nooFT7FQKszK6J+mPUwQmWHhSaJIjOHQRay59zvk25ppNDjhBcbjBP8p+6ulKnxmaXoQJAWDbcxHrx3XTX6McZ2889PGUEtJeXFCg1H58SirU/tnB7BiVqw9mFOitZD6JGfy6SefZfk6CPlXrZDcfw/LoIMQJAXnutgXkHV/qplupVmTup1PH8gJDz9ijLDSj4r5l+KiJ5TA6znq6uHT1dTRL7HrVyAn3JyMjdol8pITIxHWTg6w==";

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
            case R.id.tv_des_init:
                //DES数据初始化
                etData.setText("12345678");
                etKey.setText("12345678");
                break;
            case R.id.btn_des_encrypt:
                tvData.setText(DESUtil.encrypt(etKey.getText().toString(), etData.getText().toString()));
                break;
            case R.id.btn_des_decrypt:
                tvData.setText(DESUtil.decrypt(etKey.getText().toString(), etData.getText().toString()));
                break;
            case R.id.tv_aes_init:
                //AES数据初始化
                etData.setText("12345678123456781234567812345678");
                etKey.setText("12345678123456781234567812345678");
                break;
            case R.id.btn_aes_encrypt:
                tvData.setText(AESUtil.encrypt(etKey.getText().toString(), etData.getText().toString()));
                break;
            case R.id.btn_aes_decrypt:
                tvData.setText(AESUtil.decrypt(etKey.getText().toString(), etData.getText().toString()));
                break;
            case R.id.btn_rsa_encrypt_public:
                tvData.setText(RSAUtil.encryptPublic(publicKey, etData.getText().toString()));
                break;
            case R.id.btn_rsa_decrypt_private:
                tvData.setText(RSAUtil.decryptPrivate(privateKey, etData.getText().toString()));
                break;
            case R.id.btn_rsa_encrypt_private:
                tvData.setText(RSAUtil.encryptPrivate(privateKey, etData.getText().toString()));
                break;
            case R.id.btn_rsa_decrypt_public:
                tvData.setText(RSAUtil.decryptPublic(publicKey, etData.getText().toString()));
                break;
        }
    }

}
