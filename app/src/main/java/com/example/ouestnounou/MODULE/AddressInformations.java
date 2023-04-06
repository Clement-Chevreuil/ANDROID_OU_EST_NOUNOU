package com.example.ouestnounou.MODULE;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ouestnounou.DAO.NurseDAO;
import com.example.ouestnounou.DAO.ParentsDAO;
import com.example.ouestnounou.MODEL.Nurse;
import com.example.ouestnounou.MODEL.Parents;
import com.example.ouestnounou.R;


public class AddressInformations extends Fragment {

    Button validationButton;
    EditText addressEditText, cityEditText, PostalCodeEditText, countryEditText;
    TextView errorTextView;
    String addressString, cityString, postalCodeString, countryString, categoryString;
    int idCategoryInt;
    ParentsDAO parentsDAO;
    NurseDAO nurseDAO;
    Parents parents;
    Nurse nurse;

    public AddressInformations() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        View v = inflater.inflate(R.layout.z_fragment_address_informations, container, false);

        SharedPreferences prefs = getContext().getSharedPreferences("session", MODE_PRIVATE);
        idCategoryInt = prefs.getInt("id", -1);
        categoryString = prefs.getString("category", "");

        validationButton = v.findViewById(R.id.validation);
        cityEditText = v.findViewById(R.id.city);
        countryEditText = v.findViewById(R.id.country);
        addressEditText = v.findViewById(R.id.address);
        PostalCodeEditText = v.findViewById(R.id.postal_code);
        errorTextView = v.findViewById(R.id.error);

        validationButton.setOnClickListener(click_event);

        if(categoryString.equals(getResources().getString(R.string.nurse))){
            nurseDAO = new NurseDAO(getContext());
            nurse = nurseDAO.getNurseById(idCategoryInt);
            cityEditText.setText(nurse.getCity());
            countryEditText.setText(nurse.getCountry());
            addressEditText.setText(nurse.getaddress());
            PostalCodeEditText.setText(nurse.getPostalCode());

        }
        else{
            parentsDAO = new ParentsDAO(getContext());
            parents = parentsDAO.getParent(idCategoryInt);
            cityEditText.setText(parents.getCity());
            countryEditText.setText(parents.getCountry());
            addressEditText.setText(parents.getaddress());
            PostalCodeEditText.setText(parents.getPostalCode());
        }

        return v;
    }

    private View.OnClickListener click_event = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.validation:

                    addressString = addressEditText.getText().toString() ;
                    cityString = cityEditText.getText().toString();
                    postalCodeString =  PostalCodeEditText.getText().toString();
                    countryString = countryEditText.getText().toString();

                    if(addressString.isEmpty() || cityString.isEmpty() || postalCodeString.isEmpty() || countryString.isEmpty())
                    {
                        errorTextView.setText("Veuillez remplir les champs correctement");
                    }
                    else
                    {
                        if(categoryString.equals(getResources().getString(R.string.nurse))){
                            nurse.setaddress(addressString);
                            nurse.setCity(cityString);
                            nurse.setPostalCode(postalCodeString);
                            nurse.setCountry(countryString);
                            nurseDAO.update(nurse);

                        }
                        else{
                            parents.setaddress(addressString);
                            parents.setCity(cityString);
                            parents.setPostalCode(postalCodeString);
                            parents.setCountry(countryString);
                            parentsDAO.update(parents);
                        }

                        Toast.makeText(getContext(), "Vos informations ont bien été mise à jour!", Toast.LENGTH_SHORT).show();
                    }

                    break;
            }

        }
    };

}