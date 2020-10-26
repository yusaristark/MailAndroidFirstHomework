package com.hse.androidfirsthomework;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NumberFragment extends Fragment {
    private int number;

    public NumberFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            number = getArguments().getInt("pos");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_number, container, false);
        TextView textView = layout.findViewById(R.id.big_number);
        ColoredNumber coloredNumber = new ColoredNumber(number + 1);
        textView.setText(String.valueOf(coloredNumber.getValue()));
        textView.setTextColor(layout.getResources().getColor(coloredNumber.getColor()));
        return layout;
    }
}