package com.sjl.libplatform.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.sjl.libplatform.R;
import com.sjl.libplatform.base.PlatformActivity;

/**
 * 单个Fragment
 *
 * @author 林zero
 * @date 2019/1/16
 */
public class OneFragmentActivity extends PlatformActivity {

    @Override
    public int getContentViewLayout() {
        return R.layout.activity_one_fragment;
    }

    FrameLayout flContent;

    @Override
    public void initView() {
        flContent = findViewById(R.id.fl_content);
        findViewById(R.id.btn_load1).setOnClickListener(this);
        findViewById(R.id.btn_load2).setOnClickListener(this);
        findViewById(R.id.btn_load3).setOnClickListener(this);
        findViewById(R.id.btn_replace1).setOnClickListener(this);
        findViewById(R.id.btn_replace2).setOnClickListener(this);
        findViewById(R.id.btn_replace3).setOnClickListener(this);
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_load1:
                loadFragment("1");
                break;
            case R.id.btn_load2:
                loadFragment("2");
                break;
            case R.id.btn_load3:
                loadFragment("3");
                break;
            case R.id.btn_replace1:
                replaceFragment("1");
                break;
            case R.id.btn_replace2:
                replaceFragment("2");
                break;
            case R.id.btn_replace3:
                replaceFragment("3");
                break;
        }
    }

    private void replaceFragment(String num) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,getFragment(num)).commit();
    }

    private String lastTab = "0";

    private void loadFragment(String num) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment currentFragment = fragmentManager.findFragmentByTag(num);
        Fragment lastFragment = fragmentManager.findFragmentByTag(lastTab);
        if (lastFragment != null) {
            fragmentTransaction.hide(lastFragment);
        }
        if (currentFragment == null) {
            currentFragment = getFragment(num);
        }
        if (!currentFragment.isAdded()) {
            fragmentTransaction.add(R.id.fl_content, currentFragment, num);
        }
        fragmentTransaction.show(currentFragment).commit();
        lastTab = num;
    }

    private Fragment getFragment(String num) {
        switch (num) {
            case "1":
                return new OneFragment();
            case "2":
                return new TwoFragment();
            case "3":
                return new ThreeFragment();
        }
        return new OneFragment();
    }
}
