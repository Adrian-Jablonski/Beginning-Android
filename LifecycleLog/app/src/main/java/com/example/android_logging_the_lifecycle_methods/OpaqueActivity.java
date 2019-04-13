package com.example.android_logging_the_lifecycle_methods;

import android.os.Bundle;

public class OpaqueActivity extends LoggingActivity {

    public OpaqueActivity() {
        setActivityName("OpaqueActivity");}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opaque);
    }

}
