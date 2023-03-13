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

    Button validation, connexion;
    ImageButton back;
    EditText adress, city, postal_code, country;
    TextView error, step;
    ImageView logo;

    String first_name_text, last_name_text, category_text, date_birth_text, adress_text, city_text, postal_code_text, country_text, sex_text;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    //REGISTER PAGE 1 INFORMATIONS RECUPERATIONS
    private static final String ARG_CATEGORY = "category";
    private static final String ARG_FIRST_NAME = "first_name";
    private static final String ARG_LAST_NAME = "last_name";
    private static final String ARG_DATE_BIRTH = "date_birth";
    private static final String ARG_SEX = "sex";

    //REGISTER PAGE 2 INFORMATIONS
    private static final String ARG_ADRESS = "adress";
    private static final String ARG_CITY = "city";
    private static final String ARG_POSTAL_CODE = "postal_code";
    private static final String ARG_COUNTRY = "country";

    public RegisterP2() {
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
            sex_text = getArguments().getString(ARG_SEX);

            city_text = getArguments().getString(ARG_CITY);
            country_text = getArguments().getString(ARG_COUNTRY);
            adress_text = getArguments().getString(ARG_ADRESS);
            postal_code_text = getArguments().getString(ARG_POSTAL_CODE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.b_fragment_register_p2, container, false);

        validation = v.findViewById(R.id.validation);
        validation.setOnClickListener(click_event);

        connexion = v.findViewById(R.id.connexion);
        connexion.setOnClickListener(click_event);

        back = v.findViewById(R.id.back);
        back.setOnClickListener(click_event);

        step = v.findViewById(R.id.step);

        city = v.findViewById(R.id.city);
        country = v.findViewById(R.id.country);
        adress = v.findViewById(R.id.address);
        postal_code = v.findViewById(R.id.postal_code);
        error = v.findViewById(R.id.error);

        if(category_text.equals(getResources().getString(R.string.parents)))
        {
            step.setText("Etape 2/3");
        }
        else if(category_text.equals(getResources().getString(R.string.nurse)))
        {
            step.setText("Etape 2/4");
        }

        if(getArguments() != null && adress_text != null && city_text != null && postal_code_text != null && country_text != null)
        {
            adress.setText(adress_text);
            city.setText(city_text);
            postal_code.setText(postal_code_text);
            country.setText(country_text);
        }

        return v;
    }

    private View.OnClickListener click_event = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.validation:

                    adress_text = adress.getText().toString() ;
                    city_text = city.getText().toString();
                    postal_code_text =  postal_code.getText().toString();
                    country_text = country.getText().toString();

                    if(adress_text.isEmpty() || city_text.isEmpty() || postal_code_text.isEmpty() || country_text.isEmpty())
                    {
                        error.setText("Veuillez remplir les champs correctement");
                    }
                    else
                    {
                        Bundle args = new Bundle();
                        args.putString(ARG_FIRST_NAME, first_name_text);
                        args.putString(ARG_LAST_NAME, last_name_text);
                        args.putString(ARG_CATEGORY, category_text);
                        args.putString(ARG_DATE_BIRTH, date_birth_text);
                        args.putString(ARG_SEX, sex_text);
                        args.putString(ARG_ADRESS, adress_text);
                        args.putString(ARG_CITY, city_text);
                        args.putString(ARG_POSTAL_CODE,postal_code_text);
                        args.putString(ARG_COUNTRY, country_text);
                        Navigation.findNavController(view).navigate(R.id.action_registerP2_to_registerP3, args);
                    }

                    break;

                case R.id.back:
                    Bundle args_back = new Bundle();
                    args_back.putString(ARG_FIRST_NAME, first_name_text);
                    args_back.putString(ARG_LAST_NAME, last_name_text);
                    args_back.putString(ARG_CATEGORY, category_text);
                    args_back.putString(ARG_DATE_BIRTH, date_birth_text);
                    args_back.putString(ARG_SEX, sex_text);
                    Navigation.findNavController(view).navigate(R.id.action_registerP2_to_registerP1, args_back);
                    break;

                case R.id.connexion:
                    Navigation.findNavController(view).navigate(R.id.action_registerP2_to_login);
                    break;
            }

        }
    };

}