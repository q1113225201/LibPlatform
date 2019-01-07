package com.sjl.libplatform;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.sjl.libplatform.util.SPUtil;
import com.sjl.libplatform.util.ToastUtil;

import java.util.Stack;

/**
 * PlatformInit
 *
 * @author 林zero
 * @date 2018/12/9
 */
public class PlatformInit {
    private static final String TAG = "PlatformInit";
    private static PlatformInit platformInit;

    public static PlatformInit getInstance() {
        synchronized (TAG) {
            if (platformInit == null) {
                platformInit = new PlatformInit();
            }
        }
        return platformInit;
    }

    private Application application;
    private Activity topActivity;

    public void init(Application application) {
        this.application = application;
        //初始化SPUtil
        SPUtil.init(application, null);
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                topActivity = activity;
                activityStack.push(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                topActivity = activity;
            }

            @Override
            public void onActivityResumed(Activity activity) {
                topActivity = activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ToastUtil.removeToastView(activity);
                if (activityStack.contains(activity)) {
                    activityStack.remove(activity);
                }
            }
        });
    }

    /**
     * Activity栈
     */
    private Stack<Activity> activityStack = new Stack<>();

    public Stack<Activity> getActivitys() {
        return activityStack;
    }

    public void clearActivity() {
        for (Activity activity : activityStack) {
            if (activity != null) {
                activity.finish();
            }
        }
    }

    public Activity getTopActivity() {
        return topActivity;
    }
}
