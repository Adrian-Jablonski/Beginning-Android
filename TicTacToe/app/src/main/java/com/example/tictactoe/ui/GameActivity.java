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
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        player1Name = findViewById(R.id.player1Name);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        player1Name.setText(name);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeMarkOnBoard(v, 0);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeMarkOnBoard(v, 1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeMarkOnBoard(v, 2);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeMarkOnBoard(v, 3);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeMarkOnBoard(v, 4);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeMarkOnBoard(v, 5);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeMarkOnBoard(v, 6);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeMarkOnBoard(v, 7);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeMarkOnBoard(v, 8);
            }
        });
    }

    private void placeMarkOnBoard(View v, int i) {
        char mark = board.getMark();
        System.out.println("Clicked Spot");
        if (board.getBoardSpot(i) == 'E') {
            System.out.println("Clicked valid spot");
            board.setBoardSpot(i, mark);
            v.setBackgroundColor(Color.parseColor(board.getMarkColor()));
            printBoard();
            board.checkWinner();
        }

    }



    private void printBoard() {
        System.out.println(board.getBoard());
    }
}
