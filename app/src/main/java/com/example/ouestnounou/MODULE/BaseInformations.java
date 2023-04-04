package com.example.ouestnounou.MODULE;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.ouestnounou.DAO.NurseDAO;
import com.example.ouestnounou.DAO.ParentsDAO;
import com.example.ouestnounou.MODEL.Nurse;
import com.example.ouestnounou.MODEL.Parents;
import com.example.ouestnounou.R;

import java.util.Date;


public class BaseInformations extends Fragment {

    Button validation, log_out;
    EditText first_name, last_name;
    TextView error;
    RadioButton boy, girl;
    DatePicker birth;
    String first_name_text, last_name_text, birth_text, sex_text;
    Parents parents;
    Nurse nurse;
    ParentsDAO parentsDAO;
    NurseDAO nurseDAO;
    String category;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.z_fragment_base_informations, container, false);

        SharedPreferences prefs = getContext().getSharedPreferences("session", MODE_PRIVATE);
        int id_parents = prefs.getInt("id", -1);
        category = prefs.getString("category", "");



        validation = v.findViewById(R.id.validation);
        validation.setOnClickListener(click_event);

        log_out = v.findViewById(R.id.log_out);
        log_out.setOnClickListener(click_event);

        girl = v.findViewById(R.id.girl);
        girl.setOnClickListener(click_event);

        boy = v.findViewById(R.id.boy);
        boy.setOnClickListener(click_event);

        first_name = v.findViewById(R.id.first_name);
        last_name = v.findViewById(R.id.last_name);
        error = v.findViewById(R.id.error);
        birth = v.findViewById(R.id.birth);



        if(category.equals(getResources().getString(R.string.nurse))){
            nurseDAO = new NurseDAO(getContext());
            nurse = nurseDAO.getNurseById(id_parents);
            first_name.setText(nurse.getFist_name());
            last_name.setText(nurse.getLast_name());
            if(nurse.getSex().equals(getResources().getString(R.string.boy)))
            {
                boy.setChecked(true);
            }
            else if (nurse.getSex().equals(getResources().getString(R.string.girl)))
            {
                girl.setChecked(true);
            }
            String[] date_array = nurse.getBirth().split("/");
            birth.updateDate(Integer.valueOf(date_array[2]), Integer.valueOf(date_array[1]), Integer.valueOf(date_array[0]));

        }
        else{
            parentsDAO = new ParentsDAO(getContext());
            parents = parentsDAO.getParent(id_parents);
            first_name.setText(parents.getFist_name());
            last_name.setText(parents.getLast_name());
            if(parents.getSex().equals(getResources().getString(R.string.boy)))
            {
                boy.setChecked(true);
            }
            else if (parents.getSex().equals(getResources().getString(R.string.girl)))
            {
                girl.setChecked(true);
            }
            String[] date_array = parents.getBirth().split("/");
            birth.updateDate(Integer.valueOf(date_array[2]), Integer.valueOf(date_array[1]), Integer.valueOf(date_array[0]));

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
                        if(category.equals(getResources().getString(R.string.nurse))){
                            nurse.setFist_name(first_name_text);
                            nurse.setLast_name(last_name_text);
                            nurse.setBirth(birth_text);
                            nurseDAO.update(nurse);
                            Toast.makeText(getContext(), "Vos informations ont bien été mise à jour!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            parents.setFist_name(first_name_text);
                            parents.setLast_name(last_name_text);
                            parents.setBirth(birth_text);
                            parentsDAO.update(parents);
                            Toast.makeText(getContext(), "Vos informations ont bien été mise à jour!", Toast.LENGTH_SHORT).show();
                        }

                    }
                    break;

                case R.id.boy:
                    sex_text = boy.getText().toString();
                    break;
                case R.id.girl:
                    sex_text = girl.getText().toString();
                    break;
                case R.id.log_out:
                    SharedPreferences prefs = getContext().getSharedPreferences("session", MODE_PRIVATE);
                    prefs = null;
                    getActivity().finish();
                    break;
            }
        }
    };

}