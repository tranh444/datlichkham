package com.example.datlichkham.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.ViewHolder.ViewHoderAccount_Doctor;
import com.example.datlichkham.dao.AccountDAO;
import com.example.datlichkham.dto.AccountDTO;

import java.util.ArrayList;

public class AdapterManagerAccount_Doctor extends RecyclerView.Adapter<ViewHoderAccount_Doctor> {
    AccountDAO accountDAO;
    ArrayList<AccountDTO> listAccount;
    Context context;

    public AdapterManagerAccount_Doctor(AccountDAO accountDAO, ArrayList<AccountDTO> listAccount) {
        this.accountDAO = accountDAO;
        this.listAccount = listAccount;
    }

    @NonNull
    @Override
    public ViewHoderAccount_Doctor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_account_doctor, parent,false);
        context = parent.getContext();
        accountDAO = new AccountDAO(context);
        return new ViewHoderAccount_Doctor(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderAccount_Doctor holder, int position) {
        final int index =position;
        AccountDTO accountDTO = listAccount.get(index);
        holder.tvFullName.setText(accountDTO.getFullName());
        holder.tvUserName.setText(accountDTO.getUserName());
        holder.tvPassWord.setText(accountDTO.getPassWord());
        holder.tvPhoneNumber.setText(accountDTO.getPhoneNumber());
        if(accountDTO.getImg()!=null){
            Uri uri = Uri.parse(accountDTO.getImg());
            holder.imgDoctor.setImageURI(uri);
        }
    }

    @Override
    public int getItemCount() {
        return listAccount==null?0: listAccount.size();
    }
}
