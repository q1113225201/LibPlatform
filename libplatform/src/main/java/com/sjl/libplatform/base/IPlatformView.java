package com.sjl.libplatform.base;

import android.content.Intent;

/**
 * 平台View
 *
 * @author 沈建林
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
    void initData(Intent intent);
}
