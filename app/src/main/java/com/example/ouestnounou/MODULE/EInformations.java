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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ouestnounou.DAO.NurseDAO;
import com.example.ouestnounou.DAO.ParentsDAO;
import com.example.ouestnounou.MODEL.Nurse;
import com.example.ouestnounou.MODEL.Parents;
import com.example.ouestnounou.R;

public class EInformations extends Fragment {
    Button validationButton;
    EditText mailEditText, newPasswordEditText, oldPasswordEditText, phoneEditText;
    TextView errorTextView;
    String mailString, passwordString, phoneString, categoryString;
    ParentsDAO parentsDAO;
    NurseDAO nurseDAO;
    Nurse nurse;
    Parents parents;
    int idCategoryInt;
    SharedPreferences prefs;

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
        View v = inflater.inflate(R.layout.z_fragment_e_informations, container, false);

        validationButton = v.findViewById(R.id.validation);
        validationButton.setOnClickListener(click_event);

        phoneEditText = v.findViewById(R.id.phone);
        mailEditText = v.findViewById(R.id.mail);
        newPasswordEditText = v.findViewById(R.id.new_password);
        oldPasswordEditText = v.findViewById(R.id.old_password);
        errorTextView = v.findViewById(R.id.error);

        prefs = getContext().getSharedPreferences("session", MODE_PRIVATE);
        idCategoryInt = prefs.getInt("id", -1);
        categoryString = prefs.getString("categoryString", "");

        if(categoryString.equals(getResources().getString(R.string.nurse))){
            nurseDAO = new NurseDAO(getContext());
            nurse = nurseDAO.getNurseById(idCategoryInt);
            phoneEditText.setText(nurse.getPhone());
            mailEditText.setText(nurse.getMail());

        }
        else{
            parentsDAO = new ParentsDAO(getContext());
            parents = parentsDAO.getParent(idCategoryInt);
            phoneEditText.setText(parents.getPhone());
            mailEditText.setText(parents.getMail());
        }
        return v;
    }

    private View.OnClickListener click_event = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.validation:
                    mailString = mailEditText.getText().toString();
                    passwordString = newPasswordEditText.getText().toString();
                    phoneString = phoneEditText.getText().toString();

                    if(mailString.isEmpty() || passwordString.isEmpty() || oldPasswordEditText.getText().toString().isEmpty() || phoneString.isEmpty())
                    {
                        errorTextView.setText("Veuillez remplir les champs correctements");
                    }

                    else
                    {
                        if(categoryString.equals(getResources().getString(R.string.nurse))){
                            if ( ! nurse.getPassword().equals(oldPasswordEditText.getText().toString()))
                            {
                                errorTextView.setText("Les mots de passe ne sont pas les mêmes");
                            }
                            else{
                                nurse.setMail(mailString);
                                nurse.setPhone(phoneString);
                                nurse.setPassword(passwordString);
                                nurseDAO.update(nurse);
                            }
                        }
                        else{
                            if ( ! parents.getPassword().equals(oldPasswordEditText.getText().toString()))
                            {
                                errorTextView.setText("Les mots de passe ne sont pas les mêmes");
                            }
                            else{
                                parents.setMail(mailString);
                                parents.setPhone(phoneString);
                                parents.setPassword(passwordString);
                                parentsDAO.update(parents);
                            }
                        }
                        Toast.makeText(getContext(), "Vos informations ont bien été mise à jour!", Toast.LENGTH_SHORT).show();
                    }
                    break;

            }

        }
    };
}