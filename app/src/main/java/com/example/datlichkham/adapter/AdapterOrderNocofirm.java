package com.example.datlichkham.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.ViewHolder.ItemOrderNoCofirmViewHolder;
import com.example.datlichkham.dao.FileDAO;
import com.example.datlichkham.dao.OrderDAO;
import com.example.datlichkham.dao.OrderDoctorDAO;
import com.example.datlichkham.doctor.CofrimOrderDoctorActivity;
import com.example.datlichkham.dto.FileDTO;
import com.example.datlichkham.dto.OrderDoctorDTO;

import java.util.ArrayList;

public class AdapterOrderNocofirm extends RecyclerView.Adapter<ItemOrderNoCofirmViewHolder> {
    public ArrayList<OrderDoctorDTO> list = new ArrayList<>();
    public Context context;

    public AdapterOrderNocofirm(ArrayList<OrderDoctorDTO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemOrderNoCofirmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_no_confirm,parent,false);
        return new ItemOrderNoCofirmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemOrderNoCofirmViewHolder holder, int position) {
        OrderDoctorDAO orderDoctorDAO = new OrderDoctorDAO(context);
        OrderDoctorDTO orderDoctorDTO1 = list.get(position);
        OrderDAO orderDAO = new OrderDAO(context);

        OrderDoctorDTO orderDoctorDTO = orderDoctorDAO.getOrderDoctorDtoById(orderDoctorDTO1.getId());

        FileDAO fileDAO = new FileDAO(context);
        FileDTO fileDTO = fileDAO.getFileDToById(orderDoctorDTO.getFile_id());
        holder.tvFile.setText(fileDTO.getFullname());
        holder.tvDetailFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông tin chi tiết bệnh nhân");
                builder.setMessage("Tên bệnh nhân: "+fileDTO.getFullname()+"\nNgày sinh: "
                        +fileDTO.getBirthday()+"\nCăn cước công dân: "+fileDTO.getCccd()
                        +"\nQuốc tịch: "+fileDTO.getCountry()+"\nBảo hiểm y tế : "
                        +fileDTO.getBhyt()+"\nCông việc :"+fileDTO.getJob()
                        +"\nĐịa chỉ nơi ở : "+fileDTO.getAddress());
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        holder.tvStartDate.setText("Ngày khám: "+orderDoctorDTO.getStart_date());
        holder.tvStartTime.setText("Giờ khám: "+orderDoctorDTO.getStart_time());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CofrimOrderDoctorActivity.class);
                intent.putExtra("fullName", fileDTO.getFullname());
                intent.putExtra("birthday", fileDTO.getBirthday());
                intent.putExtra("cccd", fileDTO.getCccd());
                intent.putExtra("country", fileDTO.getCountry());
                intent.putExtra("bhyt", fileDTO.getBhyt());
                intent.putExtra("job", fileDTO.getJob());
                intent.putExtra("address", fileDTO.getAddress());
                intent.putExtra("des", fileDTO.getDes());
                intent.putExtra("orderID",orderDoctorDTO1.getOrder_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
