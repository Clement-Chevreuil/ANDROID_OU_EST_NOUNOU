package com.example.ouestnounou.VISUAL_ACTIVITY_1_CONNECTION;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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

        //userDAO = new UserDAO(getContext());


        error = v.findViewById(R.id.error);
        mail = v.findViewById(R.id.mail);
        password = v.findViewById(R.id.password);

        validation = v.findViewById(R.id.validation);
        validation.setOnClickListener(connexionApp);

        register_link = v.findViewById(R.id.register_link);
        register_link.setOnClickListener(fragment_register);

        return v;
    }

    View.OnClickListener connexionApp = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            //View itemDetailFragmentContainer = v.findViewById(R.id.fr);

            String pseudo_validation = mail.getText().toString();
            String password_validation = password.getText().toString();

            if(! pseudo_validation.isEmpty() && ! password_validation.isEmpty()) {
                //User user = userDAO.connexion(pseudo_validation, password_validation);
                //if(user != null){
                    /*Bundle bundle = new Bundle();
                    bundle.putInt("idUser", user.getId());*/
                //}
                //else{ erreurConnexion(); }
            }
            else { erreurNonRemplie(); }


        }
    };

    View.OnClickListener fragment_register = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Navigation.findNavController(v).navigate(R.id.action_login_to_registerP1);
        }
    };

    public void erreurNonRemplie() {
        error.setText("Veuillez remplir correctement les champs.");
    }
    public void erreurConnexion() {
        error.setText("Les identifiants ne sont pas les bons.");
    }

}