package com.sjl.libplatform.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sjl.libplatform.util.Util;

/**
 * PlatformFragment
 *
 * @author 沈建林
 * @date 2018/12/13
 */
public abstract class PlatformFragment extends Fragment implements IPlatformView, View.OnClickListener {
    /**
     * 内容视图
     */
    private View contentView;
    /**
     * 第一次初始化
     */
    private boolean isFirst;
    /**
     * 是否可见
     */
    private boolean isVisible;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (contentView == null) {
            contentView = inflater.inflate(getContentViewLayout(), container, false);
        }
        init();
        return contentView;
    }

    private void init() {
        if (isFirst) {
            isFirst = false;
            initView();
        }
        if (isVisible) {
            initData(getArguments());
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisible = isVisibleToUser;
        if (contentView == null) {
            return;
        }
        if (isVisibleToUser) {
            init();
        }
    }

    @Override
    public void onClick(View v) {
        if (!Util.isFastClick()) {
            onViewCLick(v);
        }
    }

    @Override
    public void onDestroyView() {
        if (contentView != null) {
            ((ViewGroup) contentView.getParent()).removeView(contentView);
        }
        super.onDestroyView();
    }

    public View getContentView() {
        return contentView;
    }
}
