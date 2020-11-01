package com.hse.androidfirsthomework;

import java.util.ArrayList;

public class ColoredNumberRepository {

    /*private static volatile ColoredNumberRepository mInstance;

    public static ColoredNumberRepository getInstance() {
        if (mInstance == null) {
            synchronized (ColoredNumberRepository.class) {
                if (mInstance == null) {
                    mInstance = new ColoredNumberRepository();
                }
            }
        }
        return mInstance;
    }*/

    protected final ArrayList<ColoredNumber> mData;


    public ColoredNumberRepository() {
        mData = initializeData();
    }

    public ArrayList<ColoredNumber> list() {
        return mData;
    }

    public ColoredNumber getItem(int index) {
        return mData.get(index);
    }

    public void addItem(int value) {
        mData.add(new ColoredNumber(value));
    }

    private ArrayList<ColoredNumber> initializeData() {
        ArrayList<ColoredNumber> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new ColoredNumber(i+1));
        }
        return list;
    }
}
