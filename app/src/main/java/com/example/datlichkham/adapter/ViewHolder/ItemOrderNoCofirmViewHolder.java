package com.example.datlichkham.adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;

public class ItemOrderNoCofirmViewHolder extends RecyclerView.ViewHolder {
    public TextView tvFile,tvDetailFile,tvStartDate,tvStartTime;
    public ItemOrderNoCofirmViewHolder(@NonNull View itemView) {
        super(itemView);
        tvFile  =itemView.findViewById(R.id.tvFile);
        tvDetailFile = itemView.findViewById(R.id.tvDetailFile);
        tvStartDate = itemView.findViewById(R.id.tvStartDate);
        tvStartTime = itemView.findViewById(R.id.tvStartTime);
    }
}
