package com.example.datlichkham.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.AdapterListDoctorStatistical;
import com.example.datlichkham.dao.OrderDetailDAO;
import com.example.datlichkham.dto.DoctorDTO;
import com.example.datlichkham.dto.StatisticalDTO;

import java.util.ArrayList;
import java.util.Calendar;


public class Fragment_Statistical extends Fragment {
    private LinearLayout ll_date, ll_startMonth, ll_dateOrder, ll_OrderMonth, ll_listDoctorOrderMonth;
    private TextView tvDay, tvNumBerOrderStartToDay, tvStartMonth, tvNumBerStartMonth, tvNumBerOrderToDay, tvSumPriceOrderByToDay, tvSumPriceStartMonth, tvSumPriceOrderStartByToDay, tvDayOrder, tvOrderMonth, tvNumBerOrderMonth, tvSumPriceOrderbyMonth, tvListDoctoOrderMonth;
    private OrderDetailDAO orderDetailDAO;
    private Button btnStartTestDay, btnTestStartMonth, btnTestOrderToDay, btnTestOrderMonth, btnTestListDoctorOrderMonth;
    private int monthTest, yearTest;
    private RecyclerView rcv_listDoctorOrderMonth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistical, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvDay = view.findViewById(R.id.tvDay);
        tvNumBerOrderStartToDay = view.findViewById(R.id.tvNumBerOrderStartToDay);
        tvSumPriceOrderStartByToDay = view.findViewById(R.id.tvSumPriceOrderStartByToDay);
        ll_date = view.findViewById(R.id.ll_date);
        btnStartTestDay = view.findViewById(R.id.btnStartTest);
        ll_startMonth = view.findViewById(R.id.ll_startMonth);
        tvStartMonth = view.findViewById(R.id.tvStartMonth);
        tvNumBerStartMonth = view.findViewById(R.id.tvNumBerStartMonth);
        tvSumPriceStartMonth = view.findViewById(R.id.tvSumPriceStartMonth);
        btnTestStartMonth = view.findViewById(R.id.btnTestStartMonth);
        ll_dateOrder = view.findViewById(R.id.ll_dateOrder);
        tvDayOrder = view.findViewById(R.id.tvDayOrder);
        btnTestOrderToDay = view.findViewById(R.id.btnTestOrderToDay);
        btnTestOrderMonth = view.findViewById(R.id.btnTestOrderMonth);
        ll_OrderMonth = view.findViewById(R.id.ll_OrderMonth);
        tvOrderMonth = view.findViewById(R.id.tvOrderMonth);
        tvNumBerOrderMonth = view.findViewById(R.id.tvNumBerOrderMonth);
        tvSumPriceOrderbyMonth = view.findViewById(R.id.tvSumPriceOrderbyMonth);
        tvNumBerOrderToDay = view.findViewById(R.id.tvNumBerOrderToDay);
        tvSumPriceOrderByToDay = view.findViewById(R.id.tvSumPriceOrderByToDay);
        ll_listDoctorOrderMonth = view.findViewById(R.id.ll_listDoctorOrderMonth);
        btnTestListDoctorOrderMonth = view.findViewById(R.id.btnTestListDoctorOrderMonth);
        tvListDoctoOrderMonth = view.findViewById(R.id.tvListDoctoOrderMonth);
        rcv_listDoctorOrderMonth = view.findViewById(R.id.rcv_listDoctorOrderMonth);
        orderDetailDAO = new OrderDetailDAO(getContext());
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        String toDay = year + "/" + (month + 1) + "/" + day;
        ll_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog date = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String today;
                        if (day >=10) {
                            today = year + "/" + (month + 1) + "/" + day;
                        } else {
                            today = year + "/" + (month + 1) + "/0" + day;
                        }
                        tvDay.setText(today);
                    }
                }, year, month, day);
                date.show();
            }
        });
        ll_dateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog date = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        String today;
                        if(day>=10){
                            today = year + "/" + (month + 1) + "/" + day;
                        }
                        else{
                            today = year + "/" + (month + 1) + "/0" + day;
                        }

                        tvDayOrder.setText(today);
                    }
                }, year, month, day);
                date.show();
            }
        });
        btnTestOrderToDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StatisticalDTO statisticalDTO = orderDetailDAO.getStatisticalDTOByOrderToDay(tvDayOrder.getText().toString());
                tvNumBerOrderToDay.setText(statisticalDTO.getNumberOrder() + "");
                tvSumPriceOrderByToDay.setText(statisticalDTO.getSumPrice() + "vnđ");
            }
        });
        btnStartTestDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StatisticalDTO statisticalDTO = orderDetailDAO.getStatisticalDTOByStartToDay(tvDay.getText().toString());
                tvNumBerOrderStartToDay.setText(statisticalDTO.getNumberOrder() + "");
                tvSumPriceOrderStartByToDay.setText(statisticalDTO.getSumPrice() + "vnđ");
            }
        });
        ll_startMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog date = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        monthTest = (month + 1);
                        yearTest = year;
                        String today;
                        if(day>=10){
                            today = year + "/" + (month + 1);
                        }
                        else{
                            today = year + "/" + (month + 1);
                        }

                        tvStartMonth.setText(today);

                    }
                }, year, month, day);
                date.show();
            }
        });

        btnTestStartMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String month1 = yearTest + "/" + monthTest + "/00";
                String month2 = yearTest + "/" + monthTest + "/32";
                StatisticalDTO statisticalDTO = orderDetailDAO.getStatisticalDTOByToMonth(month1, month2);
                tvNumBerStartMonth.setText(statisticalDTO.getNumberOrder() + "");
                tvSumPriceStartMonth.setText(statisticalDTO.getSumPrice() + "vnđ");
            }
        });

        ll_OrderMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog date = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        monthTest = (month + 1);
                        yearTest = year;
                        String today;
                        if(day>=10){
                            today = year + "/" + (month + 1);
                        }
                        else{
                            today = year + "/" + (month + 1);
                        }

                        tvOrderMonth.setText(today);

                    }
                }, year, month, day);
                date.show();
            }
        });
        btnTestOrderMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String month1 = yearTest + "/" + monthTest + "/0";
                String month2 = yearTest + "/" + monthTest + "/32";
                StatisticalDTO statisticalDTO = orderDetailDAO.getStatisticalDTOByOrderMonth(month1, month2);
                tvNumBerOrderMonth.setText(statisticalDTO.getNumberOrder() + "");
                tvSumPriceOrderbyMonth.setText(statisticalDTO.getSumPrice() + "vnđ");
            }
        });

        ll_listDoctorOrderMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog date = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        monthTest = (month + 1);
                        yearTest = year;
                        String today;
                        if(day>=10){
                            today = year + "/" + (month + 1);
                        }
                        else{
                            today = year + "/" + (month + 1);
                        }

                        tvListDoctoOrderMonth.setText(today);

                    }
                }, year, month, day);
                date.show();
            }
        });
        btnTestListDoctorOrderMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String month1 = yearTest + "/" + monthTest + "/01";
                String month2 = yearTest + "/" + monthTest + "/31";
                ArrayList<DoctorDTO> list = orderDetailDAO.getListSumPriceByOrderMonth(month1, month2);
                Log.e("TAG", "onClick: list=" + list.size());
                AdapterListDoctorStatistical adapterListDoctorStatistical = new AdapterListDoctorStatistical(list, getContext());
                LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                rcv_listDoctorOrderMonth.setLayoutManager(manager);
                rcv_listDoctorOrderMonth.setAdapter(adapterListDoctorStatistical);
            }
        });


    }
}
