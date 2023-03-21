package com.example.ouestnounou.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.ouestnounou.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Parents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_activity_parents);

        //NAVIGATION
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_menu_parents);
        NavController navController = navHostFragment.getNavController();
        BottomNavigationView menu = (BottomNavigationView) findViewById(R.id.BottomNavigation);
        menu.setSelectedItemId(R.id.settings);

        menu.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.pay:
                        navController.navigate(R.id.pay_parents);
                        return true;
                    case R.id.settings:
                        navController.navigate(R.id.settings_parents);
                        return true;
                    case R.id.calendar:
                        navController.navigate(R.id.calendar_parents_gestionning);
                        return true;
                    /*case R.id.childrens:
                        navController.navigate(R.id.calendar_gestionning);
                        return true;*/
                    case R.id.search:
                        navController.navigate(R.id.search_parents);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}