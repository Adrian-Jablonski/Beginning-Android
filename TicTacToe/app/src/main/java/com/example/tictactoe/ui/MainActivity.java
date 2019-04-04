package com.example.tictactoe.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tictactoe.R;

public class MainActivity extends AppCompatActivity {

    private EditText nameField;
    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.player1Name);
        playButton = findViewById(R.id.playButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameField.getText().toString();
                System.out.println(name);
                startGame(name);
            }
        });

    }

    private void startGame(String name) {
        Intent intent = new Intent(this, GameActivity.class);
        Resources resources = getResources();
        String key = "name";
        intent.putExtra(key, name);
        startActivity(intent);
    }
}
