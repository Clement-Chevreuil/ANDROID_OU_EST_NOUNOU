package com.example.ouestnounou.VISUAL_ACTIVITY_1_CONNECTION;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ouestnounou.DAO.NurseDAO;
import com.example.ouestnounou.DAO.ParentsDAO;
import com.example.ouestnounou.MODEL.Nurse;
import com.example.ouestnounou.MODEL.Parents;
import com.example.ouestnounou.R;

import java.util.Arrays;
import java.util.List;

public class Login extends Fragment {

    Button validationButton, registerLinkButton;
    EditText mailEditText, passwordEditText;
    TextView errorTextView;
    RadioButton nurseRadioButton, parentsRadioButton;
    String selectedCategory;

    public Login() {

    }

    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.b_fragment_login, container, false);

        errorTextView = view.findViewById(R.id.error);
        mailEditText = view.findViewById(R.id.mail);
        passwordEditText = view.findViewById(R.id.password);

        validationButton = view.findViewById(R.id.validation);
        validationButton.setOnClickListener(clickListener);

        nurseRadioButton = view.findViewById(R.id.nurse);
        nurseRadioButton.setOnClickListener(clickListener);

        parentsRadioButton = view.findViewById(R.id.parents);
        parentsRadioButton.setOnClickListener(clickListener);

        registerLinkButton = view.findViewById(R.id.register_link);
        registerLinkButton.setOnClickListener(clickListener);

        selectedCategory = "";

        if(nurseRadioButton.isChecked()){
            selectedCategory = nurseRadioButton.getText().toString();
        }
        else if(parentsRadioButton.isChecked()){
            selectedCategory = parentsRadioButton.getText().toString();
        }

        return view;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.nurse :
                    selectedCategory = nurseRadioButton.getText().toString();
                    break;

                case R.id.parents:
                    selectedCategory = parentsRadioButton.getText().toString();
                    break;

                case R.id.validation :
                    String mailValidation = mailEditText.getText().toString();
                    String passwordValidation = passwordEditText.getText().toString();

                    Nurse nurse = null;
                    Parents parents = null;

                    if(!mailValidation.isEmpty() && !passwordValidation.isEmpty()) {
                        if(selectedCategory.equals(getResources().getString(R.string.nurse)))
                        {
                            NurseDAO nurseDAO = new NurseDAO(getContext());
                            nurse = nurseDAO.connexion(mailValidation, passwordValidation);
                        }
                        else if(selectedCategory.equals(getResources().getString(R.string.parents)))
                        {
                            ParentsDAO parentsDAO = new ParentsDAO(getContext());
                            parents = parentsDAO.connexion(mailValidation, passwordValidation);
                        }
                        if(nurse != null)
                        {
                            SharedPreferences prefs = getContext().getSharedPreferences("session", Context.MODE_PRIVATE);
                            SharedPreferences.Editor shared = prefs.edit();
                            shared.putString("category", selectedCategory);
                            shared.putInt("id", nurse.getId());
                            shared.apply();

                            Navigation.findNavController(view).navigate(R.id.action_login_to_parents2);
                        }
                        else if(parents != null)
                        {
                            SharedPreferences prefs = getContext().getSharedPreferences("session", Context.MODE_PRIVATE);
                            SharedPreferences.Editor shared = prefs.edit();
                            shared.putString("category", selectedCategory);
                            shared.putInt("id", parents.getId());
                            shared.apply();

                            Navigation.findNavController(view).navigate(R.id.action_login_to_parents2);
                        }
                        else{
                            showLoginError();
                        }
                    }
                    else {
                        showIncompleteFieldsError();
                    }
                    break;

                case R.id.register_link:
                    Navigation.findNavController(view).navigate(R.id.action_login_to_registerP1);
                    break;

            }
        }
    };

    public void showIncompleteFieldsError() {
        errorTextView.setText("Veuillez remplir correctement les champs.");
    }
    public void showLoginError() {
        errorTextView.setText("Les identifiants ne sont pas les bons.");
    }

}