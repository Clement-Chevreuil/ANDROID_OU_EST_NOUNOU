package com.example.ouestnounou.VISUAL_ACTIVITY_2.SEARCH;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.fragment.app.FragmentActivity;

import com.example.ouestnounou.DAO.NurseDAO;
import com.example.ouestnounou.MODEL.Nurse;
import com.example.ouestnounou.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
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

        googleMap.setOnInfoWindowClickListener(null);
        Geocoder geocoder = new Geocoder(getContext());

        NurseDAO nurseDAO = new NurseDAO(getContext());
        ArrayList<Nurse> list_nurse = nurseDAO.getNursesNotComplete();

        if(list_nurse != null)
        {
            for(Nurse nurse : list_nurse ) {

                List<Address> addresses = null;
                try {
                    addresses = geocoder.getFromLocationName(nurse.getaddress() + nurse.getCity(), 1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


                if (addresses.size() > 0) {
                    Address address = addresses.get(0);
                    double latitude = address.getLatitude();
                    double longitude = address.getLongitude();
                    LatLng location = new LatLng(latitude, longitude);
                    MarkerOptions markerOptions = new MarkerOptions().position(location);
                    Marker marker = googleMap.addMarker(markerOptions);
                    marker.setTag(nurse);

                    View infoWindow = getLayoutInflater().inflate(R.layout.e_fragment_search_button, null);

                    googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick(Marker marker) {
                            // Récupérer l'objet Nurse associé au marqueur cliqué
                            Nurse nurse = (Nurse) marker.getTag();
                            Bundle bundle = new Bundle();
                            bundle.putInt("nurse_id", nurse.getId());
                            // Naviguer vers la vue suivante en passant l'objet Nurse à l'aide du Bundle
                            Navigation.findNavController(getView()).navigate(R.id.action_search_parents_to_parentsSearchNurse, bundle);
                        }
                    });

                    googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                        @Override
                        public View getInfoWindow(Marker marker) {
                            return null;
                        }

                        @Override
                        public View getInfoContents(Marker marker) {
                            Nurse nurse = (Nurse) marker.getTag();
                            View infoWindow = createInfoWindowView(nurse);
                            return infoWindow;
                        }
                    });
                }
            }
        }
    }

    private View createInfoWindowView(Nurse nurse) {
        View infoWindow = getLayoutInflater().inflate(R.layout.e_fragment_search_button, null);
        TextView informations = (TextView) infoWindow.findViewById(R.id.infos);
        informations.setText(nurse.getFirstName() + " " + nurse.getLastName() + "\n" + nurse.getaddress() + " " + nurse.getCity());
        return infoWindow;
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