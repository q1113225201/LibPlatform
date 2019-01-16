package com.sjl.libplatform.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sjl.libplatform.R;
import com.sjl.libplatform.base.PlatformFragment;

/**
 * OneFragment
 *
 * @author 林zero
 * @date 2019/1/16
 */
public class OneFragment extends PlatformFragment {

    @Override
    public int getContentViewLayout() {
        return R.layout.fragment_one;
    }

    private int cnt = 0;
    TextView tvText;

    @Override
    public void initView() {
        tvText = findViewById(R.id.tv_text);
        Log.e(TAG, "initView:" + 1);
        tvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cnt++;
                tvText.setText(String.format("%s\n%d", tvText.getText(), cnt));
            }
        });
    }

    @Override
    public void initData(Bundle bundle) {
        Log.e(TAG, "initData:" + 1);
        cnt++;
        tvText.setText(String.format("\ncnt=%d\n%s", cnt,tvText.getText()));
    }

    @Override
    public void onViewClick(View view) {

    }

    @Override
    public void onDestroyView() {
        Log.e(TAG, "onDestroyView:" + 1);
        super.onDestroyView();
    }
}
