package com.example.datlichkham.fragment.fragment_doctor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.datlichkham.R;
import com.example.datlichkham.SignInActivity;
import com.example.datlichkham.dao.AccountDAO;
import com.example.datlichkham.dto.AccountDTO;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;


public class FragmentInforDoctor extends Fragment {
    ImageView imgLogOut;
    private TextInputLayout tilFullname;
    private TextInputLayout tilPhoneNumber;
    private MaterialButton btnConfirm;
    AccountDAO accountDAO;
    AccountDTO accountDTO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_infor_doctor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgLogOut = (ImageView) view.findViewById(R.id.img_log_out);
        imgLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SignInActivity.class));
                getActivity().finish();
            }
        });
        accountDAO = new AccountDAO(getContext());
        SharedPreferences preferences = getActivity().getSharedPreferences("getIdUser", Context.MODE_PRIVATE);
        int idUser = preferences.getInt("idUser", -1);
        accountDTO = accountDAO.getDtoAccount(idUser);
        tilFullname = view.findViewById(R.id.til_fullname);
        tilPhoneNumber = view.findViewById(R.id.til_phone_number);
        tilFullname.getEditText().setText(accountDTO.getFullName());
        tilPhoneNumber.getEditText().setText(accountDTO.getPhoneNumber());
        btnConfirm = view.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tilFullname.getEditText().getText().toString().trim().isEmpty() || tilPhoneNumber.getEditText().getText().toString().trim().isEmpty()) {
                    if (tilFullname.getEditText().getText().toString().trim().isEmpty()) {
                        tilFullname.setError("FullName can't empty");
                    }
                    if (tilPhoneNumber.getEditText().getText().toString().trim().isEmpty()) {
                        tilPhoneNumber.setError("PhoneNumber can't empty");
                    }
                } else {
                    tilFullname.setError("");
                    tilPhoneNumber.setError("");
                    accountDTO.setFullName(tilFullname.getEditText().getText().toString());
                    accountDTO.setPhoneNumber(tilPhoneNumber.getEditText().getText().toString());
                    int res = accountDAO.updateAccount(accountDTO);
                    if (res > 0) {
                        AccountDTO obj = accountDAO.getDtoAccount(idUser);
                        tilPhoneNumber.getEditText().setText(obj.getPhoneNumber());
                        tilFullname.getEditText().setText(obj.getFullName());
                        Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}