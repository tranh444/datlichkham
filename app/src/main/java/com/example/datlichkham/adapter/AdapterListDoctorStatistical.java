package com.example.datlichkham.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.ViewHolder.ItemListDoctorStatisticalViewHolder;
import com.example.datlichkham.dao.AccountDAO;
import com.example.datlichkham.dao.DoctorDAO;
import com.example.datlichkham.dao.RoomsDAO;
import com.example.datlichkham.dao.ServicesDAO;
import com.example.datlichkham.dto.AccountDTO;
import com.example.datlichkham.dto.DoctorDTO;
import com.example.datlichkham.dto.RoomsDTO;
import com.example.datlichkham.dto.ServicesDTO;

import java.util.ArrayList;

public class AdapterListDoctorStatistical extends RecyclerView.Adapter<ItemListDoctorStatisticalViewHolder> {
    private ArrayList<DoctorDTO> list = new ArrayList<>();
    private Context context;

    public AdapterListDoctorStatistical(ArrayList<DoctorDTO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemListDoctorStatisticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_doctor_statistical,parent,false);
        return new ItemListDoctorStatisticalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListDoctorStatisticalViewHolder holder, int position) {
        DoctorDTO doctorDTO1 = list.get(position);

        DoctorDAO doctorDAO = new DoctorDAO(context);
        DoctorDTO doctorDTO = doctorDAO.getDtoDoctorByIdDoctor(doctorDTO1.getId());
        AccountDAO accountDAO = new AccountDAO(context);
        AccountDTO accountDTO = accountDAO.getDtoAccount(doctorDTO.getUser_id());
        holder.tvNameDoctor.setText(accountDTO.getFullName());

        ServicesDAO servicesDAO = new ServicesDAO(context);
        ServicesDTO servicesDTO = servicesDAO.getDtoServiceByIdByService(doctorDTO.getService_id());
        holder.tvNameService.setText("Chuyên khoa : "+servicesDTO.getServicesName());

        RoomsDAO roomsDAO = new RoomsDAO(context);
        RoomsDTO roomsDTO = roomsDAO.getDtoRoomByIdRoom(doctorDTO.getRoom_id());
        holder.tvNameRoom.setText("Phòng làm việc: "+roomsDTO.getName());

        holder.tvSumPriceOrder.setText("Tổng doanh thu : "+doctorDTO1.getSumPrice()+"vnđ");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
