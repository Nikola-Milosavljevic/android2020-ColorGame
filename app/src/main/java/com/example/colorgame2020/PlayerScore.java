package com.example.colorgame2020;

public class PlayerScore {

    private String username;
    private int difficulty;
    private int score;

    public PlayerScore(String username, int difficulty, int score) {
        this.username = username;
        this.difficulty = difficulty;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getScore() {
        return score;
    }

}
