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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {

    FrameLayout frame;
    Button validation, register_link, forget_password_link;
    EditText mail, password;
    TextView error;
    RelativeLayout relative_first, form, bottom;
    ImageView logo, bar;
    RadioGroup category;
    RadioButton nurse, parents;
    String category_text;

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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.b_fragment_login, container, false);

        error = v.findViewById(R.id.error);
        mail = v.findViewById(R.id.mail);
        password = v.findViewById(R.id.password);

        validation = v.findViewById(R.id.validation);
        validation.setOnClickListener(click_event);

        nurse = v.findViewById(R.id.nurse);
        nurse.setOnClickListener(click_event);

        parents = v.findViewById(R.id.parents);
        parents.setOnClickListener(click_event);

        register_link = v.findViewById(R.id.register_link);
        register_link.setOnClickListener(click_event);

        if(nurse.isChecked()){
            category_text = nurse.getText().toString();
        }
        else if(parents.isChecked()){
            category_text = parents.getText().toString();
        }

        return v;
    }

    private View.OnClickListener click_event = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.nurse :
                    category_text = nurse.getText().toString();
                    break;

                case R.id.parents:
                    category_text = parents.getText().toString();
                    break;

                case R.id.validation :

                    String mail_validation = mail.getText().toString();
                    String password_validation = password.getText().toString();

                    Nurse nurse = null;
                    Parents parents = null;

                    if(! mail_validation.isEmpty() && ! password_validation.isEmpty()) {
                        if(category_text.equals(getResources().getString(R.string.nurse)))
                        {
                            NurseDAO nurseDAO = new NurseDAO(getContext());
                            nurse = nurseDAO.connexion(mail_validation, password_validation);
                        }
                        else if(category_text.equals(getResources().getString(R.string.parents)))
                        {
                            ParentsDAO parentsDAO = new ParentsDAO(getContext());
                            parents = parentsDAO.connexion(mail_validation, password_validation);
                        }
                        if(nurse != null)
                        {
                            SharedPreferences prefs = getContext().getSharedPreferences("session", Context.MODE_PRIVATE);
                            SharedPreferences.Editor shared = prefs.edit();
                            shared.putString("category", category_text);
                            shared.putInt("id", nurse.getId());
                            shared.apply();

                            Navigation.findNavController(view).navigate(R.id.action_login_to_parents2);
                        }
                        else if(parents != null)
                        {
                            SharedPreferences prefs = getContext().getSharedPreferences("session", Context.MODE_PRIVATE);
                            SharedPreferences.Editor shared = prefs.edit();
                            shared.putString("category", category_text);
                            shared.putInt("id", parents.getId());
                            shared.apply();

                            Navigation.findNavController(view).navigate(R.id.action_login_to_parents2);
                        }
                        else{ erreurConnexion(); }
                    }
                    else { erreurNonRemplie(); }
                    break;

                case R.id.register_link:
                    Navigation.findNavController(view).navigate(R.id.action_login_to_registerP1);
                    break;

            }
        }
    };

    public void erreurNonRemplie() {
        error.setText("Veuillez remplir correctement les champs.");
    }
    public void erreurConnexion() {
        error.setText("Les identifiants ne sont pas les bons.");
    }

}