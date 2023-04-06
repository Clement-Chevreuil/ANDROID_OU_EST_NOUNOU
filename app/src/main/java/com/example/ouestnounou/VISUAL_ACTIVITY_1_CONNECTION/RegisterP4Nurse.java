package com.example.ouestnounou.VISUAL_ACTIVITY_1_CONNECTION;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ouestnounou.DAO.NurseDAO;
import com.example.ouestnounou.MODEL.Nurse;
import com.example.ouestnounou.R;

public class RegisterP4Nurse extends Fragment {

    Button validationButton, connexionButton;
    ImageButton backImageButton;
    EditText numChildrenEditText, ageMaxEditText, ageMinEditText;
    TextView errorTextView, stepTextView;
    Integer numChildrenInteger, ageMinInteger, ageMaxInteger;
    String firstNameString, lastNameString, categoryString, dateBirthString, addressString, cityString, postalCodeString, countryString, mailString, passwordString, phoneString, sexString;
    
    private static final String ARG_CATEGORY = "category";
    private static final String ARG_FIRST_NAME = "firstName";
    private static final String ARG_LAST_NAME = "lastName";
    private static final String ARG_DATE_BIRTH = "dateBirth";
    private static final String ARG_SEX = "sex";
    private static final String ARG_ADDRESS = "address";
    private static final String ARG_CITY = "city";
    private static final String ARG_POSTAL_CODE = "postalCode";
    private static final String ARG_COUNTRY = "country";
    private static final String ARG_MAIL = "mail";
    private static final String ARG_PASSWORD = "password";
    private static final String ARG_PHONE = "phone";


    public RegisterP4Nurse() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            firstNameString = getArguments().getString(ARG_FIRST_NAME);
            lastNameString = getArguments().getString(ARG_LAST_NAME);
            dateBirthString = getArguments().getString(ARG_DATE_BIRTH);
            categoryString = getArguments().getString(ARG_CATEGORY);
            cityString = getArguments().getString(ARG_CITY);
            sexString = getArguments().getString(ARG_SEX);

            countryString = getArguments().getString(ARG_COUNTRY);
            addressString = getArguments().getString(ARG_ADDRESS);
            postalCodeString = getArguments().getString(ARG_POSTAL_CODE);
            mailString = getArguments().getString(ARG_MAIL);
            passwordString = getArguments().getString(ARG_PASSWORD);
            phoneString = getArguments().getString(ARG_PHONE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.b_fragment_register_p4_nurse, container, false);

        numChildrenEditText = v.findViewById(R.id.num_children);
        ageMaxEditText = v.findViewById(R.id.age_max);
        ageMinEditText = v.findViewById(R.id.age_min);
        errorTextView = v.findViewById(R.id.error);
        validationButton = v.findViewById(R.id.validation);
        connexionButton = v.findViewById(R.id.connexion);
        backImageButton = v.findViewById(R.id.back);

        connexionButton.setOnClickListener(click_event);
        validationButton.setOnClickListener(click_event);
        backImageButton.setOnClickListener(click_event);

        return v;
    }

    private View.OnClickListener click_event = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.validation:

                    numChildrenInteger = Integer.valueOf(numChildrenEditText.getText().toString());
                    ageMinInteger = Integer.valueOf(ageMinEditText.getText().toString());
                    ageMaxInteger = Integer.valueOf(ageMaxEditText.getText().toString());

                    if(numChildrenInteger == null || ageMaxInteger == null || ageMinInteger == null)
                    {
                        errorTextView.setText("Veuillez remplir les champs correctements");
                    }
                    else
                    {
                        Nurse new_nurse = new Nurse(firstNameString, lastNameString, sexString, dateBirthString, cityString, countryString, phoneString, addressString, postalCodeString, mailString, passwordString, ageMinInteger, ageMaxInteger, numChildrenInteger);
                        NurseDAO nurseDAO = new NurseDAO(getContext());
                        nurseDAO.add(new_nurse);
                        Navigation.findNavController(view).navigate(R.id.action_registerP4Nurse_to_login);
                    }

                    break;

                case R.id.back:
                    Bundle args_back = new Bundle();
                    args_back.putString(ARG_FIRST_NAME, firstNameString);
                    args_back.putString(ARG_LAST_NAME, lastNameString);
                    args_back.putString(ARG_CATEGORY, categoryString);
                    args_back.putString(ARG_DATE_BIRTH, dateBirthString);
                    args_back.putString(ARG_ADDRESS, addressString);
                    args_back.putString(ARG_CITY, cityString);
                    args_back.putString(ARG_POSTAL_CODE, postalCodeString);
                    args_back.putString(ARG_COUNTRY, countryString);
                    args_back.putString(ARG_MAIL, mailString);
                    args_back.putString(ARG_PASSWORD, passwordString);
                    args_back.putString(ARG_PHONE, phoneString);
                    args_back.putString(ARG_SEX, sexString);
                    Toast.makeText(getContext(), "Inscription reussi, veuillez maintenant vous connecter", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).navigate(R.id.action_registerP4Nurse_to_registerP3);
                    break;

                case R.id.connexion:
                    Navigation.findNavController(view).navigate(R.id.action_registerP4Nurse_to_login);
                    break;
            }

        }
    };
}