package com.example.datlichkham.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.AdapterManagerAccount_User;
import com.example.datlichkham.dao.AccountDAO;
import com.example.datlichkham.dto.AccountDTO;

import java.util.ArrayList;


public class Fragment_ManagerAccountUser extends Fragment {
    private RecyclerView rcvManagerAccountUser;
    AccountDAO accountDAO;
    ArrayList<AccountDTO> listAccount;
    AdapterManagerAccount_User adapterAccount;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__manager_account_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvManagerAccountUser = view.findViewById(R.id.rcv_managerAccountUser);
        accountDAO = new AccountDAO(getContext());
        listAccount = accountDAO.getAccountUser();
        adapterAccount = new AdapterManagerAccount_User(accountDAO, listAccount);
        rcvManagerAccountUser.setAdapter(adapterAccount);
    }
}