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

    // Views
    private Button validationButton, connexionButton;
    private EditText firstNameEditText, lastNameEditText;
    private TextView errorTextView, stepTextView;
    private RadioButton nurseRadioButton, parentsRadioButton, boyRadioButton, girlRadioButton;
    private DatePicker birthDatePicker;

    // Arguments keys
    private static final String ARG_CATEGORY = "category";
    private static final String ARG_FIRST_NAME = "firstName";
    private static final String ARG_LAST_NAME = "lastName";
    private static final String ARG_DATE_BIRTH = "dateBirth";
    private static final String ARG_SEX = "sex";

    // Arguments values
    private String categoryValue, firstNameValue, lastNameValue, birthDateValue, sexValue;

    public RegisterP1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryValue = getArguments().getString(ARG_CATEGORY);
            firstNameValue = getArguments().getString(ARG_FIRST_NAME);
            lastNameValue = getArguments().getString(ARG_LAST_NAME);
            birthDateValue = getArguments().getString(ARG_DATE_BIRTH);
            sexValue = getArguments().getString(ARG_SEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.b_fragment_register_p1, container, false);

        // Find views
        validationButton = rootView.findViewById(R.id.validation);
        connexionButton = rootView.findViewById(R.id.connexion);
        nurseRadioButton = rootView.findViewById(R.id.nurse);
        parentsRadioButton = rootView.findViewById(R.id.parents);
        boyRadioButton = rootView.findViewById(R.id.boy);
        girlRadioButton = rootView.findViewById(R.id.girl);
        firstNameEditText = rootView.findViewById(R.id.first_name);
        lastNameEditText = rootView.findViewById(R.id.last_name);
        errorTextView = rootView.findViewById(R.id.error);
        stepTextView = rootView.findViewById(R.id.step);
        birthDatePicker = rootView.findViewById(R.id.birth);

        // Set listeners
        validationButton.setOnClickListener(clickListener);
        connexionButton.setOnClickListener(clickListener);
        nurseRadioButton.setOnClickListener(clickListener);
        parentsRadioButton.setOnClickListener(clickListener);
        boyRadioButton.setOnClickListener(clickListener);
        girlRadioButton.setOnClickListener(clickListener);

        // Set default values
        setDefaultCategory();
        setDefaultSex();

        if (getArguments() != null) {
            // Set values from arguments
            if (categoryValue.equals(getResources().getString(R.string.nurse))) {
                nurseRadioButton.setChecked(true);
            } else if (categoryValue.equals(getResources().getString(R.string.parents))) {
                parentsRadioButton.setChecked(true);
            }

            if (sexValue.equals(getResources().getString(R.string.boy))) {
                boyRadioButton.setChecked(true);
            } else if (sexValue.equals(getResources().getString(R.string.girl))) {
                girlRadioButton.setChecked(true);
            }

            firstNameEditText.setText(firstNameValue);
            lastNameEditText.setText(lastNameValue);

            String[] dateArray = birthDateValue.split("/");
            birthDatePicker.updateDate(
                    Integer.valueOf(dateArray[2]),
                    Integer.valueOf(dateArray[1]),
                    Integer.valueOf(dateArray[0])
            );
        }

        return rootView;
    }

    private void setDefaultCategory() {
        if (nurseRadioButton.isChecked()) {
            categoryValue = nurseRadioButton.getText().toString();
        } else if (parentsRadioButton.isChecked()) {
            categoryValue = parentsRadioButton.getText().toString();
        }
    }

    private void setDefaultSex() {
        if (sexValue.equals(getResources().getString(R.string.boy))) {
            boyRadioButton.setChecked(true);
        } else if (sexValue.equals(getResources().getString(R.string.girl))) {
            girlRadioButton.setChecked(true);
        }
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.validation:
                    Bundle args = new Bundle();

                    String firstName = firstNameEditText.getText().toString();
                    String lastName = lastNameEditText.getText().toString();
                    String birthDate = birthDatePicker.getDayOfMonth() + "/" + (birthDatePicker.getMonth() + 1) + "/" + birthDatePicker.getYear();

                    if (firstName.isEmpty() || lastName.isEmpty()) {
                        errorTextView.setText("Veuillez remplir les champs correctement.");
                    } else {
                        args.putString(ARG_CATEGORY, categoryValue);
                        args.putString(ARG_FIRST_NAME, firstName);
                        args.putString(ARG_LAST_NAME, lastName);
                        args.putString(ARG_DATE_BIRTH, birthDate);
                        args.putString(ARG_SEX, sexValue);
                        Navigation.findNavController(view).navigate(R.id.action_registerP1_to_registerP2, args);
                    }
                    break;
                case R.id.connexion:
                    Navigation.findNavController(view).navigate(R.id.action_registerP1_to_login);
                    break;
                case R.id.parents:
                    stepTextView.setText("Etape 1/3");
                    categoryValue = parentsRadioButton.getText().toString();
                    break;
                case R.id.nurse:
                    stepTextView.setText("Etape 1/4");
                    categoryValue = nurseRadioButton.getText().toString();
                    break;
                case R.id.boy:
                    sexValue = boyRadioButton.getText().toString();
                    break;
                case R.id.girl:
                    sexValue = girlRadioButton.getText().toString();
                    break;
            }
        }
    };


}