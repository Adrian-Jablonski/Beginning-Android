package com.example.tictactoe.model;

public class Board {
    private char[] board = new char[9];
    private char mark = 'X';
    private String markColor = "#0000FF";

    public Board() {
        for (int i = 0; i < 9; i++) {
            this.board[i] = 'E';
        }
    }

    public char[] getBoard() {
        return board;
    }

    public void setBoardSpot(int spot, char mark) {
        this.setMarkAndColor();
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

    public void setMarkAndColor() {
        if (this.mark == 'X') {
            this.mark = 'O';
            this.markColor = "#FF0000";
        }
        else {
            this.mark = 'X';
            this.markColor = "#0000FF";
        }
    }

    public void checkWinner() {
        if (spotsEqual(0, 1, 2) || spotsEqual(3, 4, 5) || spotsEqual(6, 7, 8) ||
                spotsEqual(0, 3, 6) || spotsEqual(1, 4, 7) || spotsEqual(2, 5, 8) ||
                spotsEqual(0, 4, 8) || spotsEqual(2, 4, 6)) {
            System.out.println("WINNER!");
        }
    }

    private boolean spotsEqual(int spot1, int spot2, int spot3) {
        return board[spot1] != 'E' && board[spot1] == board[spot2] && board[spot2] == board[spot3];
    }

}
