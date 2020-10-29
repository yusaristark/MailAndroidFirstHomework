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
    private final ArrayList<ColoredNumber> list;

    private Listener listener;

    interface Listener {
        void onClick(ColoredNumber coloredNumber);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public ColoredNumberAdapter(ArrayList<ColoredNumber> list) {
        this.list = list;
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView cardView;
        private ColoredNumber number;

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onClick(number);
            }
        }

        public void setNumber(ColoredNumber number) {
            this.number = number;
        }

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
            this.cardView.setOnClickListener(this);
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
        if (number.getValue() % 2 == 0) {
            textView.setTextColor(ContextCompat.getColor(cardView.getContext(), R.color.red));
        } else {
            textView.setTextColor(ContextCompat.getColor(cardView.getContext(), R.color.blue));
        }
        holder.setNumber(number);
        /*cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(list.get(position));
                }
            }
        });*/

    }
}
