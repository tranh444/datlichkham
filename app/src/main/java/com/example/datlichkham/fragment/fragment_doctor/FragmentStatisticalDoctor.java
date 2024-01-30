package com.example.datlichkham.fragment.fragment_doctor;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.AdapterStatisticalDoctor;
import com.example.datlichkham.dao.DoctorDAO;
import com.example.datlichkham.dao.OrderDetailDAO;
import com.example.datlichkham.dto.AllDTO;
import com.example.datlichkham.dto.DoctorDTO;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Calendar;

public class FragmentStatisticalDoctor extends Fragment {

    private TextView tvStartDate;
    private TextView tvEndDate;
    private RecyclerView rcvStatisticalDoctor;
    private TextView tvSumPrice;
    private MaterialButton btnFind;
    OrderDetailDAO orderDetailDAO;
    ArrayList<AllDTO> listPrice;
    DoctorDAO doctorDAO;
    String startDate;
    String endDate;
    DoctorDTO doctorDTO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_statistical_doctor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewID(view);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("getIdUser", Context.MODE_PRIVATE);
        int idUSer = sharedPreferences.getInt("idUser", -1);
        doctorDAO = new DoctorDAO(getContext());
        doctorDTO = doctorDAO.getDtoDoctorByIdAccount(idUSer);
        orderDetailDAO = new OrderDetailDAO(getContext());
        tvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDate(tvStartDate);
            }
        });
        tvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDate(tvEndDate);
            }
        });
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDate = tvStartDate.getText().toString().trim();
                endDate = tvEndDate.getText().toString().trim();
                listPrice = orderDetailDAO.getListPriceByMonthDoctor(startDate, endDate, doctorDTO.getId());
                AdapterStatisticalDoctor adapterStatisticalDoctor = new AdapterStatisticalDoctor(listPrice, "month");
                rcvStatisticalDoctor.setAdapter(adapterStatisticalDoctor);
                tvSumPrice.setText("Tổng tiền: "+orderDetailDAO.getSumPriceByMonthDoctor(startDate, endDate, doctorDTO.getId()) + " vnđ");
            }
        });



    }

    private void findViewID(View view) {
        tvStartDate = view.findViewById(R.id.tvStartDate);
        tvEndDate = view.findViewById(R.id.tvEndDate);
        rcvStatisticalDoctor = view.findViewById(R.id.rcv_StatisticalDoctor);
        tvSumPrice = view.findViewById(R.id.tv_sumPrice);


        btnFind = view.findViewById(R.id.btn_find);

    }

    public void dialogDate(TextView tv) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int days = dayOfMonth;
                int months = month;
                int years = year;
                if (days < 10) {
                    tv.setText(years + "/" + (months + 1) + "/" + "0" + days);
                } else {
                    tv.setText(years + "/" + (months + 1) + "/" + days);
                }
            }
        }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
}