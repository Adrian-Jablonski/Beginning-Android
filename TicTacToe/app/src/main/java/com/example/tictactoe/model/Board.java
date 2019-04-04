package com.example.tictactoe.model;

public class Board {
    private char[] board = new char[9];

    public Board() {
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
}
