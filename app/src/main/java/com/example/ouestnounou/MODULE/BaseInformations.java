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

    Button validationButton, logOutButton;
    EditText firstNameEditText, lastNameEditText;
    TextView errorTextView;
    RadioButton boyRadioButton, girlRadioButton;
    DatePicker birthDatePicker;
    String firstNameString, lastNameString, birthString, sexString, categoryString;
    int idCategoryInt;
    ParentsDAO parentsDAO;
    NurseDAO nurseDAO;
    Parents parents;
    Nurse nurse;
    SharedPreferences prefs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.z_fragment_base_informations, container, false);

        prefs = getContext().getSharedPreferences("session", MODE_PRIVATE);
        idCategoryInt = prefs.getInt("id", -1);
        categoryString = prefs.getString("categoryString", "");

        validationButton = v.findViewById(R.id.validation);
        logOutButton = v.findViewById(R.id.log_out);
        girlRadioButton = v.findViewById(R.id.girl);
        boyRadioButton = v.findViewById(R.id.boy);
        firstNameEditText = v.findViewById(R.id.first_name);
        lastNameEditText = v.findViewById(R.id.last_name);
        errorTextView = v.findViewById(R.id.error);
        birthDatePicker = v.findViewById(R.id.birth);

        validationButton.setOnClickListener(click_event);
        boyRadioButton.setOnClickListener(click_event);
        girlRadioButton.setOnClickListener(click_event);
        logOutButton.setOnClickListener(click_event);

        if(categoryString.equals(getResources().getString(R.string.nurse))){
            nurseDAO = new NurseDAO(getContext());
            nurse = nurseDAO.getNurseById(idCategoryInt);
            firstNameEditText.setText(nurse.getFirstName());
            lastNameEditText.setText(nurse.getLastName());
            if(nurse.getSex().equals(getResources().getString(R.string.boy)))
            {
                boyRadioButton.setChecked(true);
            }
            else if (nurse.getSex().equals(getResources().getString(R.string.girl)))
            {
                girlRadioButton.setChecked(true);
            }
            String[] date_array = nurse.getBirth().split("/");
            birthDatePicker.updateDate(Integer.valueOf(date_array[2]), Integer.valueOf(date_array[1]), Integer.valueOf(date_array[0]));

        }
        else{
            parentsDAO = new ParentsDAO(getContext());
            parents = parentsDAO.getParent(idCategoryInt);
            firstNameEditText.setText(parents.getFirstName());
            lastNameEditText.setText(parents.getLastName());
            if(parents.getSex().equals(getResources().getString(R.string.boy)))
            {
                boyRadioButton.setChecked(true);
            }
            else if (parents.getSex().equals(getResources().getString(R.string.girl)))
            {
                girlRadioButton.setChecked(true);
            }
            String[] date_array = parents.getBirth().split("/");
            birthDatePicker.updateDate(Integer.valueOf(date_array[2]), Integer.valueOf(date_array[1]), Integer.valueOf(date_array[0]));

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

                    firstNameString = firstNameEditText.getText().toString();
                    lastNameString = lastNameEditText.getText().toString();
                    birthString = String.valueOf(birthDatePicker.getDayOfMonth()) + "/" + String.valueOf(birthDatePicker.getMonth()) + "/" + String.valueOf(birthDatePicker.getYear());

                    if(firstNameString.isEmpty() || lastNameString.isEmpty())
                    {
                        errorTextView.setText("Vueillez remplir les champs correctement");
                    }
                    else
                    {
                        if(categoryString.equals(getResources().getString(R.string.nurse))){
                            nurse.setFirstName(firstNameString);
                            nurse.setLastName(lastNameString);
                            nurse.setBirth(birthString);
                            nurseDAO.update(nurse);
                            Toast.makeText(getContext(), "Vos informations ont bien été mise à jour!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            parents.setFirstName(firstNameString);
                            parents.setLastName(lastNameString);
                            parents.setBirth(birthString);
                            parentsDAO.update(parents);
                            Toast.makeText(getContext(), "Vos informations ont bien été mise à jour!", Toast.LENGTH_SHORT).show();
                        }

                    }
                    break;

                case R.id.boy:
                    sexString = boyRadioButton.getText().toString();
                    break;
                case R.id.girl:
                    sexString = girlRadioButton.getText().toString();
                    break;
                case R.id.log_out:
                    prefs = null;
                    getActivity().finish();
                    break;
            }
        }
    };

}