package com.sjl.libplatform.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sjl.libplatform.R;
import com.sjl.libplatform.base.PlatformFragment;

public class PagerFragment extends PlatformFragment {

    public static PagerFragment getInstance(int type) {
        PagerFragment fragment = new PagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    private int cnt = 0;
    private int type;

    @Override
    public int getContentViewLayout() {
        return R.layout.fragment_pager;
    }

    private TextView tvName;

    @Override
    public void initView() {
        type = getArguments().getInt("type");
        Log.e(TAG,"initView:"+getArguments().getInt("type"));
        tvName = findViewById(R.id.tv_name);
        findViewById(R.id.tv_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"cnt="+cnt);
                cnt++;
                tvName.setText(tvName.getText() + "\n" + cnt);
            }
        });
    }

    @Override
    public void initData(Bundle bundle) {
        Log.e(TAG,"initData:"+bundle.getInt("type"));
        tvName.setText(tvName.getText() + "," + bundle.getInt("type") + "");
    }

    @Override
    public void onViewClick(View view) {

    }

    @Override
    public void onDestroyView() {
        Log.e(TAG,"onDestroyView:"+type);
        super.onDestroyView();
    }
}
