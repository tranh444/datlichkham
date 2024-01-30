package com.example.datlichkham.adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHoderAccount_Doctor extends RecyclerView.ViewHolder {
    public CircleImageView imgDoctor;
    public TextView tvFullName;
    public TextView tvUserName;
    public TextView tvPassWord;
    public TextView tvPhoneNumber;



    public ViewHoderAccount_Doctor(@NonNull View itemView) {
        super(itemView);
        imgDoctor = itemView.findViewById(R.id.imgDoctor);
        tvFullName = itemView.findViewById(R.id.tvFullName);
        tvUserName = itemView.findViewById(R.id.tvUserName);
        tvPassWord = itemView.findViewById(R.id.tvPassWord);
        tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
    }


}
