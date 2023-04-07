package com.example.ouestnounou.VISUAL_ACTIVITY_2.SETTINGS;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ouestnounou.R;
import com.google.android.material.tabs.TabLayout;

public class Settings extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    MyViewPagerAdapterParents myViewPagerAdapterParents;
    MyViewPagerAdapterNurse myViewPagerAdapterNurse;
    SharedPreferences prefs;
    String categoryString;
    int idCategoryInt;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        prefs = getContext().getSharedPreferences("session", MODE_PRIVATE);
        categoryString = prefs.getString("categoryString", "");
        idCategoryInt = prefs.getInt("id", 0);

        if(categoryString.equals(getResources().getString(R.string.nurse))){
            v = inflater.inflate(R.layout.e_fragment_settings_nurse, container, false);
            tabLayout = v.findViewById(R.id.table_layout);
            viewPager = v.findViewById(R.id.view_pager);
            myViewPagerAdapterNurse = new MyViewPagerAdapterNurse(getActivity());
            viewPager.setAdapter(myViewPagerAdapterNurse);
        }
        else{
            v = inflater.inflate(R.layout.e_fragment_settings, container, false);
            tabLayout = v.findViewById(R.id.table_layout);
            viewPager = v.findViewById(R.id.view_pager);
            myViewPagerAdapterParents = new MyViewPagerAdapterParents(getActivity());
            viewPager.setAdapter(myViewPagerAdapterParents);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
        
        return v;
    }


}