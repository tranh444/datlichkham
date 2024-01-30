package com.example.datlichkham.fragment.fragment_doctor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.AdapterStatisticalDoctor;
import com.example.datlichkham.dao.DoctorDAO;
import com.example.datlichkham.dao.OrderDetailDAO;
import com.example.datlichkham.dto.AllDTO;
import com.example.datlichkham.dto.DoctorDTO;

import java.util.ArrayList;
import java.util.Calendar;


public class FragmentStatisticalDoctorForDay extends Fragment {
    private RecyclerView rcvStatisticalDoctorForDay;
    private TextView tvSumPrice;
    OrderDetailDAO orderDetailDAO;
    ArrayList<AllDTO> listPriceDay;
    DoctorDAO doctorDAO;
    String date;
    DoctorDTO doctorDTO;
    String TAG="zzzzzzzzzzzzz";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistical_doctor_for_day, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvStatisticalDoctorForDay = view.findViewById(R.id.rcv_StatisticalDoctorForDay);
        tvSumPrice = view.findViewById(R.id.tv_sumPrice);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("getIdUser", Context.MODE_PRIVATE);
        int idUSer = sharedPreferences.getInt("idUser",-1);
        Log.e(TAG, "onViewCreated: id user ="+idUSer );
        doctorDAO = new DoctorDAO(getContext());
        doctorDTO = doctorDAO.getDtoDoctorByIdAccount(idUSer);
        orderDetailDAO = new OrderDetailDAO(getContext());
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        if(day<10){
            date = year + "/" + (month + 1) + "/" +"0"+ day;
        }else{
            date = year + "/" + (month + 1) + "/" + day;
        }
        Log.e(TAG, "onViewCreated: date ="+date);
        Log.e(TAG, "onViewCreated: id doctor= "+doctorDTO.getId() );
        listPriceDay = orderDetailDAO.getListPriceByDayDoctor(date,doctorDTO.getId());
        Log.e(TAG, "onViewCreated: list.sixe="+listPriceDay.size() );
        AdapterStatisticalDoctor adapterStatisticalDoctor = new AdapterStatisticalDoctor(listPriceDay,"day");
        rcvStatisticalDoctorForDay.setAdapter(adapterStatisticalDoctor);
        tvSumPrice.setText("Tổng tiền: "+orderDetailDAO.getSumPriceByDayDoctor(date, doctorDTO.getId())+" vnđ");
    }


    @Override
    public void onResume() {
        super.onResume();
        listPriceDay = orderDetailDAO.getListPriceByDayDoctor(date,doctorDTO.getId());
        AdapterStatisticalDoctor adapterStatisticalDoctor = new AdapterStatisticalDoctor(listPriceDay,"day");
        rcvStatisticalDoctorForDay.setAdapter(adapterStatisticalDoctor);
        tvSumPrice.setText("Tổng tiền: " + orderDetailDAO.getSumPriceByDayDoctor(date, doctorDTO.getId())+" vnđ");
    }
}