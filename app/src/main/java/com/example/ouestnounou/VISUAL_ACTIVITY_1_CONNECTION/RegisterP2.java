package com.example.ouestnounou.VISUAL_ACTIVITY_1_CONNECTION;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ouestnounou.R;

public class RegisterP2 extends Fragment {

    Button validationButton, connexionButton;
    ImageButton backImageButton;
    EditText addressEditText, cityEditText, postalCodeEditText, countryEditText;
    TextView errorTextView, stepTextView;
    String firstNameTextString, lastNameString, categoryString, dateBirthString, addressString, cityString, postalCodeString, countryString, sexString;

    private static final String ARG_CATEGORY = "category";
    private static final String ARG_FIRST_NAME = "firstName";
    private static final String ARG_LAST_NAME = "lastName";
    private static final String ARG_DATE_BIRTH = "dateBirth";
    private static final String ARG_SEX = "sex";
    private static final String ARG_ADDRESS = "address";
    private static final String ARG_CITY = "city";
    private static final String ARG_POSTAL_CODE = "postalCode";
    private static final String ARG_COUNTRY = "country";

    public RegisterP2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            firstNameTextString = getArguments().getString(ARG_FIRST_NAME);
            lastNameString = getArguments().getString(ARG_LAST_NAME);
            dateBirthString = getArguments().getString(ARG_DATE_BIRTH);
            categoryString = getArguments().getString(ARG_CATEGORY);
            sexString = getArguments().getString(ARG_SEX);
            cityString = getArguments().getString(ARG_CITY);
            countryString = getArguments().getString(ARG_COUNTRY);
            addressString = getArguments().getString(ARG_ADDRESS);
            postalCodeString = getArguments().getString(ARG_POSTAL_CODE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.b_fragment_register_p2, container, false);

        stepTextView = v.findViewById(R.id.step);
        cityEditText = v.findViewById(R.id.city);
        countryEditText = v.findViewById(R.id.country);
        addressEditText = v.findViewById(R.id.address);
        postalCodeEditText = v.findViewById(R.id.postal_code);
        errorTextView = v.findViewById(R.id.error);
        validationButton = v.findViewById(R.id.validation);
        connexionButton = v.findViewById(R.id.connexion);
        backImageButton = v.findViewById(R.id.back);

        validationButton.setOnClickListener(click_event);
        connexionButton.setOnClickListener(click_event);
        backImageButton.setOnClickListener(click_event);

        if(categoryString.equals(getResources().getString(R.string.parents)))
        {
            stepTextView.setText("Etape 2/3");
        }
        else if(categoryString.equals(getResources().getString(R.string.nurse)))
        {
            stepTextView.setText("Etape 2/4");
        }

        if(getArguments() != null && addressString != null && cityString != null && postalCodeString != null && countryString != null)
        {
            addressEditText.setText(addressString);
            cityEditText.setText(cityString);
            postalCodeEditText.setText(postalCodeString);
            countryEditText.setText(countryString);
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
                    postalCodeString =  postalCodeEditText.getText().toString();
                    countryString = countryEditText.getText().toString();

                    if(addressString.isEmpty() || cityString.isEmpty() || postalCodeString.isEmpty() || countryString.isEmpty())
                    {
                        errorTextView.setText("Veuillez remplir les champs correctement");
                    }
                    else
                    {
                        Bundle args = new Bundle();
                        args.putString(ARG_FIRST_NAME, firstNameTextString);
                        args.putString(ARG_LAST_NAME, lastNameString);
                        args.putString(ARG_CATEGORY, categoryString);
                        args.putString(ARG_DATE_BIRTH, dateBirthString);
                        args.putString(ARG_SEX, sexString);
                        args.putString(ARG_ADDRESS, addressString);
                        args.putString(ARG_CITY, cityString);
                        args.putString(ARG_POSTAL_CODE,postalCodeString);
                        args.putString(ARG_COUNTRY, countryString);
                        Navigation.findNavController(view).navigate(R.id.action_registerP2_to_registerP3, args);
                    }
                    break;

                case R.id.back:
                    Bundle args_back = new Bundle();
                    args_back.putString(ARG_FIRST_NAME, firstNameTextString);
                    args_back.putString(ARG_LAST_NAME, lastNameString);
                    args_back.putString(ARG_CATEGORY, categoryString);
                    args_back.putString(ARG_DATE_BIRTH, dateBirthString);
                    args_back.putString(ARG_SEX, sexString);
                    Navigation.findNavController(view).navigate(R.id.action_registerP2_to_registerP1, args_back);
                    break;

                case R.id.connexion:
                    Navigation.findNavController(view).navigate(R.id.action_registerP2_to_login);
                    break;
            }

        }
    };

}