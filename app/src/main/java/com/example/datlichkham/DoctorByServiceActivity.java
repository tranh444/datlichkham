package com.example.datlichkham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datlichkham.adapter.AdapterListDoctorByService;
import com.example.datlichkham.dao.DoctorDAO;
import com.example.datlichkham.dto.AllDTO;

import java.util.ArrayList;

public class DoctorByServiceActivity extends AppCompatActivity {
    private TextView tvServiceName;
    private RecyclerView rcvListDoctorByService;
    private ImageView imgBack;
    DoctorDAO doctorDAO;
    AdapterListDoctorByService adapterListDoctorByService;
    ArrayList<AllDTO> listDoctorByService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_by_service);
        Intent intent = getIntent();
        int serviceID = intent.getIntExtra("serviceid",-1);
        String serviceName = intent.getStringExtra("serviceName");
        tvServiceName = findViewById(R.id.tv_serviceName);
        rcvListDoctorByService = findViewById(R.id.rcv_listDoctorByService);
        imgBack = findViewById(R.id.img_back);
        imgBack.setOnClickListener(view->{
            finish();
        });

        doctorDAO = new DoctorDAO(getApplicationContext());
        if(serviceID!=-1){
            tvServiceName.setText(serviceName);
            listDoctorByService = doctorDAO.getDocotrByService(serviceID);
            adapterListDoctorByService = new AdapterListDoctorByService(listDoctorByService, doctorDAO);
            rcvListDoctorByService.setAdapter(adapterListDoctorByService);
        }else{
            tvServiceName.setText(serviceName);
            listDoctorByService = doctorDAO.getDocotrByServiceAll();
            adapterListDoctorByService = new AdapterListDoctorByService(listDoctorByService, doctorDAO);
            rcvListDoctorByService.setAdapter(adapterListDoctorByService);
        }

    }
}