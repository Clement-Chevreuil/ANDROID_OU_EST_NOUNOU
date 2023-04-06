package com.example.ouestnounou.MODULE;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ouestnounou.DAO.ChildrenDAO;
import com.example.ouestnounou.DAO.NurseDAO;
import com.example.ouestnounou.DAO.ParentsDAO;
import com.example.ouestnounou.MODEL.Children;
import com.example.ouestnounou.MODEL.Nurse;
import com.example.ouestnounou.MODEL.Parents;
import com.example.ouestnounou.R;

import java.util.ArrayList;

public class NurseInformations extends Fragment {

    Button validation;
    TextView error;
    NurseDAO nurseDAO;
    Nurse nurse;
    Integer numChildrenInteger, ageMinInteger, ageMaxInteger;
    EditText numChildrenEditText, ageMinEditText, ageMaxEditText;
    SharedPreferences prefs;
    String categoryString;
    int idCategoryInt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.z_fragment_nurse_informations, container, false);

        numChildrenEditText = v.findViewById(R.id.num_children);
        ageMaxEditText = v.findViewById(R.id.age_max);
        ageMinEditText = v.findViewById(R.id.age_min);
        error = v.findViewById(R.id.error);
        validation = v.findViewById(R.id.validation);
        validation.setOnClickListener(click_event);

        prefs = getContext().getSharedPreferences("session", MODE_PRIVATE);
        idCategoryInt = prefs.getInt("id", -1);
        categoryString = prefs.getString("category", "");

        nurseDAO = new NurseDAO(getContext());
        nurse = nurseDAO.getNurseById(idCategoryInt);

        ageMinEditText.setText(String.valueOf(nurse.getAgeMin()));
        ageMaxEditText.setText(String.valueOf(nurse.getAgeMax()));
        numChildrenEditText.setText(String.valueOf(nurse.getNbChildren()));

        return v;
    }

    private View.OnClickListener click_event = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.validation:

                    ChildrenDAO childrenDAO = new ChildrenDAO(getContext());
                    ArrayList<Children> listChildren = childrenDAO.getChildrensByNurseId(idCategoryInt);

                    numChildrenInteger = Integer.valueOf(numChildrenEditText.getText().toString());
                    ageMinInteger = Integer.valueOf(ageMinEditText.getText().toString());
                    ageMaxInteger = Integer.valueOf(ageMaxEditText.getText().toString());

                    if(numChildrenInteger == null || ageMaxInteger == null || ageMinInteger == null)
                    {
                        error.setText("Veuillez remplir les champs correctements");
                    }
                    else
                    {
                        if(listChildren != null && listChildren.size() > numChildrenInteger)
                        {
                            error.setText("Le nombre est inférieur au enfant que vous avez actuellement (" + listChildren.size() + ")" );
                        }
                        else{
                            nurse.setNbChildren(numChildrenInteger);
                            nurse.setAgeMin(ageMinInteger);
                            nurse.setAgeMax(ageMaxInteger);
                            nurseDAO.update(nurse);
                            Toast.makeText(getContext(), "Vos informations ont bien été mise à jour!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
            }

        }
    };
}