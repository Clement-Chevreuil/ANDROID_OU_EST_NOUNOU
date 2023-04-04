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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.ouestnounou.DAO.ParentsDAO;
import com.example.ouestnounou.MODEL.Parents;
import com.example.ouestnounou.R;

import java.util.Date;


public class AdressInformations extends Fragment {

    Button validation;
    EditText adress, city, postal_code, country;
    TextView error;

    String adress_text, city_text, postal_code_text, country_text;

    Parents parents;
    ParentsDAO parentsDAO;

    public AdressInformations() {
        // Required empty public constructor
    }

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
        View v = inflater.inflate(R.layout.z_fragment_adress_informations, container, false);

        SharedPreferences prefs = getContext().getSharedPreferences("session", MODE_PRIVATE);
        int id_parents = prefs.getInt("id", -1);

        parentsDAO = new ParentsDAO(getContext());
        parents = parentsDAO.getParent(id_parents);

        validation = v.findViewById(R.id.validation);
        validation.setOnClickListener(click_event);

        city = v.findViewById(R.id.city);
        country = v.findViewById(R.id.country);
        adress = v.findViewById(R.id.address);
        postal_code = v.findViewById(R.id.postal_code);
        error = v.findViewById(R.id.error);

        city.setText(parents.getCity());
        country.setText(parents.getCountry());
        adress.setText(parents.getAdress());
        postal_code.setText(parents.getPostal_code());

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
                        parents.setAdress(adress_text);
                        parents.setCity(city_text);
                        parents.setPostal_code(postal_code_text);
                        parents.setCountry(country_text);

                        parentsDAO.update(parents);
                        Navigation.findNavController(view).navigate(R.id.action_settings_parents_self);
                    }

                    break;
            }

        }
    };

}