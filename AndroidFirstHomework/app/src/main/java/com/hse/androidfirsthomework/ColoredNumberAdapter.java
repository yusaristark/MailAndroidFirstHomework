package com.hse.androidfirsthomework;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ColoredNumberAdapter extends RecyclerView.Adapter<ColoredNumberAdapter.ViewHolder> {
    private ArrayList<ColoredNumber> list;
    private Listener listener;

    interface Listener {
        void onClick(ColoredNumber coloredNumber);
    }


    public ColoredNumberAdapter(ArrayList<ColoredNumber> list) {
        this.list = list;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }

    @NonNull
    @Override
    public ColoredNumberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView textView = cardView.findViewById(R.id.card_text);
        ColoredNumber number = list.get(position);
        textView.setText(String.valueOf(number.getValue()));
        textView.setTextColor(ContextCompat.getColor(cardView.getContext(), number.getColor()));
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int value = position + 1;
                    int color;
                    if (value % 2 == 0) {
                        color = R.color.red;
                    } else {
                        color = R.color.blue;
                    }
                    listener.onClick(new ColoredNumber(value, color));
                }
            }
        });
    }
}
