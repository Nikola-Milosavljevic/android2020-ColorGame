package com.example.colorgame2020;

import android.graphics.Color;

import java.util.ArrayList;

public class ColorElement { // red u listi

    public static final int[] COLORS = {
        Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.GRAY};

    private int value;
    private ArrayList<Integer> colors;
    private int currInd = 0;

    public ColorElement(int value, ArrayList<Integer> colors) {
        this.value = value;
        this.colors = colors;
    }

    public int getCurrColor() {
        return colors.get(currInd);
    }

    public int getColor(int position) {
        return colors.get(position);
    }

    public void changeColor() {
        currInd = (currInd + 1) % colors.size();
    }

    public int getValue() {
        return value;
    }

    public int getColorsSize() {
        return colors.size();
    }
}
