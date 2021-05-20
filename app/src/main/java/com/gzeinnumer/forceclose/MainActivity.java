package com.gzeinnumer.forceclose;

import android.os.Bundle;
import android.widget.TextView;

import com.gzeinnumer.forceclose.base.BaseActivity;

public class MainActivity extends BaseActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //trigger Force CLose Now
        findViewById(R.id.btn).setOnClickListener(v -> textView.setText(""));
    }
}