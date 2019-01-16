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
//        Log.e(TAG,"onCreateView");
        toInitView();
        if (isVisible && !isInitData) {
//            Log.e(TAG,"onCreateView  isVisible && !isInitData");
            isInitData = true;
            initData(getArguments());
        }
        return contentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Log.e(TAG,"onViewCreated");
        if (!isInitData) {
//            Log.e(TAG,"onViewCreated  isVisible && !isInitData");
            isInitData = true;
            initData(getArguments());
        }
    }

    private void toInitView() {
//        Log.e(TAG,"toInitView");
        if (!isInitView) {
//            Log.e(TAG,"toInitView !isInitView");
            isInitView = true;
            initView();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        Log.e(TAG,"setUserVisibleHint");
        if (isVisibleToUser) {
//            Log.e(TAG,"setUserVisibleHint isVisibleToUser");
            this.isVisible = true;
        }
        if (contentView == null) {
//            Log.e(TAG,"setUserVisibleHint contentView == null");
            return;
        }
        if (!isInitData && isVisible) {
//            Log.e(TAG,"setUserVisibleHint !isInitData && isVisible");
            isInitData = true;
            initData(getArguments());
        }
        if (isVisible) {
//            Log.e(TAG,"setUserVisibleHint isVisible");
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
