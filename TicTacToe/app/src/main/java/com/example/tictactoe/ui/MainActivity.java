package com.example.tictactoe.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tictactoe.R;
import com.example.tictactoe.model.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String p1Color = "#0000FF";
    private String p2Color = "#FF0000";

    private EditText nameField;
    private TextView player1Color;
    private TextView player2Color;

    private Button playButton;
    private Button player1Blue;
    private Button player1Red;
    private Button player1Green;
    private Button player1Orange;

    private RadioGroup radioGroup;
    private RadioButton selectedPlayerBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.player1Name);
        player1Color = findViewById(R.id.p1Color);
        radioGroup = findViewById(R.id.playerTypeSelection);

        player1Blue = findViewById(R.id.player1Blue);
        player1Red = findViewById(R.id.player1Red);
        player1Green = findViewById(R.id.player1Green);
        player1Orange = findViewById(R.id.player1Orange);

        playButton = findViewById(R.id.playButton);

        player1Blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1Color = setPlayerColor(p1Color, p2Color, "#0000FF");
                player1Color.setBackgroundColor(Color.parseColor(p1Color));
            }
        });
        player1Red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1Color = setPlayerColor(p1Color, p2Color, "#FF0000");
                player1Color.setBackgroundColor(Color.parseColor(p1Color));
            }
        });
        player1Green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1Color = setPlayerColor(p1Color, p2Color, "#00FF00");
                player1Color.setBackgroundColor(Color.parseColor(p1Color));
            }
        });
        player1Orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1Color = setPlayerColor(p1Color, p2Color, "#FFA500");
                player1Color.setBackgroundColor(Color.parseColor(p1Color));
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p1Name = nameField.getText().toString();
                if (p1Name.matches("")) {
                    p1Name = "Player 1";
                }
                String p2Name = "Computer";
                if (p2Name.matches("")) {
                    p2Name = "Player 2";
                }

                player2Type();

                startGame(p1Name, p1Color, p2Name, p2Color);
            }
        });

    }

    private String setPlayerColor(String currPlayerColor, String otherPlayerColor, String color) {
        if (otherPlayerColor.equals(color)) {
            color = currPlayerColor;
        }
        return color;
    }

    private void startGame(String p1Name, String p1Color, String p2Name, String p2Color) {
        Intent intent = new Intent(this, GameActivity.class);
        Resources resources = getResources();
        intent.putExtra("player1Name", p1Name);
        intent.putExtra("player1Color", p1Color);
        intent.putExtra("player2Name", p2Name);
        intent.putExtra("player2Color", p2Color);
        intent.putExtra("player2Type", player2Type());

        startActivity(intent);
    }

    private String player2Type() {
        int selectedPlayerType = radioGroup.getCheckedRadioButtonId();
        selectedPlayerBtn = findViewById(selectedPlayerType);

        return (String) selectedPlayerBtn.getText();
    }
}
