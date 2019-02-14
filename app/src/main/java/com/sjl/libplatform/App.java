package com.sjl.libplatform;

import android.app.Application;

/**
 * App
 *
 * @author 林zero
 * @date 2019/1/2
 */
public class App extends Application {
    private static App app;

    public static Application getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //初始化
        PlatformInit.getInstance().init(this);
    }
}
