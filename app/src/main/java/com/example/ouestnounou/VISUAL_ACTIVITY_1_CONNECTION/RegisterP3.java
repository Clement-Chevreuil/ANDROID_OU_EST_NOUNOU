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

    Button validation, connexion;
    ImageButton back;
    EditText mail, password, password_confirmation, phone;
    TextView error, step;
    ImageView logo;
    String first_name_text, last_name_text, category_text, date_birth_text, adress_text, city_text, postal_code_text, country_text, mail_text, password_text, phone_text, sex_text;

    //REGISTER PAGE 1 - 2 INFORMATIONS RECUPERATIONS
    private static final String ARG_CATEGORY = "category";
    private static final String ARG_FIRST_NAME = "first_name";
    private static final String ARG_LAST_NAME = "last_name";
    private static final String ARG_DATE_BIRTH = "date_birth";
    private static final String ARG_SEX = "sex";
    private static final String ARG_ADRESS = "adress";
    private static final String ARG_CITY = "city";
    private static final String ARG_POSTAL_CODE = "postal_code";
    private static final String ARG_COUNTRY = "country";

    //REGISTER PAGE 3 INFORMATIONS

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
            first_name_text = getArguments().getString(ARG_FIRST_NAME);
            last_name_text = getArguments().getString(ARG_LAST_NAME);
            date_birth_text = getArguments().getString(ARG_DATE_BIRTH);
            category_text = getArguments().getString(ARG_CATEGORY);
            sex_text = getArguments().getString(ARG_SEX);

            city_text = getArguments().getString(ARG_CITY);
            country_text = getArguments().getString(ARG_COUNTRY);
            adress_text = getArguments().getString(ARG_ADRESS);
            postal_code_text = getArguments().getString(ARG_POSTAL_CODE);
            mail_text = getArguments().getString(ARG_MAIL);
            password_text = getArguments().getString(ARG_PASSWORD);
            phone_text = getArguments().getString(ARG_PHONE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.b_fragment_register_p3, container, false);

        validation = v.findViewById(R.id.validation);
        validation.setOnClickListener(click_event);

        connexion = v.findViewById(R.id.connexion);
        connexion.setOnClickListener(click_event);

        back = v.findViewById(R.id.back);
        back.setOnClickListener(click_event);

        phone = v.findViewById(R.id.phone);

        mail = v.findViewById(R.id.mail);
        password = v.findViewById(R.id.password);
        password_confirmation = v.findViewById(R.id.password_confirmation);

        step = v.findViewById(R.id.step);
        error = v.findViewById(R.id.error);

        if(getArguments() != null && mail_text != null && password_text != null && phone != null)
        {
            phone.setText(phone_text);
            mail.setText(mail_text);
            password.setText(password_text);
            password_confirmation.setText(password_text);
        }

        if(category_text.equals(getResources().getResourceName(R.string.parents)))
        {
            step.setText("Etape 3/3");
        }

        else if (category_text.equals(getResources().getResourceName(R.string.nurse)))
        {
            step.setText("Etape 3/4");
        }

        return v;
    }

    private View.OnClickListener click_event = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.validation:


                    mail_text = mail.getText().toString();
                    password_text = password.getText().toString();
                    phone_text = phone.getText().toString();

                    if(mail_text.isEmpty() || password_text.isEmpty() || password_confirmation.getText().toString().isEmpty() || phone_text.isEmpty())
                    {
                        error.setText("Veuillez remplir les champs correctements");
                    }
                    else if ( ! password_text.equals(password_confirmation.getText().toString()))
                    {
                        error.setText("LEs mots de passe ne sont pas les mêmes");
                    }
                    else
                    {
                        Bundle args = new Bundle();
                        args.putString(ARG_FIRST_NAME, first_name_text);
                        args.putString(ARG_LAST_NAME, last_name_text);
                        args.putString(ARG_CATEGORY, category_text);
                        args.putString(ARG_DATE_BIRTH, date_birth_text);
                        args.putString(ARG_ADRESS, adress_text);
                        args.putString(ARG_SEX, sex_text);
                        args.putString(ARG_CITY, city_text);
                        args.putString(ARG_POSTAL_CODE,postal_code_text);
                        args.putString(ARG_COUNTRY, country_text);
                        args.putString(ARG_PASSWORD, password_text);
                        args.putString(ARG_MAIL, mail_text);
                        args.putString(ARG_PHONE, ARG_PHONE);

                        if(category_text.equals(getResources().getString(R.string.nurse)))
                        {
                            Navigation.findNavController(view).navigate(R.id.action_registerP3_to_registerP4Nurse, args);
                        }

                        else if (category_text.equals(getResources().getString(R.string.parents)))
                        {

                            Parents new_parents = new Parents(first_name_text, last_name_text, date_birth_text, city_text, country_text, adress_text, postal_code_text, mail_text, password_text, phone_text, sex_text);
                            ParentsDAO parentsDAO = new ParentsDAO(getContext());
                            parentsDAO.add(new_parents);
                            Toast.makeText(getContext(), "Inscription reussi, veuillez maintenant vous connecter", Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(view).navigate(R.id.action_registerP3_to_login);


                        }
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
                    args_back.putString(ARG_SEX, sex_text);
                    Navigation.findNavController(view).navigate(R.id.action_registerP3_to_registerP2,args_back);
                    break;

                case R.id.connexion:
                    Navigation.findNavController(view).navigate(R.id.action_registerP3_to_login);
                    break;
            }

        }
    };
}