package com.sonans.lv1_android.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPager2Fragment extends FragmentStateAdapter {

    public ViewPager2Fragment(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return new WorkerMaleFragment();
        }
        return new WorkerFemaleFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
