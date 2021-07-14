package com.gzeinnumer.forceclose.log;

import java.io.PrintWriter;
import java.io.StringWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.gzeinnumer.forceclose.BuildConfig;

public class ExceptionHandler implements java.lang.Thread.UncaughtExceptionHandler {
    private final Activity myContext;
    private final String LINE_SEPARATOR = "\n";

    public ExceptionHandler(Activity context) {
        myContext = context;
    }

    private void reportFatalCrash(Throwable exception) {
//        FirebaseApp firebaseApp = FirebaseApp.getInstance();
//        if (firebaseApp != null) {
//            try {
//                FirebaseCrash.getInstance(firebaseApp)
//                        .zzg(exception); // Reports the exception as fatal.
//            } catch (com.google.firebase.crash.internal.zzb zzb) {
//                Log.d(getClass().getSimpleName(), "reportFatalCrash: Internal firebase crash reporting error "+zzb);
//
//            } catch (Throwable t) {
//                Log.d(getClass().getSimpleName(), "Unknown error during firebase crash reporting "+t);
//            }
//        } else Log.d(getClass().getSimpleName(), "no FirebaseApp!!");
    }

    public void uncaughtException(Thread thread, Throwable exception) {
        if (BuildConfig.DEBUG) exception.printStackTrace();
        reportFatalCrash(exception);

        StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));
        StringBuilder errorReport = new StringBuilder();
        errorReport.append("************ CAUSE OF ERROR ************\n\n");
        errorReport.append(stackTrace.toString());
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("************ DEVICE INFORMATION ***********\n");
        errorReport.append("Brand: ");
        errorReport.append(Build.BRAND);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("Device: ");
        errorReport.append(Build.DEVICE);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("Model: ");
        errorReport.append(Build.MODEL);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("Id: ");
        errorReport.append(Build.ID);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("Product: ");
        errorReport.append(Build.PRODUCT);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("************ FIRMWARE ************\n");
        errorReport.append("SDK: ");
        errorReport.append(Build.VERSION.SDK);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("Release: ");
        errorReport.append(Build.VERSION.RELEASE);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("Incremental: ");
        errorReport.append(Build.VERSION.INCREMENTAL);
        errorReport.append(LINE_SEPARATOR);

        Intent intent = new Intent(myContext, SomeThingWrongActivity.class);
        intent.putExtra(SomeThingWrongActivity.DATA, errorReport.toString());
        myContext.startActivity(intent);

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }
}
