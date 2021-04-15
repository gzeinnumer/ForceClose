package com.gzeinnumer.forceclose;

import android.app.Application;

import com.gzeinnumer.lea.utils.MBUtilsLogError;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            String appName = "TestA";
            String logLocation = "/logs";
            MBUtilsLogError.initFileLogError(appName, logLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
