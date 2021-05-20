# ForceClose

Use Custom View if Force Close happen,

|![]()|![]()|![]()|
|---|---|---|

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

        button.setOnClickListener(v -> {
            finishAffinity();
        });
    }
}
```
[activity_some_thing_wrong.xml](https://github.com/gzeinnumer/ForceClose/blob/master/app/src/main/res/layout/activity_some_thing_wrong.xml)
