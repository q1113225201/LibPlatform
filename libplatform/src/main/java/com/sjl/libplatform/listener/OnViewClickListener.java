package com.sjl.libplatform.listener;

import android.view.View;

import com.sjl.libplatform.util.Util;

/**
 * OnViewClickListener
 *
 * @author 林zero
 * @date 2019/1/2
 */
public abstract class OnViewClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        if (!Util.isFastClick()) {
            onViewClick(v);
        }
    }

    public abstract void onViewClick(View v);
}
