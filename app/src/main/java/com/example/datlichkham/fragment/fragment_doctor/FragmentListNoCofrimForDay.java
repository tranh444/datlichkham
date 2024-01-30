package com.example.datlichkham.fragment.fragment_doctor;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.AdapterOrderNocofirm;
import com.example.datlichkham.dao.DoctorDAO;
import com.example.datlichkham.dao.OrderDoctorDAO;
import com.example.datlichkham.dto.DoctorDTO;
import com.example.datlichkham.dto.OrderDoctorDTO;

import java.util.ArrayList;
import java.util.Calendar;


public class FragmentListNoCofrimForDay extends Fragment {
    private String date;
    private DoctorDTO doctorDTO;
    private OrderDoctorDAO orderDoctorDAO;
    private ArrayList<OrderDoctorDTO> listOrderNoCofirmByToDay;
    DoctorDAO doctorDAO;
    private RecyclerView rcvListNoCofrimForDay;
    AdapterOrderNocofirm adapterOrderNocofirm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_no_cofrim_for_day, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvListNoCofrimForDay = view.findViewById(R.id.rcv_listNoCofrimForDay);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("getIdUser", getContext().MODE_PRIVATE);
        int id = sharedPreferences.getInt("idUser", -1);
        doctorDAO = new DoctorDAO(getContext());
        orderDoctorDAO = new OrderDoctorDAO(getContext());
        if (id != -1) {
            doctorDTO = doctorDAO.getDtoDoctorByIdAccount(id);
        }
        // lấy ngày hiện tại
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        if(day<10){
            date = year + "/" + (month + 1) + "/" +"0"+ day;
        }else{
            date = year + "/" + (month + 1) + "/" + day;
        }

        showdata();

    }
    public void showdata(){
        listOrderNoCofirmByToDay = orderDoctorDAO.listOrderDoctorByDateToDayByDoctor(date,doctorDTO.getId());
        adapterOrderNocofirm = new AdapterOrderNocofirm(listOrderNoCofirmByToDay,getContext());
        rcvListNoCofrimForDay.setAdapter(adapterOrderNocofirm);
    }

    @Override
    public void onResume() {
        super.onResume();
        showdata();
    }
}