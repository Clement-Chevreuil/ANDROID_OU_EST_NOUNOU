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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ouestnounou.R;

public class RegisterP4Parents extends Fragment {

    FrameLayout frame;
    Button validation, connexion, add_children;
    TextView error, step;
    ImageView logo;

    String first_name_text, last_name_text, category_text, date_birth_text, adress_text, city_text, postal_code_text, country_text, mail_text, password_text;

    //REGISTER PAGE 1 - 2 - 3 INFORMATIONS RECUPERATIONS
    private static final String ARG_CATEGORY = "category";
    private static final String ARG_FIRST_NAME = "first_name";
    private static final String ARG_LAST_NAME = "last_name";
    private static final String ARG_DATE_BIRTH = "date_birth";
    private static final String ARG_SEX = "sex";
    private static final String ARG_ADRESS = "adress";
    private static final String ARG_CITY = "city";
    private static final String ARG_POSTAL_CODE = "postal_code";
    private static final String ARG_COUNTRY = "country";
    private static final String ARG_MAIL = "mail";
    private static final String ARG_PASSWORD = "password";

    public RegisterP4Parents() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            first_name_text = getArguments().getString(ARG_FIRST_NAME);
            last_name_text = getArguments().getString(ARG_LAST_NAME);
            date_birth_text = getArguments().getString(ARG_DATE_BIRTH);
            category_text = getArguments().getString(ARG_CATEGORY);
            city_text = getArguments().getString(ARG_CITY);
            country_text = getArguments().getString(ARG_COUNTRY);
            adress_text = getArguments().getString(ARG_ADRESS);
            postal_code_text = getArguments().getString(ARG_POSTAL_CODE);
            mail_text = getArguments().getString(ARG_MAIL);
            password_text = getArguments().getString(ARG_PASSWORD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.b_fragment_register_p4_parents, container, false);

        validation = v.findViewById(R.id.validation);
        validation.setOnClickListener(click_event);

        connexion = v.findViewById(R.id.connexion);
        connexion.setOnClickListener(click_event);

        return v;
    }

    private View.OnClickListener click_event = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.validation:
                    break;

                case R.id.connexion:
                    Navigation.findNavController(view).navigate(R.id.action_registerP4Parents_to_login);
                    break;
            }

        }
    };
}