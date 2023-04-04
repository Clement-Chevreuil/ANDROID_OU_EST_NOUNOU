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
    Nurse nurse;
    NurseDAO nurseDAO;
    Integer num_children_int, age_min_int, age_max_int;
    EditText num_children, age_min, age_max;
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

        num_children = v.findViewById(R.id.num_children);
        age_max = v.findViewById(R.id.age_max);
        age_min = v.findViewById(R.id.age_min);
        error = v.findViewById(R.id.error);
        validation = v.findViewById(R.id.validation);
        validation.setOnClickListener(click_event);

        SharedPreferences prefs = getContext().getSharedPreferences("session", MODE_PRIVATE);
        int id_parents = prefs.getInt("id", -1);

        nurseDAO = new NurseDAO(getContext());
        nurse = nurseDAO.getNurseById(id_parents);

        age_min.setText(String.valueOf(nurse.getAge_min()));
        age_max.setText(String.valueOf(nurse.getAge_max()));
        num_children.setText(String.valueOf(nurse.getNb_children()));

        return v;
    }

    private View.OnClickListener click_event = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.validation:

                    SharedPreferences prefs = getContext().getSharedPreferences("session", MODE_PRIVATE);
                    String category = prefs.getString("category", "");
                    int id_category = prefs.getInt("id", 0);

                    ChildrenDAO childrenDAO = new ChildrenDAO(getContext());
                    ArrayList<Children> listChildren = childrenDAO.getChildrensByNurseId(id_category);




                    num_children_int = Integer.valueOf(num_children.getText().toString());
                    age_min_int = Integer.valueOf(age_min.getText().toString());
                    age_max_int = Integer.valueOf(age_max.getText().toString());

                    Log.e("test", String.valueOf(listChildren.size()));


                    if(num_children_int == null || age_max_int == null || age_min_int == null)
                    {
                        error.setText("Veuillez remplir les champs correctements");
                    }
                    else
                    {
                        if(listChildren != null && listChildren.size() > num_children_int)
                        {
                            error.setText("Le nombre est inférieur au enfant que vous avez actuellement (" + listChildren.size() + ")" );
                        }

                        else{
                            nurse.setNb_children(num_children_int);
                            nurse.setAge_min(age_min_int);
                            nurse.setAge_max(age_max_int);
                            nurseDAO.update(nurse);
                            Toast.makeText(getContext(), "Vos informations ont bien été mise à jour!", Toast.LENGTH_SHORT).show();
                        }
                        //Navigation.findNavController(view).navigate(R.id.action_registerP3_to_registerP4Parents);
                    }

                    break;

            }

        }
    };
}