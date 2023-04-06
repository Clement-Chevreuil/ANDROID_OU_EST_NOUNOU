package com.example.ouestnounou.VISUAL_ACTIVITY_2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ouestnounou.DAO.SchoolDAO;
import com.example.ouestnounou.MODEL.School;
import com.example.ouestnounou.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddSchool#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSchool extends Fragment {

    Button validation, back;
    LinearLayout city_cp;
    EditText name, phone, address, city, postal_code;
    TextView error, step;

    String name_text, phoneString, address_text, cityString, postalCodeString;

    private static final String ARG_NAME = "name";
    private static final String ARG_PHONE = "phone";
    private static final String ARG_ADDRESS = "address";
    private static final String ARG_CITY = "city";
    private static final String ARG_POSTAL_CODE = "postal_code";


    public AddSchool() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name_text = getArguments().getString(ARG_NAME);
            phoneString = getArguments().getString(ARG_PHONE);
            address_text = getArguments().getString(ARG_ADDRESS);
            cityString = getArguments().getString(ARG_CITY);
            postalCodeString = getArguments().getString(ARG_POSTAL_CODE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.c_fragment_add_school, container, false);

        name = v.findViewById(R.id.name);
        phone = v.findViewById(R.id.phone);
        address = v.findViewById(R.id.address);
        city = v.findViewById(R.id.city);
        postal_code = v.findViewById(R.id.postal_code);

        validation = v.findViewById(R.id.validation);
        validation.setOnClickListener(click_event);

        if(getArguments() != null)
        {
            name.setText(name_text);
            phone.setText(phoneString);
            address.setText(address_text);
            city.setText(cityString);
            postal_code.setText(postalCodeString);
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

                    name_text = name.getText().toString();
                    phoneString = phone.getText().toString();
                    address_text = address.getText().toString();
                    cityString = city.getText().toString();
                    postalCodeString = postal_code.getText().toString();

                    if(name_text.isEmpty() || phoneString.isEmpty() || address_text.isEmpty() || cityString.isEmpty() || postalCodeString.isEmpty())
                    {
                        error.setText("Veuillez remplir les champs correctement");
                    }
                    else
                    {
                        args.putString(ARG_NAME, name_text);
                        args.putString(ARG_PHONE, phoneString);
                        args.putString(ARG_ADDRESS, address_text);
                        args.putString(ARG_CITY, cityString);
                        args.putString(ARG_POSTAL_CODE, postalCodeString);

                        //School new_school = new School(name_text, phoneString, address_text, cityString, postalCodeString);
                        //SchoolDAO schoolDAO = new SchoolDAO(getContext());
                        //schoolDAO.add(new_school);

                        //Navigation.findNavController(view).navigate(R.id.action_addSchool_to_parents2);
                    }
                    break;
            }

        }
    };
}