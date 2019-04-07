package com.example.tictactoe.model;

import java.util.Random;

public class Board {
    private char[] board = new char[9];
    private String markOrder = "";
    private char mark = 'X';
    private String markColor = "#0000FF";
    private Player currPlayer;
    private Player otherPlayer;
    private boolean winner = false;
    private String gameMode;
    private boolean disappearingGameMode = false;
    private boolean disappearingFlashingGameMode = false;

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

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
        if (gameMode.toLowerCase().indexOf("disappearing") != -1) {
            this.disappearingGameMode = true;
            if (gameMode.toLowerCase().indexOf("flashing") != -1) {
                this.disappearingFlashingGameMode = true;
            }
        }
    }

    public boolean isDisappearingGameMode() {
        return disappearingGameMode;
    }

    public boolean isDisappearingFlashingGameMode() {
        return disappearingFlashingGameMode;
    }

    public String getMarkOrder() {
        return markOrder;
    }

    public void setMarkOrder(String markOrder) {
        this.markOrder = markOrder;
        System.out.println("Mark Order: " + this.markOrder);
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

    public int getComputerMove(char computerMark) {
//        System.out.println("Answer: " + (boolean)analyzeBoard(0, 1, 2).get(0));

        // Computer wins
        if (analyzeBoard(0, 1, 2, computerMark) || analyzeBoard(0, 3, 6, computerMark) || analyzeBoard(0, 4, 8, computerMark)) {
            return 0;
        }
        else if (analyzeBoard(1, 0, 2, computerMark) || analyzeBoard(1, 4, 7, computerMark)) {
            return 1;
        }
        else if (analyzeBoard(2, 0, 1, computerMark) || analyzeBoard(2, 5, 8, computerMark) || analyzeBoard(2, 4, 6, computerMark)) {
            return 2;
        }
        else if (analyzeBoard(3, 0, 6, computerMark) || analyzeBoard(3, 4, 5, computerMark)) {
            return 3;
        }
        else if (analyzeBoard(4, 3, 5, computerMark) || analyzeBoard(4, 1, 7, computerMark) || analyzeBoard(4, 0, 8, computerMark) || analyzeBoard(4, 2, 6, computerMark)) {
            return 4;
        }
        else if (analyzeBoard(5, 3, 4, computerMark) || analyzeBoard(5, 2, 8, computerMark)) {
            return 5;
        }
        else if (analyzeBoard(6, 0, 3, computerMark) || analyzeBoard(6, 7, 8, computerMark) || analyzeBoard(6, 4, 2, computerMark)) {
            return 6;
        }
        else if (analyzeBoard(7, 6, 8, computerMark) || analyzeBoard(7, 1, 4, computerMark)) {
            return 7;
        }
        else if (analyzeBoard(8, 6, 7, computerMark) || analyzeBoard(8, 2, 5, computerMark) || analyzeBoard(8, 0, 4, computerMark)) {
            return 8;
        }

        // Computer blocks
        else if (analyzeBoardBlock(0, 1, 2) || analyzeBoardBlock(0, 3, 6) || analyzeBoardBlock(0, 4, 8)) {
            return 0;
        }
        else if (analyzeBoardBlock(1, 0, 2) || analyzeBoardBlock(1, 4, 7)) {
            return 1;
        }
        else if (analyzeBoardBlock(2, 0, 1) || analyzeBoardBlock(2, 5, 8) || analyzeBoardBlock(2, 4, 6)) {
            return 2;
        }
        else if (analyzeBoardBlock(3, 0, 6) || analyzeBoardBlock(3, 4, 5)) {
            return 3;
        }
        else if (analyzeBoardBlock(4, 3, 5) || analyzeBoardBlock(4, 1, 7) || analyzeBoardBlock(4, 0, 8) || analyzeBoardBlock(4, 2, 6)) {
            return 4;
        }
        else if (analyzeBoardBlock(5, 3, 4) || analyzeBoardBlock(5, 2, 8)) {
            return 5;
        }
        else if (analyzeBoardBlock(6, 0, 3) || analyzeBoardBlock(6, 7, 8) || analyzeBoardBlock(6, 4, 2)) {
            return 6;
        }
        else if (analyzeBoardBlock(7, 6, 8) || analyzeBoardBlock(7, 1, 4)) {
            return 7;
        }
        else if (analyzeBoardBlock(8, 6, 7) || analyzeBoardBlock(8, 2, 5) || analyzeBoardBlock(8, 0, 4)) {
            return 8;
        }

        return computerMoveRandom();
    }

    private boolean analyzeBoard(int spotToPlace, int spot1, int spot2, char computerMark) {
        return board[spotToPlace] == 'E' && board[spot1] != 'E' && board[spot1] == board[spot2]  && board[spot1] == computerMark;
    }

    private boolean analyzeBoardBlock(int spotToPlace, int spot1, int spot2) {
        return board[spotToPlace] == 'E' && board[spot1] != 'E' && board[spot1] == board[spot2];
    }

    private int computerMoveRandom() {
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
