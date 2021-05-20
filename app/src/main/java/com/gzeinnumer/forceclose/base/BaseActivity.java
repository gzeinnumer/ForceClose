package com.gzeinnumer.forceclose.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gzeinnumer.forceclose.log.ExceptionHandler;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
    }
}
