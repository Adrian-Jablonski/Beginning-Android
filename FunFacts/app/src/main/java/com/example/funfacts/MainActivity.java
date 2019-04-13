package com.example.funfacts;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private static final String KEY_FACT = "KEY_FACT";
    private static final String KEY_COLOR = "KEY_COLOR";
    private FactBook factBook = new FactBook();
    private ColorWheel colorWheel = new ColorWheel();
    // Declare our View variables
    private TextView factTextView;
    private Button showFactButton;
    private RelativeLayout relativeLayout;
    private String mFact = factBook.facts[0];   // initializing with values to prevent null values from shows on screen orientation if user changes orientation before pressing button
    private int mColor = Color.parseColor(colorWheel.colors[8]);

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Saves instance states for orientation changes
        outState.putString(KEY_FACT, mFact);
        outState.putInt(KEY_COLOR, mColor);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // retrieves saved instance fields
        mFact = savedInstanceState.getString(KEY_FACT);
        factTextView.setText(mFact);
        mColor = savedInstanceState.getInt(KEY_COLOR);
        relativeLayout.setBackgroundColor(mColor);
        showFactButton.setTextColor(mColor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign the Views from the layout file to the corresponding variable. (Need to set views after the call to setContentView)
        factTextView = findViewById(R.id.factTextView);     // Selected field value by ID
        showFactButton = findViewById(R.id.showFactButton);
        relativeLayout = findViewById(R.id.appLayout);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The button was clicked, so update the fact TextView with a new fact

                // Update the screen with our new fact
                mFact = factBook.getFact();     // Stores instant state
                factTextView.setText(mFact);

                // Update background color of screen;
                mColor = colorWheel.getColor();     // Stores instant state
                relativeLayout.setBackgroundColor(mColor);
                showFactButton.setTextColor(mColor);

            }
        };
        showFactButton.setOnClickListener(listener);

//        // Toasts display a short message for the user to see in the app that does not require any user input
//        Toast.makeText(this, "Yay! Our Activity was created!", Toast.LENGTH_SHORT).show();

        Log.d(TAG, "We're logging from the onCreate() method");

    }
}
