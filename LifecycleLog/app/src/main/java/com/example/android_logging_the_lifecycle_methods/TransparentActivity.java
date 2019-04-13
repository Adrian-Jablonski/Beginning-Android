package com.example.android_logging_the_lifecycle_methods;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.os.Bundle;

public class TransparentActivity extends LoggingActivity {

    public TransparentActivity() {
        setActivityName("TransparentActivity");}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent);
    }

}