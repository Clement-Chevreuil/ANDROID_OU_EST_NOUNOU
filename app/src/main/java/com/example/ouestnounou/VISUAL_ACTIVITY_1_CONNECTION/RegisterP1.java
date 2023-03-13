package com.example.ouestnounou.VISUAL_ACTIVITY_1_CONNECTION;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ouestnounou.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterP1 extends Fragment {

    Button validation, connexion;
    EditText first_name, last_name;
    TextView error, step;
    RadioButton nurse, parents, boy, girl;
    DatePicker birth;
    String first_name_text, last_name_text, category_text, birth_text, sex_text;
    Date birth_date;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_CATEGORY = "category";
    private static final String ARG_FIRST_NAME = "first_name";
    private static final String ARG_LAST_NAME = "last_name";
    private static final String ARG_DATE_BIRTH = "date_birth";
    private static final String ARG_SEX = "sex";

    public RegisterP1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            first_name_text = getArguments().getString(ARG_FIRST_NAME);
            last_name_text = getArguments().getString(ARG_LAST_NAME);
            birth_text = getArguments().getString(ARG_DATE_BIRTH);
            category_text = getArguments().getString(ARG_CATEGORY);
            sex_text = getArguments().getString(ARG_SEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.b_fragment_register_p1, container, false);

        validation = v.findViewById(R.id.validation);
        validation.setOnClickListener(click_event);

        connexion = v.findViewById(R.id.connexion);
        connexion.setOnClickListener(click_event);

        nurse = v.findViewById(R.id.nurse);
        nurse.setOnClickListener(click_event);
        
        parents = v.findViewById(R.id.parents);
        parents.setOnClickListener(click_event);

        girl = v.findViewById(R.id.girl);
        girl.setOnClickListener(click_event);

        boy = v.findViewById(R.id.boy);
        boy.setOnClickListener(click_event);

        first_name = v.findViewById(R.id.first_name);
        last_name = v.findViewById(R.id.last_name);
        error = v.findViewById(R.id.error);
        step = v.findViewById(R.id.step);
        birth = v.findViewById(R.id.birth);



        if(getArguments() != null)
        {
            if(category_text.equals(getResources().getString(R.string.nurse)))
            {
                nurse.setChecked(true);
            }
            else if (category_text.equals(getResources().getString(R.string.parents)))
            {
                parents.setChecked(true);
            }

            if(sex_text.equals(getResources().getString(R.string.boy)))
            {
                boy.setChecked(true);
            }
            else if (sex_text.equals(getResources().getString(R.string.girl)))
            {
                girl.setChecked(true);
            }

            first_name.setText(first_name_text);
            last_name.setText(last_name_text);

            String[] date_array = birth_text.split("/");
            birth.updateDate(Integer.valueOf(date_array[2]), Integer.valueOf(date_array[1]), Integer.valueOf(date_array[0]));
        }
        else
        {
            //CATEGORY BASE
            if(nurse.isChecked()){
                category_text = nurse.getText().toString();
            }
            else if(parents.isChecked()){
                category_text = parents.getText().toString();
            }

            //SEX BASE
            if(boy.isChecked()){
                sex_text = boy.getText().toString();
            }
            else if(girl.isChecked()){
                sex_text = girl.getText().toString();
            }

        }



        return v;
    }

    private View.OnClickListener click_event = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.validation:
                    Bundle args = new Bundle();

                    first_name_text = first_name.getText().toString();
                    last_name_text = last_name.getText().toString();
                    birth_text = String.valueOf(birth.getDayOfMonth()) + "/" + String.valueOf(birth.getMonth()) + "/" + String.valueOf(birth.getYear());

                    if(first_name_text.isEmpty() || last_name_text.isEmpty())
                    {
                        error.setText("Vueillez remplir les champs correctement");
                    }
                    else
                    {
                        args.putString(ARG_CATEGORY, category_text);
                        args.putString(ARG_FIRST_NAME, last_name_text);
                        args.putString(ARG_LAST_NAME, last_name.getText().toString());
                        args.putString(ARG_DATE_BIRTH, birth_text);
                        args.putString(ARG_SEX, sex_text);
                        Navigation.findNavController(view).navigate(R.id.action_registerP1_to_registerP2, args);
                    }
                    break;

                case R.id.connexion:
                    Navigation.findNavController(view).navigate(R.id.action_registerP1_to_login);
                    break;
                case R.id.parents:
                    step.setText("etape 1/3");
                    category_text = parents.getText().toString();
                    break;
                case R.id.nurse:
                    step.setText("etape 1/4");
                    category_text = nurse.getText().toString();
                    break;
                case R.id.boy:
                    sex_text = boy.getText().toString();
                    break;
                case R.id.girl:
                    sex_text = girl.getText().toString();
                    break;
            }

        }
    };

}