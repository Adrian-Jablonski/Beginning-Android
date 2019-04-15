package com.example.weatherapp.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.R;
import com.example.weatherapp.weather.Current;
import com.example.weatherapp.databinding.ActivityMainBinding;
import com.example.weatherapp.weather.Forecast;
import com.example.weatherapp.weather.Hour;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// Notes: To enable data binding    // https://developer.android.com/topic/libraries/data-binding
// First: Go to build.gradle and add
//    dataBinding {
//        enabled = true
//    }
// Next go to activity_main.xml and create a layout tag at the beginning of the file. Enclose all xml code inside the layout tag and copy the xmlns:android properties into the tag
// Next add a data tag
// <data>
//      <variable
//          name="weather"
//          type = "com.example.weatherapp.Weather.Current">
//      </variable>
//  </data>
// Could use @{weather.summary} in activity_main to get the current weather data

// Next change setContentView(R.layout.activity_main); to ActivityMainBinding binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
// Next in onresponse, make a new Current object
// Then to pass the data to the xml view, use binding.setWeather(displayWeather);

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private TextView temperature;
    private TextView time;
    private TextView location;
    private TextView humidity;
    private TextView precipitation;
    private TextView summary;

    private Forecast forecast;

    private ImageView iconImageView;

    final double latitude = 29.7604;
    final double longitude = -95.3698;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        getForecast(latitude, longitude);
    }

    private void getForecast(double latitude, double longitude) {
        final ActivityMainBinding binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        TextView darkSky = findViewById(R.id.darkSkyAttribution);
        darkSky.setMovementMethod(LinkMovementMethod.getInstance());    // Enables clicking on link

        iconImageView = findViewById(R.id.iconImageView);

        String apiKey = "73bce4dfddf4d93bf253cdbb61c85400";

        String forecastURL = "https://api.darksky.net/forecast/"
                + apiKey + "/"
                + latitude + ","
                + longitude;

        if (isNetworkAvailable()) {
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
                        String jsonData = response.body().string();
                        Log.e(TAG, jsonData);
                        if (response.isSuccessful()) {
                            forecast = parseForecastData(jsonData);

                            Current current = forecast.getCurrent();

                            final Current displayWeather = new Current(
                                    current.getLocationLabel(),
                                    current.getIcon(),
                                    current.getTime(),
                                    current.getTemperature(),
                                    current.getHumidity(),
                                    current.getPrecipChance(),
                                    current.getSummary(),
                                    current.getGetTimeZone()
                            );

                            binding.setWeather(displayWeather);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Drawable drawable = getResources().getDrawable(displayWeather.getIconId());
                                    iconImageView.setImageDrawable(drawable);
                                }
                            });

                        } else {
                            alertUserAboutError();
                        }
                    } catch (IOException e) {
                        System.out.println("FAILED");
                        Log.e(TAG, "IO Exception caught: ", e);
                    } catch (JSONException e) {
                        Log.e(TAG, "JSON Exception caught: ", e);
                    }
                }
            });
        }
    }

    private Forecast parseForecastData(String jsonData) throws JSONException {
        Forecast forecast = new Forecast();

        forecast.setCurrent(getCurrentDetails(jsonData));
        forecast.setHourlyForecast(getHourlyForecast(jsonData));

        return forecast;
    }

    private Hour[] getHourlyForecast(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");

        JSONObject hourly = forecast.getJSONObject("hourly");
        JSONArray data = hourly.getJSONArray("data");

        Hour[] hours = new Hour[data.length()];

        for (int i = 0; i < data.length(); i++) {
            JSONObject jsonHour = data.getJSONObject(i);

            Hour hour = new Hour();

            hour.setSummary(jsonHour.getString("summary"));
            hour.setIcon(jsonHour.getString("icon"));
            hour.setTemperature(jsonHour.getDouble("temperature"));
            hour.setTime(jsonHour.getLong("time"));
            hour.setTimeZone(timezone);

            hours[i] = hour;
        }

        return hours;
    }

    private Current getCurrentDetails(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);

        String timezone = forecast.getString("timezone");   // timezone is the key in the JSON data

        System.out.println(timezone);

        JSONObject currently = forecast.getJSONObject("currently");

        Current current = new Current();

        current.setHumidity(currently.getDouble("humidity"));
        current.setTime(currently.getLong("time"));
        current.setIcon(currently.getString("icon"));
        current.setLocationLabel("Houston, TX");
        current.setPrecipChance(currently.getDouble("precipProbability"));
        current.setSummary(currently.getString("summary"));
        current.setTemperature(currently.getDouble("temperature"));
        current.setTimeZone(timezone);

        System.out.println(currently);
        System.out.println(current.getFormattedTime());

        return current;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();   // Need to add <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/> to AndroidManifest to avoid error

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        else {
            Toast.makeText(this, "Sorry, network is unavailable", Toast.LENGTH_LONG).show();
        }
        return isAvailable;
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error_dialog");
    }

    // This Onclick being called using android:onClick="refreshOnClick" in the xml file
    public void refreshOnClick(View view) {
        Toast.makeText(this, "Refreshing data", Toast.LENGTH_LONG).show();
        getForecast(latitude, longitude);
    }

    public void hourlyOnClick(View view) {
        List<Hour> hours = Arrays.asList(forecast.getHourlyForecast());

        Intent intent = new Intent(this, HourlyForecastActivity.class);
        intent.putExtra("HourlyList", (Serializable) hours);    // Sends data to HourlyForecastActivity. Serializable allows for an object to be sent to another activity
        startActivity(intent);
    }

}