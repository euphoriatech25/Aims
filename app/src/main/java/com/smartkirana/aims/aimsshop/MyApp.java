package com.smartkirana.aims.aimsshop;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
    private static MyApp instance;
    private static Context appContext;

    public static MyApp getInstance() {
        return instance;
    }

    public static Context getContext() {
        return appContext;
    }

    public void setAppContext(Context mAppContext) {
        this.appContext = mAppContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        this.setAppContext(getApplicationContext());
    }
}
