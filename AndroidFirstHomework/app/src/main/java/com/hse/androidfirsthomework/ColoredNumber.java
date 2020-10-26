package com.hse.androidfirsthomework;

import java.io.Serializable;

public class ColoredNumber implements Serializable {
    private final int value;
    private final int color;

    public ColoredNumber(int value) {
        this.value = value;
        if (value % 2 == 0) {
            color = R.color.red;
        } else {
            color = R.color.blue;
        }
    }

    public int getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }
}
