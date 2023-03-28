package com.example.ouestnounou.VISUAL_ACTIVITY_2_PARENTS;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.ouestnounou.DAO.NurseDAO;
import com.example.ouestnounou.MODEL.Nurse;
import com.example.ouestnounou.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Search extends Fragment implements OnMapReadyCallback{


    private MapView mapView;
    private GoogleMap googleMap;





    public Search() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.e_fragment_search, container, false);

        // Obtient une référence au SupportMapFragment
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapView);

        // Si le SupportMapFragment n'existe pas, crée-le et ajoute-le
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.mapView, mapFragment).commit();
        }

        // Initialise la carte avec l'interface OnMapReadyCallback
        mapFragment.getMapAsync(this);



        return view;
    }



    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        Geocoder geocoder = new Geocoder(getContext());

        NurseDAO nurseDAO = new NurseDAO(getContext());
        ArrayList<Nurse> list_nurse = nurseDAO.getNurses();

        for(Nurse nurse : list_nurse ) {

            Log.e("test", nurse.getAdress());

            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocationName(nurse.getAdress() + nurse.getCity(), 1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                double latitude = address.getLatitude();
                double longitude = address.getLongitude();
                LatLng location = new LatLng(latitude, longitude);
                MarkerOptions markerOptions = new MarkerOptions().position(location).title(nurse.getFist_name() + " " + nurse.getLast_name()).snippet(nurse.getAdress() + " " + nurse.getCity());
                googleMap.addMarker(markerOptions);
            }
        }



    }


    @Override
    public void onStart() {
        super.onStart();
        if (googleMap != null) {
            googleMap.clear();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (googleMap != null) {
            googleMap.clear();
        }
    }
}