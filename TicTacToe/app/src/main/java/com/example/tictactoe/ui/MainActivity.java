package com.example.tictactoe.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.tictactoe.R;

public class MainActivity extends AppCompatActivity {
    private String p1Color = "#0000FF";
    private String p2Color = "#FF0000";

    private EditText player1Name;
    private EditText player2Name;
    private TextView player1Color;
    private TextView player2Color;

    private Button playButton;
    private Button player1Blue;
    private Button player1Red;
    private Button player1Green;
    private Button player1Orange;
    private Button player2Blue;
    private Button player2Red;
    private Button player2Green;
    private Button player2Orange;

    private RadioGroup playerTypeSelection;
    private RadioGroup gameMode;
    private RadioButton selectedPlayerBtn;
    private RadioButton selectedGameModeBtn;

    private RadioButton playComputerBtn;
    private RadioButton playPersonBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1Name = findViewById(R.id.player1Name);
        player2Name = findViewById(R.id.player2Name);
        player1Color = findViewById(R.id.p1Color);
        player2Color = findViewById(R.id.p2Color);

        playerTypeSelection = findViewById(R.id.playerTypeSelection);
        gameMode = findViewById(R.id.gameMode);

        playComputerBtn = findViewById(R.id.playComputerBtn);
        playPersonBtn = findViewById(R.id.playPersonBtn);

        player1Blue = findViewById(R.id.player1Blue);
        player1Red = findViewById(R.id.player1Red);
        player1Green = findViewById(R.id.player1Green);
        player1Orange = findViewById(R.id.player1Orange);

        player2Blue = findViewById(R.id.player2Blue);
        player2Red = findViewById(R.id.player2Red);
        player2Green = findViewById(R.id.player2Green);
        player2Orange = findViewById(R.id.player2Orange);

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

        player2Blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p2Color = setPlayerColor(p2Color, p1Color, "#0000FF");
                player2Color.setBackgroundColor(Color.parseColor(p2Color));
            }
        });
        player2Red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p2Color = setPlayerColor(p2Color, p1Color, "#FF0000");
                player2Color.setBackgroundColor(Color.parseColor(p2Color));
            }
        });
        player2Green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p2Color = setPlayerColor(p2Color, p1Color, "#00FF00");
                player2Color.setBackgroundColor(Color.parseColor(p2Color));
            }
        });
        player2Orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p2Color = setPlayerColor(p2Color, p1Color, "#FFA500");
                player2Color.setBackgroundColor(Color.parseColor(p2Color));
            }
        });

        playComputerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player2Name.setText("Computer");
                player2Name.setVisibility(View.INVISIBLE);
            }
        });
        playPersonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player2Name.setVisibility(View.VISIBLE);
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p1Name = player1Name.getText().toString();
                if (p1Name.matches("")) {
                    p1Name = "Player 1";
                }
                String p2Name = player2Name.getText().toString();
                if (p2Name.matches("")) {
                    p2Name = "Player 2";
                }

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
        intent.putExtra("gameMode", gameMode());

        startActivity(intent);
    }

    private String gameMode() {
        int selectedGameMode = gameMode.getCheckedRadioButtonId();
        selectedGameModeBtn = findViewById(selectedGameMode);

        return (String) selectedGameModeBtn.getText();
    }

    private String player2Type() {
        int selectedPlayerType = playerTypeSelection.getCheckedRadioButtonId();
        selectedPlayerBtn = findViewById(selectedPlayerType);

        return (String) selectedPlayerBtn.getText();

    }
}
