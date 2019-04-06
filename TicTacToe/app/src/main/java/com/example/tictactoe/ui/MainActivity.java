package com.example.tictactoe.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tictactoe.R;
import com.example.tictactoe.model.Player;

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
                String p1Name = nameField.getText().toString();
                String p1Color = "#0000FF";
                String p2Name = "Computer";
                String p2Color = "#FF0000";

                startGame(p1Name, p1Color, p2Name, p2Color);
            }
        });

    }

    private void startGame(String p1Name, String p1Color, String p2Name, String p2Color) {
        Intent intent = new Intent(this, GameActivity.class);
        Resources resources = getResources();
        intent.putExtra("player1Name", p1Name);
        intent.putExtra("player1Color", p1Color);
        intent.putExtra("player2Name", p2Name);
        intent.putExtra("player2Color", p2Color);

        startActivity(intent);
    }
}
