package com.hse.androidfirsthomework;

import java.util.ArrayList;

public class ColoredNumberRepository {

    private static volatile ColoredNumberRepository mInstance;

    public static ColoredNumberRepository getInstance() {
        if (mInstance == null) {
            synchronized (ColoredNumberRepository.class) {
                if (mInstance == null) {
                    mInstance = new ColoredNumberRepository();
                }
            }
        }
        return mInstance;
    }

    protected final ArrayList<ColoredNumber> mData;


    private ColoredNumberRepository() {
        mData = initializeData();
    }

    public ArrayList<ColoredNumber> list() {
        return mData;
    }

    public ColoredNumber getItem(int index) {
        return mData.get(index);
    }

    public void addItem(int value) {
        int color;
        if (value % 2 == 0) {
            color = R.color.red;
        } else {
            color = R.color.blue;
        }
        mData.add(new ColoredNumber(value, color));
    }

    private ArrayList<ColoredNumber> initializeData() {
        ArrayList<ColoredNumber> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int value = i + 1;
            int color;
            if (value % 2 == 0) {
                color = R.color.red;
            } else {
                color = R.color.blue;
            }
            list.add(new ColoredNumber(value, color));
        }
        return list;
    }
}
