package com.example.datlichkham.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.datlichkham.R;
import com.example.datlichkham.fragment.Fragment_Calender_Doctor;
import com.example.datlichkham.fragment.fragment_doctor.FragmentInforDoctor;
import com.example.datlichkham.fragment.fragment_doctor.FragmentListNoCofrimForDay;
import com.example.datlichkham.fragment.fragment_doctor.FragmentListYesCofrim;
import com.example.datlichkham.fragment.fragment_doctor.FragmentViewPagerDoctor;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DoctorActivity extends AppCompatActivity {
    private int mCount;
    private FrameLayout frameDoctor;
    private BottomNavigationView naviBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_account_doctor);
        frameDoctor = findViewById(R.id.frame_doctor);
        naviBottom = findViewById(R.id.naviBottom);
        replaceFragment(new Fragment_Calender_Doctor());
        naviBottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.m_lich_kham:
                        replaceFragment(new Fragment_Calender_Doctor());
                        break;
                    case R.id.m_statisticalDoctor:
                        replaceFragment(new FragmentViewPagerDoctor());
                        break;
                    case R.id.m_noCofrimForDay:
                        replaceFragment(new FragmentListNoCofrimForDay());
                        break;
                    case R.id.m_yesCofrim:
                        replaceFragment(new FragmentListYesCofrim());
                        break;
                    case R.id.m_inforDoctor:
                        replaceFragment(new FragmentInforDoctor());
                        break;
                }
                return true;
            }
        });

    }
    public void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame_doctor, fragment).commit();
    }
    public void setCount(int count, BottomNavigationView botnv){
        mCount = count;
        if (count > 0) {
            int id = botnv.getMenu().getItem(1).getItemId();
            BadgeDrawable badgeDrawable = botnv.getOrCreateBadge(id);
            badgeDrawable.setVisible(true);
            badgeDrawable.setNumber(count);
            badgeDrawable.setVerticalOffset(10);
            badgeDrawable.setHorizontalOffset(-10);
        }
    }

    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }
}