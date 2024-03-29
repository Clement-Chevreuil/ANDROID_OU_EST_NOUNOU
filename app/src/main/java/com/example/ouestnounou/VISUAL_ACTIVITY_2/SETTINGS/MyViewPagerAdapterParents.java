package com.example.ouestnounou.VISUAL_ACTIVITY_2.SETTINGS;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ouestnounou.MODULE.AddressInformations;
import com.example.ouestnounou.MODULE.BaseInformations;
import com.example.ouestnounou.MODULE.EInformations;
import com.example.ouestnounou.VISUAL_ACTIVITY_1_CONNECTION.RegisterP3;

public class MyViewPagerAdapterParents extends FragmentStateAdapter {

    public MyViewPagerAdapterParents(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch (position){
           case 0:
               return new BaseInformations();
           case 1:
               return new AddressInformations();
           case 2:
               return new EInformations();
           default:
               return new RegisterP3();
       }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
