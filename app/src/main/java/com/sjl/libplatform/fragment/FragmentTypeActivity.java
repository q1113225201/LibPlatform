package com.sjl.libplatform.fragment;

import android.os.Bundle;
import android.view.View;

import com.sjl.libplatform.R;
import com.sjl.libplatform.base.PlatformActivity;
import com.sjl.libplatform.util.ActivityUtil;

/**
 * Fragment
 *
 * @author 林zero
 * @date 2019/1/16
 */
public class FragmentTypeActivity extends PlatformActivity {

    @Override
    public int getContentViewLayout() {
        return R.layout.activity_fragment_type;
    }

    @Override
    public void initView() {
        findViewById(R.id.btn_pager_fragment).setOnClickListener(this);
        findViewById(R.id.btn_one_fragment).setOnClickListener(this);
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pager_fragment:
                ActivityUtil.startActivity(this, PagerActivity.class);
                break;
            case R.id.btn_one_fragment:
                ActivityUtil.startActivity(this, OneFragmentActivity.class);
                break;
        }
    }
}
