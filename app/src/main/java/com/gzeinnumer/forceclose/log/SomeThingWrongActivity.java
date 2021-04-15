package com.gzeinnumer.forceclose.log;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gzeinnumer.forceclose.BuildConfig;
import com.gzeinnumer.forceclose.R;

public class SomeThingWrongActivity extends AppCompatActivity {

    public static String DATA = "data_error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_some_thing_wrong);

        TextView textView = findViewById(R.id.error);
        Button button = findViewById(R.id.btn);

        if (BuildConfig.DEBUG) textView.setVisibility(View.VISIBLE);
        textView.setText(getIntent().getStringExtra(DATA));

        button.setOnClickListener(v -> {
            finishAffinity();
        });
    }
}