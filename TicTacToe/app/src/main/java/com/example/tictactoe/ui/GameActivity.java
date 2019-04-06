package com.example.tictactoe.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.example.tictactoe.R;
import com.example.tictactoe.model.Board;
import com.example.tictactoe.model.Player;

public class GameActivity extends AppCompatActivity {

    private TextView player1Name;
    private TextView player2Name;
    private TextView player1Score;
    private TextView player2Score;
    private TextView gameMessage;
    private TextView player1Color;
    private TextView player2Color;

    private Board board = new Board();

    private Button[] buttons = new Button[9];

    private Player player1, player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        player1Name = findViewById(R.id.player1Name);
        player2Name = findViewById(R.id.player2Name);
        player1Score = findViewById(R.id.player1Score);
        player2Score = findViewById(R.id.player2Score);
        gameMessage = findViewById(R.id.gameMessage);
        player1Color = findViewById(R.id.player1Color);
        player2Color = findViewById(R.id.player2Color);

        buttons[0] = findViewById(R.id.button0);
        buttons[1] = findViewById(R.id.button1);
        buttons[2] = findViewById(R.id.button2);
        buttons[3] = findViewById(R.id.button3);
        buttons[4] = findViewById(R.id.button4);
        buttons[5] = findViewById(R.id.button5);
        buttons[6] = findViewById(R.id.button6);
        buttons[7] = findViewById(R.id.button7);
        buttons[8] = findViewById(R.id.button8);

        Intent intent = getIntent();
        String p1Name = intent.getStringExtra("player1Name");
        String p1Color = intent.getStringExtra("player1Color");
        String p2Name = intent.getStringExtra("player2Name");
        String p2Color = intent.getStringExtra("player2Color");

        player1 = new Player(p1Name, 'X', p1Color, true);
        player2 = new Player(p2Name, 'O', p2Color, false);

        setInitialGameText();

        for (int i = 0; i < 9; i++) {
            final int finalI = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    placeMarkOnBoard(v, finalI);
                }
            });
        }
    }

    private void setInitialGameText() {
        player1Name.setText(player1.getPlayerName());
        player1Color.setBackgroundColor(Color.parseColor(player1.getPlayerMarkColor()));
        player2Name.setText(player2.getPlayerName());
        player2Color.setBackgroundColor(Color.parseColor(player2.getPlayerMarkColor()));

        setPlayerScores();

        board.setCurrAndOtherPlayer(player1, player2);
        updateGameMessage(board.getCurrPlayer());
    }

    private void setPlayerScores() {
        player1Score.setText(Integer.toString(player1.getPlayerScore()));
        player2Score.setText(Integer.toString(player2.getPlayerScore()));
    }

    private void placeMarkOnBoard(View v, int i) {
        board.setCurrAndOtherPlayer(player1, player2);
        Player currPlayer = board.getCurrPlayer();

        System.out.println("Clicked Spot");
        if (board.getBoardSpot(i) == 'E') {
            System.out.println("Clicked valid spot");
            board.setBoardSpot(i, currPlayer.getPlayerMark());
            v.setBackgroundColor(Color.parseColor(currPlayer.getPlayerMarkColor()));
            printBoard();

            player1.setPlayersTurn(!player1.isPlayersTurn());
            player2.setPlayersTurn(!player2.isPlayersTurn());

            if (board.checkWinner(currPlayer)) {
                //TODO: Set score here
                currPlayer.setPlayerScore();
                setPlayerScores();
                //TODO: Flash winning spots
                flashWinningSpots();
                //TODO: Reset game

                String message = String.format("%s Wins!", currPlayer.getPlayerName());
                gameMessage.setText(message);

            }
            else {
                updateGameMessage(board.getOtherPlayer());
            }
        }
    }


    private void printBoard() {
        System.out.println(board.getBoard());
    }

    private void updateGameMessage(Player player) {
        String message = String.format("%s's Turn", player.getPlayerName());
        gameMessage.setText(message);
    }

    private void flashWinningSpots() {
        Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(300);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(5);
        animation.setRepeatMode(Animation.REVERSE);
        buttons[0].startAnimation(animation);
        buttons[1].startAnimation(animation);
    }
}
