package com.example.datlichkham.doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.example.datlichkham.R;
import com.example.datlichkham.dao.OrderDAO;
import com.example.datlichkham.dao.OrderDoctorDAO;
import com.example.datlichkham.dto.OrderDTO;
import com.example.datlichkham.dto.OrderDoctorDTO;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CofrimOrderDoctorActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextInputLayout tilNameFullName;
    private TextInputLayout tilPhoneNumber;
    private TextInputLayout tilBirthday;
    private TextInputLayout tilEmail;
    private TextInputLayout tilCccd;
    private TextInputLayout tilCountry;
    private TextInputLayout tilbhyt;
    private TextInputLayout tilJob;
    private TextInputLayout tilAddress;
    private MaterialButton btnComfrim;
    private TextInputLayout tilNote;

    OrderDAO orderDAO;
    OrderDoctorDAO orderDoctorDAO;
    OrderDoctorDTO orderDoctorDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cofrim_order_doctor);
        findViewID();
        orderDoctorDAO = new OrderDoctorDAO(getApplicationContext());
        orderDAO = new OrderDAO(getApplicationContext());
        Intent intent = getIntent();
        toolbar.setNavigationOnClickListener(view -> {
            finish();
        });
        tilNameFullName.getEditText().setText(intent.getStringExtra("fullName"));
        tilBirthday.getEditText().setText(formatDate(intent.getStringExtra("birthday")));
        tilCccd.getEditText().setText(intent.getStringExtra("cccd"));
        tilCountry.getEditText().setText(intent.getStringExtra("country"));
        tilAddress.getEditText().setText(intent.getStringExtra("address"));
        tilbhyt.getEditText().setText(intent.getStringExtra("bhyt"));
        tilJob.getEditText().setText(intent.getStringExtra("job"));
        btnComfrim.setOnClickListener(view -> {
            OrderDTO orderDTO = orderDAO.getOrderDTOById(intent.getIntExtra("orderID", -1));
            orderDTO.setStatus("Đã khám xong");
            orderDTO.setNote(tilNote.getEditText().getText().toString());
//            orderDoctorDTO = orderDoctorDAO.getOrderDoctorDtoById(intent.getIntExtra("orderID", -1));
//            orderDoctorDTO.setStatus("Đã khám xong");
//            int res2 = orderDoctorDAO.updateRow(orderDoctorDTO);
            int res = orderDAO.updateRow(orderDTO);
            if (res > 0) {
                Toast.makeText(getApplicationContext(), "Đã xác nhận", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public String formatDate(String a) {
        String newDate = "";
        Date objdate2 = new Date(System.currentTimeMillis());
        DateFormat dateFormat2 = new DateFormat();
        String dates2 = a;
        SimpleDateFormat Format2 = new SimpleDateFormat("yyyy/mm/dd");
        try {
            Date obj = Format2.parse(dates2);
            newDate = (String) dateFormat2.format("dd/mm/yyyy", obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    private void findViewID() {


        toolbar = findViewById(R.id.toolbar);
        tilNameFullName = findViewById(R.id.tilNameFullName);
        tilPhoneNumber = findViewById(R.id.tilPhoneNumber);
        tilBirthday = findViewById(R.id.tilBirthday);
        tilEmail = findViewById(R.id.tilEmail);
        tilCccd = findViewById(R.id.tilCccd);
        tilCountry = findViewById(R.id.tilCountry);
        tilbhyt = findViewById(R.id.tilbhyt);
        tilJob = findViewById(R.id.tilJob);
        tilAddress = findViewById(R.id.tilAddress);
        btnComfrim = findViewById(R.id.btn_comfrim);
        tilNote = findViewById(R.id.tilNote);

    }
}