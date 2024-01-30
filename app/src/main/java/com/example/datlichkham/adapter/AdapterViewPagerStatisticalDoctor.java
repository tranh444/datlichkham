package com.example.datlichkham.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.datlichkham.fragment.Fragment_Statistical_month_doctor;
import com.example.datlichkham.fragment.fragment_doctor.FragmentStatisticalDoctor;
import com.example.datlichkham.fragment.fragment_doctor.FragmentStatisticalDoctorForDay;

public class AdapterViewPagerStatisticalDoctor extends FragmentStateAdapter {

    public AdapterViewPagerStatisticalDoctor(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Fragment_Statistical_month_doctor();
            case 1:
                return new FragmentStatisticalDoctorForDay();
            case 2:
                return new FragmentStatisticalDoctor();
            default:
                return new Fragment_Statistical_month_doctor();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
