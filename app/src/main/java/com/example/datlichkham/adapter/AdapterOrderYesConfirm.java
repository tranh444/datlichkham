package com.example.datlichkham.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.ViewHolder.ItemOrderYesCofirmViewHolder;
import com.example.datlichkham.dao.FileDAO;
import com.example.datlichkham.dao.OrderDAO;
import com.example.datlichkham.dao.OrderDoctorDAO;
import com.example.datlichkham.dto.FileDTO;
import com.example.datlichkham.dto.OrderDTO;
import com.example.datlichkham.dto.OrderDoctorDTO;

import java.util.ArrayList;

public class AdapterOrderYesConfirm extends RecyclerView.Adapter<ItemOrderYesCofirmViewHolder> {

    public ArrayList<OrderDoctorDTO> list = new ArrayList<>();
    public Context context;

    public AdapterOrderYesConfirm(ArrayList<OrderDoctorDTO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemOrderYesCofirmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_yes_confirm,parent,false);
        return new ItemOrderYesCofirmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemOrderYesCofirmViewHolder holder, int position) {
        OrderDoctorDAO orderDoctorDAO = new OrderDoctorDAO(context);
        OrderDoctorDTO orderDoctorDTO1 = list.get(position);
        OrderDAO orderDAO = new OrderDAO(context);
        OrderDTO orderDTO = orderDAO.getOrderDTOById(orderDoctorDTO1.getOrder_id());

        OrderDoctorDTO orderDoctorDTO = orderDoctorDAO.getOrderDoctorDtoById(orderDoctorDTO1.getId());

        FileDAO fileDAO = new FileDAO(context);
        FileDTO fileDTO = fileDAO.getFileDToById(orderDoctorDTO.getFile_id());
        holder.tvFile.setText(fileDTO.getFullname());
        holder.tvDetailFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông tin chi tiết bệnh nhân");
                builder.setMessage("Tên bệnh nhân: "+fileDTO.getFullname()+"\nNgày sinh: "+fileDTO.getBirthday()+"\nCăn cước công dân: "+fileDTO.getCccd()+"\nQuốc tịch: "+fileDTO.getCountry()+"\nBảo hiểm y tế : "+fileDTO.getBhyt()+"\nCông việc :"+fileDTO.getJob()+"\nĐịa chỉ nơi ở : "+fileDTO.getAddress()+"\nGhi chú: "+orderDTO.getNote());
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        holder.tvStartDate.setText("Ngày khám: "+orderDoctorDTO.getStart_date());
        holder.tvStartTime.setText("Giờ khám: "+orderDoctorDTO.getStart_time());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
