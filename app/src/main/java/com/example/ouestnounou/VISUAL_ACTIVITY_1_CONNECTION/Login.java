package com.example.ouestnounou.VISUAL_ACTIVITY_1_CONNECTION;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ouestnounou.DAO.UserDAO;
import com.example.ouestnounou.MODEL.User;
import com.example.ouestnounou.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {

    EditText email, password;
    TextView error;
    Button connexion;
    UserDAO userDAO;

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

        userDAO = new UserDAO(getContext());

        error = v.findViewById(R.id.error);
        email = v.findViewById(R.id.email);
        password = v.findViewById(R.id.password);
        connexion = v.findViewById(R.id.validation);
        connexion.setOnClickListener(connexionApp);

        return v;
    }

    View.OnClickListener connexionApp = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            //View itemDetailFragmentContainer = v.findViewById(R.id.fr);

            String pseudo_validation = email.getText().toString();
            String password_validation = password.getText().toString();

            if(! pseudo_validation.isEmpty() && ! password_validation.isEmpty()) {
                User user = userDAO.connexion(pseudo_validation, password_validation);
                if(user != null){
                    /*Bundle bundle = new Bundle();
                    bundle.putInt("idUser", user.getId());*/
                }
                else{ erreurConnexion(); }
            }
            else { erreurNonRemplie(); }


        }
    };

    public void erreurNonRemplie() {
        error.setText("Veuillez remplir correctement les champs.");
    }
    public void erreurConnexion() {
        error.setText("Les identifiants ne sont pas les bons.");
    }

}