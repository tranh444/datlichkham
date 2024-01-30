package com.example.datlichkham.adapter;

import android.content.Context;
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

public class AdapterListDoctorByService extends RecyclerView.Adapter<AdapterListDoctorByService.ViewHoderListDoctor> {
    ArrayList<AllDTO> listDoctor;
    DoctorDAO doctorDAO;
    Context context;

    public AdapterListDoctorByService(ArrayList<AllDTO> listDoctor, DoctorDAO doctorDAO) {
        this.listDoctor = listDoctor;
        this.doctorDAO = doctorDAO;
    }

    public AdapterListDoctorByService(ArrayList<AllDTO> listDoctor, Context context) {
        this.listDoctor = listDoctor;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoderListDoctor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_doctor, parent, false);
        context = parent.getContext();
        doctorDAO= new DoctorDAO(context);
        return new ViewHoderListDoctor(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderListDoctor holder, int position) {
        final int index = position;
        AllDTO allDTO = listDoctor.get(index);
        holder.tvFullNameDoctor.setText(allDTO.getFullameUser());
        holder.tvBirthdayDoctor.setText(formatDate(allDTO.getBirthday()));
        holder.tvService.setText(allDTO.getServicesName());
    }

    @Override
    public int getItemCount() {
        return listDoctor==null?0: listDoctor.size();
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
    public class ViewHoderListDoctor extends RecyclerView.ViewHolder {
        private TextView tvFullNameDoctor;
        private TextView tvBirthdayDoctor;
        private TextView tvService;
        public ViewHoderListDoctor(@NonNull View itemView) {
            super(itemView);
            tvFullNameDoctor = itemView.findViewById(R.id.tv_fullNameDoctor);
            tvBirthdayDoctor = itemView.findViewById(R.id.tv_birthdayDoctor);
            tvService = itemView.findViewById(R.id.tv_service);

        }
    }
}
