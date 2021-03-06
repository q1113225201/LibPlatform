package com.sjl.libplatform.base;

import android.os.Bundle;
import android.view.View;

/**
 * 平台View
 *
 * @author 林zero
 * @date 2018/12/10
 */
public interface IPlatformView {
    /**
     * 布局layout
     */
    int getContentViewLayout();

    /**
     * 初始化界面
     */
    void initView();

    /**
     * 初始化数据
     */
    void initData(Bundle bundle);

    /**
     * 点击事件
     * @param view
     */
    void onViewClick(View view);
}
