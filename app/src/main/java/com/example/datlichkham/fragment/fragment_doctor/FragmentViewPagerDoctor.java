package com.example.datlichkham.fragment.fragment_doctor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.AdapterViewPagerStatisticalDoctor;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class FragmentViewPagerDoctor extends Fragment {
    private TabLayout tabVpStatisticalDoctor;
    private ViewPager2 vpStatisticalDoctor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager_doctor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabVpStatisticalDoctor = view.findViewById(R.id.tab_vp_statisticalDoctor);
        vpStatisticalDoctor = view.findViewById(R.id.vp_statisticalDoctor);
        AdapterViewPagerStatisticalDoctor adapterViewPagerStatisticalDoctor = new AdapterViewPagerStatisticalDoctor(this);
        vpStatisticalDoctor.setAdapter(adapterViewPagerStatisticalDoctor);
        TabLayoutMediator m = new TabLayoutMediator(tabVpStatisticalDoctor, vpStatisticalDoctor, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Thống kê tháng này");
                } else if (position == 1) {
                    tab.setText("Thống kê hôm nay");
                } else if (position == 2) {
                    tab.setText("Thống kê");
                }
            }
        });
        m.attach();
    }
}