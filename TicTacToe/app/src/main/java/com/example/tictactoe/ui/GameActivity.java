package com.example.tictactoe.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tictactoe.R;
import com.example.tictactoe.model.Board;

public class GameActivity extends AppCompatActivity {

    private String name;

    private TextView player1Name;

    private Board board = new Board();
    private Button button0, button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        player1Name = findViewById(R.id.player1Name);

        button0 = findViewById(R.id.button0);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        player1Name.setText(name);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char mark = 'X';
                button0.setBackgroundColor(Color.parseColor("#0000FF"));
                board.setBoardSpot(0, mark);
                printBoard();
            }
        });

    }

    private void printBoard() {
        System.out.println(board.getBoard());
    }
}
