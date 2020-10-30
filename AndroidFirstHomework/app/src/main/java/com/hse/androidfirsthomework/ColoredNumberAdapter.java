package com.hse.androidfirsthomework;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ColoredNumberAdapter extends RecyclerView.Adapter<ViewHolder> {
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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(cardView, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setNumber(list.get(position));
    }
}
