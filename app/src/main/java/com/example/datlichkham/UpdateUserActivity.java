package com.example.datlichkham;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datlichkham.dao.AccountDAO;
import com.example.datlichkham.dto.AccountDTO;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateUserActivity extends AppCompatActivity {
    private ImageView imgUser,img_back;
    private AccountDAO accountDAO;
    private String uriImg;
    private TextView tvFullName;
    private TextInputEditText edt_fullname;
    private MaterialButton btn_continue;
    private Boolean check = false;
    private AccountDTO accountDTO;
    private int idUser;
    private String img, fullNameUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        imgUser = findViewById(R.id.imgUser);
        tvFullName = findViewById(R.id.tvFullName);
        edt_fullname = findViewById(R.id.edt_fullname);
        btn_continue = findViewById(R.id.btn_continue);
        img_back = findViewById(R.id.img_back);

        accountDAO = new AccountDAO(this);

        //Lấy dữ liệu
        SharedPreferences preferences = getSharedPreferences("getIdUser", MODE_PRIVATE);
        fullNameUser = preferences.getString("fullname", "");
        img = preferences.getString("imgUser", "");
        idUser = preferences.getInt("idUser", -1);
        if (!img.isEmpty()) {
            Uri uri = Uri.parse(img.trim());
            imgUser.setImageURI(uri);
        }
        tvFullName.setText(fullNameUser);

        imgUser.setOnClickListener(view -> {
            if (check == false) {
                checkPermission();
                check = true;
            } else {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
        edt_fullname.setText(fullNameUser);

        //Lấy ra đối tượng đăng
        accountDTO = accountDAO.getDtoAccount(idUser);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accountDTO.setFullName(edt_fullname.getText().toString());
                accountDTO.setImg(uriImg);
                int res = accountDAO.updateAccount(accountDTO);
                if (res > 0) {
                    Toast.makeText(UpdateUserActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (data != null) {
                Uri uri = data.getData();
                //Lấy ra uri của ảnh
                uriImg = uri + "";
                //Gắn ảnh vào imgEditUser
                imgUser.setImageURI(uri);
            }
        }
    }
    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (UpdateUserActivity.this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissions, 1);
            } else {
                Intent intentGrallary = new Intent(Intent.ACTION_PICK);
                intentGrallary.setType("image/*");
                startActivityForResult(intentGrallary, 1);
            }
        }
    }

}