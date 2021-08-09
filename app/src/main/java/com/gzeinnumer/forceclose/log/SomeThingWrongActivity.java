package com.gzeinnumer.forceclose.log;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
        
        //String[] data = new String[]{getIntent().getStringExtra(DATA)};

        //buat file dalam folder App
        //   /storage/emulated/0/MyLibsTesting/MyFile.txt 
        //try {
        //    String fileName = "/"+ FunctionGLobal.getCurrentTime()+".txt";
        //    String saveTo = "/LogsError";
        //    FGFile.initFile(fileName, saveTo,data);
        //} catch (Exception e){
        //    e.printStackTrace();
        //}

        button.setOnClickListener(v -> {
            finishAffinity();
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
