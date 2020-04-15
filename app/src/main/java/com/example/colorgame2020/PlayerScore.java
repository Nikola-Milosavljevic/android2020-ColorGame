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

    public void setUsername(String username) {
        this.username = username;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
