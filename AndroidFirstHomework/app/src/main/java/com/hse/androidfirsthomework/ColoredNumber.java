package com.hse.androidfirsthomework;

import java.io.Serializable;

public class ColoredNumber implements Serializable {
    private final int value;

    public ColoredNumber(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
