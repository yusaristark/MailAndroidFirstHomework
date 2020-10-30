package com.hse.androidfirsthomework;

import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ColoredNumber number;
    private final ColoredNumberAdapter.Listener listener;

    protected final TextView mTextView;

    @Override
    public void onClick(View view) {
        if (listener != null && number != null) {
            listener.onClick(number);
        }
    }

    public void setNumber(ColoredNumber number) {
        this.number = number;
        mTextView.setText(String.valueOf(number.getValue()));
        if (number.getValue() % 2 == 0) {
            mTextView.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.red));
        } else {
            mTextView.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.blue));
        }
    }

    public ViewHolder(View itemView, ColoredNumberAdapter.Listener listener) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.card_text);
        this.listener = listener;
        itemView.setOnClickListener(this);
    }

}
