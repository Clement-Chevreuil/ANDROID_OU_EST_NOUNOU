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
import android.widget.Toast;

import com.example.ouestnounou.DAO.NurseDAO;
import com.example.ouestnounou.MODEL.Nurse;
import com.example.ouestnounou.R;

public class RegisterP4Nurse extends Fragment {

    Button validation, connexion, back;
    EditText num_children, age_min, age_max;
    TextView error, step;
    ImageView logo;
    Integer num_children_int, age_min_int, age_max_int;
    String first_name_text, last_name_text, category_text, date_birth_text, adress_text, city_text, postal_code_text, country_text, mail_text, password_text, phone_text, sex_text;

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
    private static final String ARG_PHONE = "phone";


    public RegisterP4Nurse() {
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
            sex_text = getArguments().getString(ARG_SEX);

            country_text = getArguments().getString(ARG_COUNTRY);
            adress_text = getArguments().getString(ARG_ADRESS);
            postal_code_text = getArguments().getString(ARG_POSTAL_CODE);
            mail_text = getArguments().getString(ARG_MAIL);
            password_text = getArguments().getString(ARG_PASSWORD);
            phone_text = getArguments().getString(ARG_PHONE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.b_fragment_register_p4_nurse, container, false);

        num_children = v.findViewById(R.id.num_children);
        age_max = v.findViewById(R.id.age_max);
        age_min = v.findViewById(R.id.age_min);
        error = v.findViewById(R.id.error);
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

                    num_children_int = Integer.valueOf(num_children.getText().toString());
                    age_min_int = Integer.valueOf(age_min.getText().toString());
                    age_max_int = Integer.valueOf(age_max.getText().toString());

                    if(num_children_int == null || age_max_int == null || age_min_int == null)
                    {
                        error.setText("Veuillez remplir les champs correctements");
                    }
                    else
                    {
                        Nurse new_nurse = new Nurse(first_name_text, last_name_text, sex_text, date_birth_text, city_text, country_text, phone_text, adress_text, postal_code_text, mail_text, password_text, age_min_int, age_max_int, num_children_int);
                        NurseDAO nurseDAO = new NurseDAO(getContext());
                        nurseDAO.add(new_nurse);
                        Navigation.findNavController(view).navigate(R.id.action_registerP4Nurse_to_login);
                    }

                    break;

                case R.id.back:
                    Bundle args_back = new Bundle();
                    args_back.putString(ARG_FIRST_NAME, first_name_text);
                    args_back.putString(ARG_LAST_NAME, last_name_text);
                    args_back.putString(ARG_CATEGORY, category_text);
                    args_back.putString(ARG_DATE_BIRTH, date_birth_text);
                    args_back.putString(ARG_ADRESS, adress_text);
                    args_back.putString(ARG_CITY, city_text);
                    args_back.putString(ARG_POSTAL_CODE, postal_code_text);
                    args_back.putString(ARG_COUNTRY, country_text);
                    args_back.putString(ARG_MAIL, mail_text);
                    args_back.putString(ARG_PASSWORD, password_text);
                    args_back.putString(ARG_PHONE, phone_text);
                    args_back.putString(ARG_SEX, sex_text);
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