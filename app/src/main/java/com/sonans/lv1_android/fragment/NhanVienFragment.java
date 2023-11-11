package com.sonans.lv1_android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sonans.lv1_android.R;

import java.util.ArrayList;

public class NhanVienFragment extends Fragment {
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    WorkerMaleFragment tab1;
    WorkerFemaleFragment tab2;
    public RecyclerView rcv;
    boolean isTab1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nhan_vien, container, false);
        tabLayout = v.findViewById(R.id.tabLayout);
        viewPager2 = v.findViewById(R.id.viewPager2);
        ViewPager2Fragment adapterViewPager2 = new ViewPager2Fragment(this);
        viewPager2.setAdapter(adapterViewPager2);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:{
                        tab.setText("Nhan Vien Nam");
                        isTab1 = true;
                        break;
                    }
                    case 1:{
                        tab.setText("Nhan Vien Nu");
                        isTab1 = false;
                        break;
                    }
                    default:break;
                }
            }
        }).attach();
        return v;
    }
}