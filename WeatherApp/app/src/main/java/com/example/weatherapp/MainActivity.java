package com.example.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String apiKey = "73bce4dfddf4d93bf253cdbb61c85400";

        double latitude = 37.8267;
        double longitude = -122.423;

        String forecastURL = "https://api.darksky.net/forecast/"
                + apiKey + "/"
                + latitude + ","
                + longitude;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(forecastURL)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {       // Executes call Asynchronously in the background in the order they are added to the queue
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Need to add <uses-permission android:name="android.permission.INTERNET" to AndroidManifest to prevent missing INTERNET permission error
                try {
                    if (response.isSuccessful()) {
                        System.out.println(response.body().string());
                    }
                    else {
                        System.out.println("***Else Failed");
                    }
                } catch (IOException e) {
                    System.out.println("FAILED");
                    Log.e(TAG, "IO Exception caught: ", e);
                }
            }
        });
    }
}