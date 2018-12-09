package com.uuevc.libplatform;

import android.app.Activity;
import android.app.Application;

import com.uuevc.libplatform.utils.SPUtil;

import java.util.Stack;

/**
 * PlatformInit
 *
 * @author 沈建林
 * @date 2018/9/21
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

    public void init(Application application) {
        this.application = application;
        //初始化SPUtil
        SPUtil.init(application,null);
    }

    /**
     * Activity栈
     */
    private Stack<Activity> activityStack = new Stack<>();

    public Stack<Activity> getActivitys() {
        return activityStack;
    }

    public void pushActivity(Activity activity) {
        activityStack.push(activity);
    }

    public void popActivity(Activity activity) {
        if (activityStack.contains(activity)) {
            activityStack.remove(activity);
        }
    }

    public void clearActivity() {
        for (Activity activity : activityStack) {
            if (activity != null) {
                activity.finish();
            }
        }
    }
}
