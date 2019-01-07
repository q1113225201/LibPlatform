package com.sjl.libplatform;

import android.app.Application;

/**
 * App
 *
 * @author 林zero
 * @date 2019/1/2
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PlatformInit.getInstance().init(this);
    }
}
