package com.example.datlichkham.adapter;

import  android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;
import com.example.datlichkham.dao.DoctorDAO;
import com.example.datlichkham.dto.AllDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterCalenderDoctor extends RecyclerView.Adapter<AdapterCalenderDoctor.ViewHoderCalenderDoctor>{
    Context context;
    DoctorDAO doctorDAO;
    ArrayList<AllDTO> listCalender;
    public AdapterCalenderDoctor(DoctorDAO doctorDAO, ArrayList<AllDTO> listCalender) {
        this.doctorDAO = doctorDAO;
        this.listCalender = listCalender;
    }

    @NonNull
    @Override
    public ViewHoderCalenderDoctor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_doctor_calendar, parent, false);
        context = parent.getContext();
        doctorDAO = new DoctorDAO(context);
        return new ViewHoderCalenderDoctor(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderCalenderDoctor holder, int position) {
        final int index = position;
        AllDTO allDTO = listCalender.get(index);
        holder.tvFullNameUser.setText(allDTO.getFullameUser());
        holder.tvBirthdayUser.setText(allDTO.getBirthdayUser());
        holder.tvStarDate.setText(formatDate(allDTO.getStartDate()));
        holder.tvStarTime.setText(allDTO.getStartTime());
    }

    @Override
    public int getItemCount() {
        return listCalender == null ? 0 : listCalender.size();
    }

    public String formatDate(String a) {
        String newDate ="";
        Date objdate2 = new Date(System.currentTimeMillis());
        DateFormat dateFormat2 = new DateFormat();
        String dates2 =a;
        SimpleDateFormat Format2 = new SimpleDateFormat("yyyy/mm/dd");
        try {
            Date obj = Format2.parse(dates2);
            newDate = (String) dateFormat2.format("dd/mm/yyyy", obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public class ViewHoderCalenderDoctor extends RecyclerView.ViewHolder {
        private TextView tvFullNameUser;
        private TextView tvBirthdayUser;
        private TextView tvStarDate;
        private TextView tvStarTime;

        public ViewHoderCalenderDoctor(@NonNull View itemView) {
            super(itemView);
            tvFullNameUser = itemView.findViewById(R.id.tv_fullNameUser);
            tvBirthdayUser = itemView.findViewById(R.id.tv_birthdayUser);
            tvStarDate = itemView.findViewById(R.id.tv_starDate);
            tvStarTime = itemView.findViewById(R.id.tv_starTime);
        }
    }
}
