package com.sjl.libplatform.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.sjl.libplatform.R;
import com.sjl.libplatform.base.PlatformActivity;

import java.util.ArrayList;
import java.util.List;

public class PagerActivity extends PlatformActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public int getContentViewLayout() {
        return R.layout.activity_pager;
    }

    private FragmentPagerAdapter adapter;
    private List<Fragment> list;
    private String[] titles = {"全部", "必读学习", "选读学习"};

    @Override
    public void initView() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

    }

    @Override
    public void initData(Bundle bundle) {
        initFragments();
    }

    private void initFragments() {
        list = new ArrayList<>();
        list.add(PagerFragment.getInstance(0));
        list.add(PagerFragment.getInstance(1));
        list.add(PagerFragment.getInstance(2));
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        };
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onViewClick(View view) {

    }
}
