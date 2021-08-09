# ForceClose

Use Custom View if Force Close happen,

|![](https://github.com/gzeinnumer/ForceClose/blob/master/preview/example1.gif)|![](https://github.com/gzeinnumer/ForceClose/blob/master/preview/example2.gif)|![](https://github.com/gzeinnumer/ForceClose/blob/master/preview/example3.gif)|
|---|---|---|
|Default dialog error|Error screen that you can customize|Error screen with log|

- Manifest
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest >
    ...

    <application>
        ...
        <activity android:name=".log.SomeThingWrongActivity" />

    </application>

</manifest>
```

- BaseActivity.java
```java
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //add this code to BaseActivity to use Custom Force Close Screen
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
    }
}
```

- MainActivity.java
```java
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
```

- log/ExceptionHandler.java
```java
public class ExceptionHandler implements java.lang.Thread.UncaughtExceptionHandler {
    private final Activity myContext;
    private final String LINE_SEPARATOR = "\n";

    public ExceptionHandler(Activity context) {
        myContext = context;
    }

    public void uncaughtException(Thread thread, Throwable exception) {
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
```

- log/SomeThingWrongActivity.java
```java
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
```
[activity_some_thing_wrong.xml](https://github.com/gzeinnumer/ForceClose/blob/master/app/src/main/res/layout/activity_some_thing_wrong.xml)

---

```
Copyright 2021 M. Fadli Zein
```
