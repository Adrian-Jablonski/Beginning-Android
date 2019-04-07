package com.example.tictactoe.model;

import java.util.Arrays;
import java.util.Random;

public class Board {
    private char[] board = new char[9];
    private char mark = 'X';
    private String markColor = "#0000FF";
    private Player currPlayer;
    private Player otherPlayer;
    private boolean winner = false;

    public Board() {
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < 9; i++) {
            this.board[i] = 'E';
        }
    }

    public char[] getBoard() {
        return board;
    }

    public void setBoardSpot(int spot, char mark) {
        this.board[spot] = mark;
    }

    public char getBoardSpot(int spot) {
        return this.board[spot];
    }

    public char getMark() {
        return mark;
    }

    public String getMarkColor() {
        return markColor;
    }

    public int[] winningSpots() {
        if (spotsEqual(0, 1, 2)) {
            return new int[]{0, 1, 2};
        }
        else if (spotsEqual(3, 4, 5)) {
            return new int[]{3, 4, 5};
        }
        else if (spotsEqual(6, 7, 8)) {
            return new int[]{6, 7, 8};
        }

        else if (spotsEqual(0, 3, 6)) {
            return new int[]{0, 3, 6};
        }
        else if (spotsEqual(1, 4, 7)) {
            return new int[]{1, 4, 7};
        }
        else if (spotsEqual(2, 5, 8)) {
            return new int[]{2, 5, 8};
        }

        else if (spotsEqual(0, 4, 8)) {
            return new int[]{0, 4, 8};
        }
        else if (spotsEqual(2, 4, 6)) {
            return new int[]{2, 4, 6};
        }

        return new int[] {};
    }

    private boolean spotsEqual(int spot1, int spot2, int spot3) {
        return board[spot1] != 'E' && board[spot1] == board[spot2] && board[spot2] == board[spot3];
    }

    public Player getCurrPlayer() {
        return currPlayer;
    }

    public void setCurrAndOtherPlayer(Player player1, Player player2) {
        if (player1.isPlayersTurn()) {
            this.currPlayer = player1;
            this.otherPlayer = player2;
        }
        else {
            this.currPlayer = player2;
            this.otherPlayer = player1;
        }
    }

    public Player getOtherPlayer() {
        return otherPlayer;
    }

    public void setOtherPlayer(Player otherPlayer) {
        this.otherPlayer = otherPlayer;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public int getComputerMove() {
        Random rand = new Random();
        int computerMove = rand.nextInt(9);
        while (board[computerMove] != 'E') {
            computerMove = rand.nextInt(9);
        }

        return computerMove;
    }

    public boolean noMoreSpots() {
        return !(new String(board).contains("E"));
    }
}
