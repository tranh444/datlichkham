package com.example.datlichkham.fragment.fragment_nav_user;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.datlichkham.DoctorByServiceActivity;
import com.example.datlichkham.R;
import com.example.datlichkham.adapter.BannerAdapter;
import com.example.datlichkham.dao.ServicesDAO;
import com.example.datlichkham.dto.Banner;
import com.example.datlichkham.dto.ServicesDTO;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class Fragment_home extends Fragment {
    private RelativeLayout itemService1;
    private RelativeLayout itemDoctor;
    private RelativeLayout itemService2;
    private RelativeLayout itemService3;
    private RelativeLayout itemService4;
//    DrawerLayout drawerLayout;
//    TextView tvHiName;
//    ImageView imgOpenNav, imgAvt;
    CircleIndicator circleIndicator;
    ViewPager viewPager;
    BannerAdapter bannerAdapter;
    List<Banner> list;
    ServicesDAO servicesDAO;
    ArrayList<ServicesDTO> listService;
    Handler handler = new Handler(Looper.getMainLooper());
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentPosition = viewPager.getCurrentItem();
            if (currentPosition == list.size() - 1) {
                viewPager.setCurrentItem(0);
            } else {
                viewPager.setCurrentItem(currentPosition + 1);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        servicesDAO = new ServicesDAO(getContext());
        viewPager = view.findViewById(R.id.view_pager);
        circleIndicator = view.findViewById(R.id.circle_indicator);

        list = getListPhoto();
        bannerAdapter = new BannerAdapter(getContext(), list);
        viewPager.setAdapter(bannerAdapter);

        circleIndicator.setViewPager(viewPager);
        handler.postDelayed(runnable, 3000);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        listService = servicesDAO.selectAll();
        itemService1 = view.findViewById(R.id.item_service1);
        itemDoctor = view.findViewById(R.id.item_doctor);
        itemService2 = view.findViewById(R.id.item_service2);
        itemService3 = view.findViewById(R.id.item_service3);
        itemService4 = view.findViewById(R.id.item_service4);
        itemService1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DoctorByServiceActivity.class);
                intent.putExtra("serviceid", listService.get(0).getServicesId());
                intent.putExtra("serviceName", listService.get(0).getServicesName());
                startActivity(intent);
            }
        });
        itemService2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DoctorByServiceActivity.class);
                intent.putExtra("serviceid", listService.get(1).getServicesId());
                intent.putExtra("serviceName", listService.get(1).getServicesName());
                startActivity(intent);
            }
        });
        itemService3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DoctorByServiceActivity.class);
                intent.putExtra("serviceid", listService.get(2).getServicesId());
                intent.putExtra("serviceName", listService.get(2).getServicesName());
                startActivity(intent);
            }
        });
        itemService4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DoctorByServiceActivity.class);
                intent.putExtra("serviceid", listService.get(3).getServicesId());
                intent.putExtra("serviceName", listService.get(3).getServicesName());
                startActivity(intent);
            }
        });
        itemDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DoctorByServiceActivity.class);
                intent.putExtra("serviceid", -1);
                intent.putExtra("serviceName", "List of Doctors");
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

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }
}