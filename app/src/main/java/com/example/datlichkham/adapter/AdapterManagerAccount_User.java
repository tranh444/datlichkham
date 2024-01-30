package com.example.datlichkham.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.ViewHolder.ViewHoderAccount_User;
import com.example.datlichkham.dao.AccountDAO;
import com.example.datlichkham.dto.AccountDTO;

import java.util.ArrayList;

public class AdapterManagerAccount_User extends RecyclerView.Adapter<ViewHoderAccount_User> {
    AccountDAO accountDAO;
    ArrayList<AccountDTO> listAccountUser;
    Context context;

    public AdapterManagerAccount_User(AccountDAO accountDAO, ArrayList<AccountDTO> listAccountUser) {
        this.accountDAO = accountDAO;
        this.listAccountUser = listAccountUser;
    }

    @NonNull
    @Override
    public ViewHoderAccount_User onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_account_user, parent,false);
        context = parent.getContext();
        accountDAO = new AccountDAO(context);
        return new ViewHoderAccount_User(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderAccount_User holder, int position) {
        final int index =position;
        AccountDTO accountDTO = listAccountUser.get(index);
        holder.tvFullName.setText(accountDTO.getFullName());
        holder.tvUserName.setText(accountDTO.getUserName());
        holder.tvPhoneNumber.setText(accountDTO.getPhoneNumber());
        if(accountDTO.getImg()!=null){
            Uri uri = Uri.parse(accountDTO.getImg());
            holder.imgUser.setImageURI(uri);
        }
    }

    @Override
    public int getItemCount() {
        return listAccountUser==null?0: listAccountUser.size();
    }

}
