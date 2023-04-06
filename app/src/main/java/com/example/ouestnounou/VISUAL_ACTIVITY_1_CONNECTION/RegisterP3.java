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

import com.example.ouestnounou.DAO.ParentsDAO;
import com.example.ouestnounou.MODEL.Parents;
import com.example.ouestnounou.R;

public class RegisterP3 extends Fragment {

    Button validationButton, connexionButton;
    ImageButton backImageButton;
    EditText phoneEditText, mailEditText, passwordEditText, passwordConfirmationEditText;
    TextView errorTextView, stepTextView;
    String firstNameTextString, lastNameString, categoryString, dateBirthString, addressString, cityString, postalCodeString, countryString, sexString, mailString, passwordString, phoneString;

    //REGISTER PAGE 1 - 2 INFORMATIONS RECUPERATIONS
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



    public RegisterP3() {
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
            mailString = getArguments().getString(ARG_MAIL);
            passwordString = getArguments().getString(ARG_PASSWORD);
            phoneString = getArguments().getString(ARG_PHONE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.b_fragment_register_p3, container, false);

        passwordEditText = v.findViewById(R.id.password);
        passwordConfirmationEditText = v.findViewById(R.id.password_confirmation);
        stepTextView = v.findViewById(R.id.step);
        errorTextView = v.findViewById(R.id.error);
        phoneEditText = v.findViewById(R.id.phone);
        mailEditText = v.findViewById(R.id.mail);
        validationButton = v.findViewById(R.id.validation);
        connexionButton = v.findViewById(R.id.connexion);
        backImageButton = v.findViewById(R.id.back);

        validationButton.setOnClickListener(click_event);
        connexionButton.setOnClickListener(click_event);
        backImageButton.setOnClickListener(click_event);


        if(getArguments() != null && mailString != null && passwordString != null && phoneString != null)
        {
            phoneEditText.setText(phoneString);
            mailEditText.setText(mailString);
            passwordEditText.setText(passwordString);
            passwordConfirmationEditText.setText(passwordString);
        }
        if(categoryString.equals(getResources().getResourceName(R.string.parents)))
        {
            stepTextView.setText("Etape 3/3");
        }

        else if (categoryString.equals(getResources().getResourceName(R.string.nurse)))
        {
            stepTextView.setText("Etape 3/4");
        }
        return v;
    }

    private View.OnClickListener click_event = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.validation:
                    mailString = mailEditText.getText().toString();
                    passwordString = passwordEditText.getText().toString();
                    phoneString = phoneEditText.getText().toString();

                    if(mailString.isEmpty() || passwordString.isEmpty() || passwordConfirmationEditText.getText().toString().isEmpty() || phoneEditText.getText().toString().isEmpty())
                    {
                        errorTextView.setText("Veuillez remplir les champs correctements");
                    }
                    else if ( ! passwordString.equals(passwordEditText.getText().toString()))
                    {
                        errorTextView.setText("LEs mots de passe ne sont pas les mêmes");
                    }
                    else
                    {
                        Bundle args = new Bundle();
                        args.putString(ARG_FIRST_NAME, firstNameTextString);
                        args.putString(ARG_LAST_NAME, lastNameString);
                        args.putString(ARG_CATEGORY, categoryString);
                        args.putString(ARG_DATE_BIRTH, dateBirthString);
                        args.putString(ARG_ADDRESS, addressString);
                        args.putString(ARG_SEX, sexString);
                        args.putString(ARG_CITY, cityString);
                        args.putString(ARG_POSTAL_CODE,postalCodeString);
                        args.putString(ARG_COUNTRY, countryString);
                        args.putString(ARG_PASSWORD, passwordString);
                        args.putString(ARG_MAIL, mailString);
                        args.putString(ARG_PHONE, phoneString);

                        if(categoryString.equals(getResources().getString(R.string.nurse)))
                        {
                            Navigation.findNavController(view).navigate(R.id.action_registerP3_to_registerP4Nurse, args);
                        }

                        else if (categoryString.equals(getResources().getString(R.string.parents)))
                        {
                            Parents new_parents = new Parents(firstNameTextString, lastNameString, dateBirthString, cityString, countryString, addressString, postalCodeString, mailString, passwordString, phoneString, sexString);
                            ParentsDAO parentsDAO = new ParentsDAO(getContext());
                            parentsDAO.add(new_parents);
                            Toast.makeText(getContext(), "Inscription reussi, veuillez maintenant vous connecter", Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(view).navigate(R.id.action_registerP3_to_login);
                        }
                    }
                    break;

                case R.id.back:
                    Bundle args_back = new Bundle();
                    args_back.putString(ARG_FIRST_NAME, firstNameTextString);
                    args_back.putString(ARG_LAST_NAME, lastNameString);
                    args_back.putString(ARG_CATEGORY, categoryString);
                    args_back.putString(ARG_DATE_BIRTH, dateBirthString);
                    args_back.putString(ARG_ADDRESS, addressString);
                    args_back.putString(ARG_CITY, cityString);
                    args_back.putString(ARG_POSTAL_CODE, postalCodeString);
                    args_back.putString(ARG_COUNTRY, countryString);
                    args_back.putString(ARG_SEX, sexString);
                    Navigation.findNavController(view).navigate(R.id.action_registerP3_to_registerP2,args_back);
                    break;

                case R.id.connexion:
                    Navigation.findNavController(view).navigate(R.id.action_registerP3_to_login);
                    break;
            }

        }
    };
}