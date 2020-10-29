package com.hse.androidfirsthomework;

import java.io.Serializable;

public class ColoredNumber implements Serializable {
    private final int value;
    private final int color;

    public ColoredNumber(int value, int color) {
        this.value = value;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }
}
