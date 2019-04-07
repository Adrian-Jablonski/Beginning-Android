package com.example.tictactoe.model;

public class Player {
    private int playerScore = 0;
    private String playerName;
    private boolean player2AsPC = true;
    private char playerMark;
    private String playerMarkColor;
    private boolean playersTurn;
    private boolean isComputer;

    public Player(String playerName, char playerMark, String playerMarkColor, boolean playersTurn, boolean isComputer) {
        this.playerName = playerName;
        this.playerMark = playerMark;
        this.playerMarkColor = playerMarkColor;
        this.playersTurn = playersTurn;
        this.isComputer = isComputer;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore() {
        this.playerScore += 1;
    }

    public boolean isPlayer2AsPC() {
        return player2AsPC;
    }

    public void setPlayer2AsPC(boolean player2AsPC) {
        this.player2AsPC = player2AsPC;
    }

    public char getPlayerMark() {
        return playerMark;
    }

    public void setPlayerMark(char playerMark) {
        this.playerMark = playerMark;
    }

    public boolean isPlayersTurn() {
        return playersTurn;
    }

    public void setPlayersTurn(boolean playersTurn) {
        this.playersTurn = playersTurn;
    }

    public String getPlayerMarkColor() {
        return playerMarkColor;
    }

    public void setPlayerMarkColor(String playerMarkColor) {
        this.playerMarkColor = playerMarkColor;
    }

    public boolean isComputer() {
        return isComputer;
    }
}
