package com.example.ouestnounou.VISUAL_ACTIVITY_1_CONNECTION;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ouestnounou.DAO.ChildrenDAO;
import com.example.ouestnounou.DAO.ParentsDAO;
import com.example.ouestnounou.MODEL.Children;
import com.example.ouestnounou.MODEL.Parents;
import com.example.ouestnounou.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddChildren#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddChildren extends Fragment {

    Button validation;
    EditText first_name, last_name;
    TextView error, step;
    RadioGroup sex;
    RadioButton boy, girl;
    DatePicker birth;

    String first_name_text, last_name_text, birth_text, sex_text;

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
            first_name_text = getArguments().getString(ARG_FIRST_NAME);
            last_name_text = getArguments().getString(ARG_LAST_NAME);
            birth_text = getArguments().getString(ARG_DATE_BIRTH);
            sex_text = getArguments().getString(ARG_SEX);
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

        if(getArguments() != null)
        {
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
                        error.setText("Veuillez remplir les champs correctement");
                    }
                    else
                    {
                        args.putString(ARG_FIRST_NAME, last_name_text);
                        args.putString(ARG_LAST_NAME, last_name.getText().toString());
                        args.putString(ARG_DATE_BIRTH, birth_text);
                        args.putString(ARG_SEX, sex_text);

                        Children new_children = new Children(first_name_text, last_name_text, birth_text, sex_text);
                        ChildrenDAO childrenDAO = new ChildrenDAO(getContext());
                        childrenDAO.add(new_children);

                        Navigation.findNavController(view).navigate(R.id.action_addChildren_to_addSchool);
                    //ENREGISTER ENFANT BDD
                    //ALLER SUR LA PAGE ECOLE

                    //AUTRE :
                    //AJOUTER SCOLARISE ET LE RELIER A CETTE PAGE
                    //PENSER A RELIER LE DATE BIRTH ET SEX DE L'enfant voir la page REGISTER 1 si tu veux t'aider facilement
                    //EN CAS DE PROB DEMANDE A BILLY
                    }
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