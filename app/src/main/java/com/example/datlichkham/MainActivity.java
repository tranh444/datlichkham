package com.example.datlichkham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.datlichkham.dto.Banner;
import com.example.datlichkham.fragment.fragment_nav_user.Fragment_bookingHistory;
import com.example.datlichkham.fragment.fragment_nav_user.Fragment_file;
import com.example.datlichkham.fragment.fragment_nav_user.Fragment_home_new;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    TextView tvHiName, tv_title;
    ImageView imgOpenNav, imgAvt;

    int back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        back = intent.getIntExtra("back", -1);
//        servicesDAO = new ServicesDAO(getBaseContext());

//        viewPager = findViewById(R.id.view_pager);
//        circleIndicator = findViewById(R.id.circle_indicator);
        drawerLayout = findViewById(R.id.drawerLayout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        imgOpenNav = toolbar.findViewById(R.id.img_open_nav);
        tv_title = findViewById(R.id.tv_title);
//        tvHiName.setText("Hi " + getIntent().getStringExtra("fullname") + " !");
//        list = getListPhoto();
//        bannerAdapter = new BannerAdapter(this, list);
//        viewPager.setAdapter(bannerAdapter);

//        circleIndicator.setViewPager(viewPager);
//        handler.postDelayed(runnable, 3000);
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                handler.removeCallbacks(runnable);
//                handler.postDelayed(runnable, 3000);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

        imgOpenNav.setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));

        NavigationView navView = findViewById(R.id.nav_view);
        replaceFragment(new Fragment_home_new());
        navView.getMenu().findItem(R.id.nav_home_user).setChecked(true);
        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.nav_home_user:
                    replaceFragment(new Fragment_home_new());
                    tv_title.setText("BookingCare");
                    navView.getMenu().findItem(R.id.nav_home_user).setChecked(true);
                    navView.getMenu().findItem(R.id.nav_file).setChecked(false);
                    navView.getMenu().findItem(R.id.nav_booking_history).setChecked(false);
                    break;
                case R.id.nav_file:
                    replaceFragment(new Fragment_file());
                    tv_title.setText("Hồ Sơ Bệnh Án");
                    navView.getMenu().findItem(R.id.nav_home_user).setChecked(false);
                    navView.getMenu().findItem(R.id.nav_file).setChecked(true);
                    navView.getMenu().findItem(R.id.nav_booking_history).setChecked(false);
                    break;
                case R.id.nav_booking_history:
                    replaceFragment(new Fragment_bookingHistory());
                    tv_title.setText("Lịch Sử Khám Bệnh");
                    navView.getMenu().findItem(R.id.nav_home_user).setChecked(false);
                    navView.getMenu().findItem(R.id.nav_file).setChecked(false);
                    navView.getMenu().findItem(R.id.nav_booking_history).setChecked(true);
                    break;
                case R.id.nav_exit:
                    Intent back = new Intent(getApplicationContext(), SignInActivity.class);
                    startActivity(back);
                    finish();
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
        View headerView = navView.getHeaderView(0);
        tvHiName = headerView.findViewById(R.id.tv_hi_name);
        imgAvt = headerView.findViewById(R.id.imgAvt);
        SharedPreferences preferences = getSharedPreferences("getIdUser", MODE_PRIVATE);
        String imgUser = preferences.getString("imgUser", "");
        String tvFullName = preferences.getString("fullname", "");
        tvHiName.setText("Hi " + tvFullName + " !");
        if (!imgUser.isEmpty()) {
            Uri uri = Uri.parse(imgUser);
            imgAvt.setImageURI(uri);
        }

        imgAvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), UpdateUserActivity.class);
                startActivity(intent);
            }
        });


    }

    private List<Banner> getListPhoto() {
        List<Banner> list = new ArrayList<>();
        list.add(new Banner(R.drawable.banner1));
        list.add(new Banner(R.drawable.banner2));
        list.add(new Banner(R.drawable.banner3));
        return list;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framer, fragment).commit();
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        handler.removeCallbacks(runnable);
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        handler.postDelayed(runnable, 3000);
//    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (back == 1) {
            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
            startActivity(intent);
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}