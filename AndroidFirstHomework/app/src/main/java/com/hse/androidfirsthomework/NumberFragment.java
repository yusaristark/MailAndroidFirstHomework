package com.hse.androidfirsthomework;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NumberFragment extends Fragment {
    private ColoredNumber number;

    public NumberFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            number = (ColoredNumber) getArguments().getSerializable("number");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_number, container, false);
        TextView textView = layout.findViewById(R.id.big_number);
        textView.setText(String.valueOf(number.getValue()));
        if (number.getValue() % 2 == 0) {
            textView.setTextColor(ContextCompat.getColor(layout.getContext(), R.color.red));
        } else {
            textView.setTextColor(ContextCompat.getColor(layout.getContext(), R.color.blue));
        }
        return layout;
    }
}