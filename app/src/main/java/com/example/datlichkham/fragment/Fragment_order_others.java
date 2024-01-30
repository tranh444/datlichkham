package com.example.datlichkham.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.datlichkham.ConfirmActivity;
import com.example.datlichkham.OrderDoctorActivity;
import com.example.datlichkham.R;
import com.example.datlichkham.dao.DoctorDAO;
import com.example.datlichkham.dao.FileDAO;
import com.example.datlichkham.dao.OrderDoctorDAO;
import com.example.datlichkham.dao.ServicesDAO;
import com.example.datlichkham.dto.DoctorDTO;
import com.example.datlichkham.dto.FileDTO;
import com.example.datlichkham.dto.OrderDoctorDTO;
import com.example.datlichkham.dto.ServicesDTO;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Fragment_order_others extends Fragment {
    private TextInputLayout tilNameFullName;
    private TextInputLayout tilPhoneNumber;
    private TextView tvBirthday;
    private ImageView imgBirthday;
    private TextInputLayout tilEmail;
    private TextInputLayout tilCccd;
    private TextInputLayout tilCountry;
    private RadioButton rdoYes;
    private RadioButton rdoNo;
    private TextInputLayout tilJob;
    private TextInputLayout tilAddress;
    private TextInputLayout tilDes;
    private MaterialButton btnOrder;
    private FileDAO fileDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_i, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewID(view);
        fileDAO = new FileDAO(getContext());

        SharedPreferences preferences = getActivity().getSharedPreferences("getIdUser", Context.MODE_PRIVATE);
        int idUser = preferences.getInt("idUser", -1);

        rdoNo.setChecked(true);
        imgBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String date = day + "/" + (month + 1) + "/" + year;
                        tvBirthday.setText(date);
                    }
                }, year, month, day);
                dialog.show();
            }
        });


        SharedPreferences preferences1 = getActivity().getSharedPreferences("getOrderDoctor", Context.MODE_PRIVATE);
        String startTime = preferences1.getString("startTime", "");
        String startDate = preferences1.getString("startDate", "");
        int idDoctor = preferences1.getInt("idDoctor", -1);

        DoctorDAO doctorDAO = new DoctorDAO(getContext());
        DoctorDTO doctorDTO = doctorDAO.getDtoDoctorByIdDoctor(idDoctor);

        ServicesDAO servicesDAO = new ServicesDAO(getContext());
        ServicesDTO servicesDTO = servicesDAO.getDtoServiceByIdByService(doctorDTO.getService_id());
        OrderDoctorDAO orderDoctorDao = new OrderDoctorDAO(getContext());

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileDTO fileDTO = new FileDTO();
                fileDTO.setFullname(tilNameFullName.getEditText().getText().toString());
                fileDTO.setUser_id(idUser);
                fileDTO.setBirthday(formatDate(tvBirthday.getText().toString()));
                fileDTO.setCccd(tilCccd.getEditText().getText().toString());
                fileDTO.setCountry(tilCountry.getEditText().getText().toString());
                if (rdoYes.isChecked()) {
                    fileDTO.setBhyt("Có");
                } else {
                    fileDTO.setBhyt("Không");
                }
                fileDTO.setJob(tilJob.getEditText().getText().toString());
                fileDTO.setEmail(tilEmail.getEditText().getText().toString());
                fileDTO.setAddress(tilAddress.getEditText().getText().toString());
                fileDTO.setPhoneNumber(tilPhoneNumber.getEditText().getText().toString());
                long res = fileDAO.insertRow(fileDTO);
                FileDTO fileDTO1 = fileDAO.getFileDToTop();
                OrderDoctorDTO orderDoctorDTO = new OrderDoctorDTO();
                orderDoctorDTO.setFile_id(fileDTO1.getId());
                orderDoctorDTO.setDoctor_id(idDoctor);
                orderDoctorDTO.setStart_date(startDate);
                orderDoctorDTO.setStart_time(startTime);
                orderDoctorDTO.setTotal(servicesDTO.getServicesPrice());

                long res1 = orderDoctorDao.insertRow(orderDoctorDTO);
                OrderDoctorDTO orderDoctorDTO1 = orderDoctorDao.getOrderDoctorDtoDesc();
                if (res1 > 0) {
                    OrderDoctorActivity.listOrderDoctor.add(orderDoctorDTO1);
                    Intent intent = new Intent(getContext(), ConfirmActivity.class);
                    startActivity(intent);
                }
            }
        });


    }

    public String formatDate(String a) {
        String newDate = "";
        Date objdate2 = new Date(System.currentTimeMillis());
        DateFormat dateFormat2 = new DateFormat();
        String dates2 = a;
        SimpleDateFormat Format2 = new SimpleDateFormat("dd/mm/yyyy");
        try {
            Date obj = Format2.parse(dates2);
            newDate = (String) dateFormat2.format("yyyy/mm/dd", obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    private void findViewID(View view) {


        tilNameFullName = view.findViewById(R.id.tilNameFullName);
        tilPhoneNumber = view.findViewById(R.id.tilPhoneNumber);
        tvBirthday = view.findViewById(R.id.tvBirthday);
        imgBirthday = view.findViewById(R.id.imgBirthday);
        tilEmail = view.findViewById(R.id.tilEmail);
        tilCccd = view.findViewById(R.id.tilCccd);
        tilCountry = view.findViewById(R.id.tilCountry);
        rdoYes = view.findViewById(R.id.rdoYes);
        rdoNo = view.findViewById(R.id.rdoNo);
        tilJob = view.findViewById(R.id.tilJob);
        tilAddress = view.findViewById(R.id.tilAddress);
        tilDes = view.findViewById(R.id.tilDes);
        btnOrder = view.findViewById(R.id.btnOrder);

    }
}
