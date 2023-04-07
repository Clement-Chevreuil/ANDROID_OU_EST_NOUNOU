package com.example.ouestnounou.VISUAL_ACTIVITY_2;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ouestnounou.DAO.ChildrenDAO;
import com.example.ouestnounou.MODEL.Children;
import com.example.ouestnounou.MODEL.Parents;
import com.example.ouestnounou.R;

public class AddChildren extends Fragment {

    Button validation;
    EditText first_name, last_name;
    TextView error;
    RadioButton boy, girl;
    DatePicker birth;
    String firstNameString, lastNameString, birthString, sexString;

    private static final String ARG_FIRST_NAME = "first_name";
    private static final String ARG_LAST_NAME = "last_name";
    private static final String ARG_DATE_BIRTH = "date_birth";
    private static final String ARG_SEX = "sex";

    public AddChildren() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            firstNameString = getArguments().getString(ARG_FIRST_NAME);
            lastNameString = getArguments().getString(ARG_LAST_NAME);
            birthString = getArguments().getString(ARG_DATE_BIRTH);
            sexString = getArguments().getString(ARG_SEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.c_fragment_add_children, container, false);

        first_name = v.findViewById(R.id.first_name);
        last_name = v.findViewById(R.id.last_name);

        validation = v.findViewById(R.id.validation);
        validation.setOnClickListener(click_event);

        girl = v.findViewById(R.id.girl);
        girl.setOnClickListener(click_event);

        boy = v.findViewById(R.id.boy);
        boy.setOnClickListener(click_event);

        birth = v.findViewById(R.id.birth);

        error = v.findViewById(R.id.error);

        if(getArguments() != null)
        {
            if(sexString.equals(getResources().getString(R.string.boy)))
            {
                boy.setChecked(true);
            }
            else if (sexString.equals(getResources().getString(R.string.girl)))
            {
                girl.setChecked(true);
            }

            first_name.setText(firstNameString);
            last_name.setText(lastNameString);

            String[] date_array = birthString.split("/");
            birth.updateDate(Integer.valueOf(date_array[2]), Integer.valueOf(date_array[1]), Integer.valueOf(date_array[0]));
        }
        else
        {
            //SEX BASE
            if(boy.isChecked()){
                sexString = boy.getText().toString();
            }
            else if(girl.isChecked()){
                sexString = girl.getText().toString();
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

                    firstNameString = first_name.getText().toString();
                    lastNameString = last_name.getText().toString();
                    birthString = String.valueOf(birth.getDayOfMonth()) + "/" + String.valueOf(birth.getMonth()) + "/" + String.valueOf(birth.getYear());


                    if(firstNameString.isEmpty() || lastNameString.isEmpty())
                    {
                        error.setText("Veuillez remplir les champs correctement");
                    }
                    else
                    {
                        args.putString(ARG_FIRST_NAME, firstNameString);
                        args.putString(ARG_LAST_NAME, lastNameString);
                        args.putString(ARG_DATE_BIRTH, birthString);
                        args.putString(ARG_SEX, sexString);

                        SharedPreferences prefs = getContext().getSharedPreferences("session", MODE_PRIVATE);
                        int id_parents = prefs.getInt("id", 0);

                        com.example.ouestnounou.MODEL.Parents parents_children = new Parents();
                        parents_children.setId(id_parents);

                        Children new_children = new Children(firstNameString, lastNameString, birthString, sexString, parents_children);

                        ChildrenDAO childrenDAO = new ChildrenDAO(getContext());
                        childrenDAO.add(new_children);

                        Navigation.findNavController(view).navigate(R.id.action_addChildren_to_children);
                    }
                    break;
                case R.id.boy:
                    sexString = boy.getText().toString();
                    break;
                case R.id.girl:
                    sexString = girl.getText().toString();
                    break;
            }

        }
    };
}