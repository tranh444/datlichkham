package com.example.datlichkham.adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;

public class ItemListDoctorStatisticalViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNameDoctor,tvNameService,tvNameRoom,tvSumPriceOrder;
    public ItemListDoctorStatisticalViewHolder(@NonNull View itemView) {
        super(itemView);
        tvNameDoctor = itemView.findViewById(R.id.tvNameDoctor);
        tvNameService = itemView.findViewById(R.id.tvNameService);
        tvNameRoom = itemView.findViewById(R.id.tvNameRoom);
        tvSumPriceOrder = itemView.findViewById(R.id.tvSumPriceOrder);
    }
}
