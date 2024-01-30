package com.example.datlichkham.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
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


public class Fragment_Calender_Doctor extends Fragment {

    private RecyclerView rcvCalenderDoctor;
    private DoctorDAO doctorDAO;
    private ArrayList<OrderDoctorDTO> listAllOrderNoCofirm;
    private DoctorDTO doctorDTO;
    private OrderDoctorDAO orderDoctorDAO;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment__calender__doctor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewId(view);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("getIdUser", getContext().MODE_PRIVATE);
        int id = sharedPreferences.getInt("idUser", -1);
        doctorDAO = new DoctorDAO(getContext());
        orderDoctorDAO = new OrderDoctorDAO(getContext());
        if (id != -1) {
            doctorDTO = doctorDAO.getDtoDoctorByIdAccount(id);
            listAllOrderNoCofirm = orderDoctorDAO.listOrderDoctorByDateToDayByDoctorAllNoConfirm(doctorDTO.getId());
            showListAllNoCofrim(listAllOrderNoCofirm);
        }
    }

    private void showListAllNoCofrim(ArrayList<OrderDoctorDTO> list) {
        AdapterOrderNocofirm adapterOrderNocofirm = new AdapterOrderNocofirm(list, getContext());
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvCalenderDoctor.setLayoutManager(manager1);
        rcvCalenderDoctor.setAdapter(adapterOrderNocofirm);
    }

    public void findViewId(View view) {
        rcvCalenderDoctor = view.findViewById(R.id.rcv_calender_doctor);
    }

    @Override
    public void onResume() {
        super.onResume();
        listAllOrderNoCofirm = orderDoctorDAO.listOrderDoctorByDateToDayByDoctorAllNoConfirm(doctorDTO.getId());
        showListAllNoCofrim(listAllOrderNoCofirm);

    }

}