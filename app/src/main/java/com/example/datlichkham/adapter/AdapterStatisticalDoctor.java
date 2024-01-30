package com.example.datlichkham.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;
import com.example.datlichkham.dto.AllDTO;

import java.util.ArrayList;

public class AdapterStatisticalDoctor extends RecyclerView.Adapter<AdapterStatisticalDoctor.ViewHoderItemStatisticalDoctor> {
    Context context;
    ArrayList<AllDTO> list;
    String check;

    public AdapterStatisticalDoctor(ArrayList<AllDTO> list, String check) {
        this.list = list;
        this.check = check;
    }

    @NonNull
    @Override
    public ViewHoderItemStatisticalDoctor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_statistical_doctor, parent, false);
        context = parent.getContext();
        return new ViewHoderItemStatisticalDoctor(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderItemStatisticalDoctor holder, int position) {
        final int index =position;
        AllDTO allDTO =list.get(index);
        holder.tvTotal.setText(allDTO.getTotal()+" vnđ");
        if(check.equalsIgnoreCase("month")){
            holder.tvTime.setText(allDTO.getDay());
        }else if(check.equalsIgnoreCase("day")){
            holder.tvTime.setText(allDTO.getTime());
        }
    }

    @Override
    public int getItemCount() {
        return list==null?0: list.size();
    }

    public class ViewHoderItemStatisticalDoctor extends RecyclerView.ViewHolder {
        private TextView tvTime;
        private TextView tvTotal;
        public ViewHoderItemStatisticalDoctor(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvTotal = itemView.findViewById(R.id.tv_total);
        }
    }
}
