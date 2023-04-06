package com.example.ouestnounou.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ouestnounou.DAO.ChildrenDAO;
import com.example.ouestnounou.MODEL.Children;
import com.example.ouestnounou.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    SharedPreferences prefs;
    String categoryString;
    int idCategoryInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_activity_parents);

        //NAVIGATION
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_menu_parents);
        NavController navController = navHostFragment.getNavController();
        BottomNavigationView menu = (BottomNavigationView) findViewById(R.id.BottomNavigation);

        prefs = getApplicationContext().getSharedPreferences("session", MODE_PRIVATE);
        categoryString = prefs.getString("category", "");
        idCategoryInt = prefs.getInt("id", 0);

        if(categoryString.equals(getResources().getString(R.string.nurse))){
            menu.setSelectedItemId(R.id.pay);
            navController.navigate(R.id.contract);
        }
        else{
            menu.setSelectedItemId(R.id.childrens);
            navController.navigate(R.id.children);
        }


        menu.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            ChildrenDAO childrenDAO = new ChildrenDAO(getApplicationContext());
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.pay:
                        navController.navigate(R.id.contract);
                        return true;
                    case R.id.settings:
                        navController.navigate(R.id.settings_parents);
                        return true;
                    case R.id.calendar:
                        if(categoryString.equals(getResources().getString(R.string.nurse))){
                            ArrayList<Children> childrens = childrenDAO.getChildrensByNurseId(idCategoryInt);
                            if(childrens == null)
                            {
                                Toast.makeText(getApplicationContext(), "Il vous faut au moins un contrat pour afficher acceder a cette page", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                navController.navigate(R.id.calendar_parents_gestionning);
                            }
                        }
                        else{
                            ArrayList<Children> childrens = childrenDAO.getChildrensByParentId(idCategoryInt);
                            if(childrens == null)
                            {
                                Toast.makeText(getApplicationContext(), "Il vous faut d'abord créer des enfants pour y acceder", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                navController.navigate(R.id.calendar_parents_gestionning);
                            }
                        }

                        return true;
                    case R.id.childrens:

                        if(categoryString.equals(getResources().getString(R.string.nurse))){

                            ArrayList<Children> childrensNurse = childrenDAO.getChildrensByNurseId(idCategoryInt);
                            if(childrensNurse == null)
                            {
                                Toast.makeText(getApplicationContext(), "Il vous faut au moins un contrat pour afficher acceder a cette page", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                navController.navigate(R.id.children);
                            }
                        }
                        else{
                            navController.navigate(R.id.children);
                        }

                        return true;
                    case R.id.search:
                        if( ! categoryString.equals(getResources().getString(R.string.nurse))){
                            ArrayList<Children> childrensVerif = childrenDAO.getChildrensByParentId(idCategoryInt);
                            ArrayList<Children> childrensNurse = childrenDAO.getChildrensByParentIdWithoutNurse(idCategoryInt);

                            if (childrensVerif == null) {
                                Toast.makeText(getApplicationContext(), "Il vous faut des enfants pour acceder a cette page", Toast.LENGTH_SHORT).show();
                            }
                            else if(childrensNurse == null)
                            {
                                Toast.makeText(getApplicationContext(), "Tout vos enfants ont deja une nounou", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                navController.navigate(R.id.search_parents);
                            }

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Fonctionnalité non disponible pour le moment", Toast.LENGTH_SHORT).show();
                        }
                        return true;

                        default:
                        return false;
                }
            }
        });
    }
}