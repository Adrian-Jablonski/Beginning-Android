package com.example.tictactoe.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    private Button playAgain;

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

        playAgain = findViewById(R.id.playAgainBtn);

        Intent intent = getIntent();
        String p1Name = intent.getStringExtra("player1Name");
        String p1Color = intent.getStringExtra("player1Color");
        String p2Name = intent.getStringExtra("player2Name");
        String p2Color = intent.getStringExtra("player2Color");
        String gameMode = intent.getStringExtra("gameMode");
        boolean p2IsComputer = intent.getStringExtra("player2Type").equals("Computer");

        player1 = new Player(p1Name, 'X', p1Color, true, false);
        player2 = new Player(p2Name, 'O', p2Color, false, p2IsComputer);

        board.setGameMode(gameMode);

        System.out.printf("Game Mode: %s%n", board.getGameMode());

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

        isComputerStarting();

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain.setVisibility(View.INVISIBLE);
                toggleButtonDisabled(false);
                board.resetBoard();
                resetButtonColors();
                board.setCurrAndOtherPlayer(player1, player2);
                updateGameMessage(board.getCurrPlayer());
                isComputerStarting();
            }
        });
    }

    private void isComputerStarting() {
        if (board.getCurrPlayer().isComputer()) {
            computerMove();
        }
        else {
            toggleButtonDisabled(true);
        }
    }

    private void resetButtonColors() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setBackgroundColor(Color.LTGRAY);
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
        Player otherPlayer = board.getOtherPlayer();

        if (board.getBoardSpot(i) == 'E') {
            board.setBoardSpot(i, currPlayer.getPlayerMark());
            v.setBackgroundColor(Color.parseColor(currPlayer.getPlayerMarkColor()));
            printBoard();

            player1.setPlayersTurn(!player1.isPlayersTurn());
            player2.setPlayersTurn(!player2.isPlayersTurn());

            if (board.isDisappearingGameMode()) {
                removeMark(i);
            }

            int[] winningSpots = board.winningSpots();

            if (winningSpots.length != 0) {
                board.setWinner(true);
                gameOver(currPlayer, winningSpots);
            }
            else if (board.noMoreSpots()) {
                gameOver(currPlayer, winningSpots);
            }
            else {
                updateGameMessage(otherPlayer);

                if (otherPlayer.isComputer()) {
                    computerMove();
                }
            }
        }
    }

    private void removeMark(int i) {
        board.setMarkOrder(board.getMarkOrder() + i);
        String markOrder = board.getMarkOrder();
        int markOrderLen = markOrder.length();
        if (board.isDisappearingFlashingGameMode()) {
            if (markOrderLen == 6) {
                flashDisappearingSpot(Integer.parseInt(markOrder.substring(0, 1)));
            }
            else if (markOrderLen >= 7) {
                flashDisappearingSpot(Integer.parseInt(markOrder.substring(1, 2)));
            }
        }
        if (markOrderLen >= 7) {
            int firstMark = Integer.parseInt(markOrder.substring(0, 1));
            board.setMarkOrder(markOrder.substring(1));
            board.setBoardSpot(firstMark, 'E');
            buttons[firstMark].setBackgroundColor(Color.LTGRAY);
        }
    }

    private void gameOver(Player currPlayer, int[] winningSpots) {
        toggleButtonDisabled(false);
        String message = "Tie Game";
        if (board.isWinner()) {
            currPlayer.setPlayerScore();
            setPlayerScores();
            flashWinningSpots(winningSpots);
            board.setWinner(false);
            board.setMarkOrder("");
            message = String.format("%s Wins!", currPlayer.getPlayerName());
        }
        playAgain.setVisibility(View.VISIBLE);

        gameMessage.setText(message);
    }


    private void printBoard() {
        System.out.println(board.getBoard());
    }

    private void updateGameMessage(Player player) {
        String message = String.format("%s's Turn", player.getPlayerName());
        gameMessage.setText(message);
    }

    private void flashWinningSpots(int[] winningSpots) {
        Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(300);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(7);
        animation.setRepeatMode(Animation.REVERSE);
        buttons[winningSpots[0]].startAnimation(animation);
        buttons[winningSpots[1]].startAnimation(animation);
        buttons[winningSpots[2]].startAnimation(animation);
    }

    private void flashDisappearingSpot(int disappearingSpot) {
        Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(300);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(5);
        animation.setRepeatMode(Animation.REVERSE);
        buttons[disappearingSpot].startAnimation(animation);
    }

    private void computerMove() {
        toggleButtonDisabled(false);
        int computerSpot = board.getComputerMove(board.getOtherPlayer().getPlayerMark());
        computerClick(computerSpot);
    }

    private void computerClick(final int i) {
        //TODO: Disable button clicks when computers turn. Enable on players turn
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                buttons[i].performClick();
                toggleButtonDisabled(true);
            }
        }, 1500);
    }

    private void toggleButtonDisabled(boolean buttonEnabled) {
        for (Button button : buttons) {
            button.setEnabled(buttonEnabled);
        }
    }
}
