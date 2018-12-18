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
 * @author 林zero
 * @date 2018/12/13
 */
public abstract class PlatformFragment extends Fragment implements IPlatformView, View.OnClickListener {
    public String TAG = getClass().getSimpleName();
    /**
     * 内容视图
     */
    private View contentView;
    /**
     * 是否初始化过试图
     */
    private boolean isInitView = false;
    /**
     * 是否初始化过数据
     */
    private boolean isInitData = false;
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
        toInitView();
        if (isVisible && !isInitData) {
            isInitData = true;
            initData(getArguments());
        }
        return contentView;
    }

    private void toInitView() {
        if (!isInitView) {
            isInitView = true;
            initView();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            this.isVisible = true;
        }
        if (contentView == null) {
            return;
        }
        if (!isInitData && isVisible) {
            isInitData = true;
            initData(getArguments());
        }
        if (isVisible) {
            this.isVisible = false;
        }
    }

    @Override
    public void onClick(View v) {
        if (!Util.isFastClick()) {
            onViewClick(v);
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

    public <T extends View> T findViewById(int id) {
        return (T) contentView.findViewById(id);
    }
}
