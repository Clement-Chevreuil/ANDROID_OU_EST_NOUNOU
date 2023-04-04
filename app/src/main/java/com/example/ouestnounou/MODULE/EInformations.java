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

import com.example.ouestnounou.DAO.ParentsDAO;
import com.example.ouestnounou.MODEL.Parents;
import com.example.ouestnounou.R;

public class EInformations extends Fragment {

    Button validation;
    EditText mail, new_password, old_password, phone;
    TextView error;
    String mail_text, password_text, phone_text;
    Parents parents;
    ParentsDAO parentsDAO;
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

        validation = v.findViewById(R.id.validation);
        validation.setOnClickListener(click_event);

        phone = v.findViewById(R.id.phone);
        mail = v.findViewById(R.id.mail);
        new_password = v.findViewById(R.id.new_password);
        old_password = v.findViewById(R.id.old_password);
        error = v.findViewById(R.id.error);

        SharedPreferences prefs = getContext().getSharedPreferences("session", MODE_PRIVATE);
        int id_parents = prefs.getInt("id", -1);

        parentsDAO = new ParentsDAO(getContext());
        parents = parentsDAO.getParent(id_parents);

        phone.setText(parents.getPhone());
        mail.setText(parents.getMail());

        return v;
    }

    private View.OnClickListener click_event = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.validation:


                    mail_text = mail.getText().toString();
                    password_text = new_password.getText().toString();
                    phone_text = phone.getText().toString();

                    if(mail_text.isEmpty() || password_text.isEmpty() || old_password.getText().toString().isEmpty() || phone_text.isEmpty())
                    {
                        error.setText("Veuillez remplir les champs correctements");
                    }
                    else if ( ! parents.getPassword().equals(old_password.getText().toString()))
                    {
                        error.setText("LEs mots de passe ne sont pas les mêmes");
                    }
                    else
                    {
                        parents.setMail(mail_text);
                        parents.setPassword(password_text);
                        parentsDAO.update(parents);
                        Toast.makeText(getContext(), "Vos informations ont bien été mise à jour!", Toast.LENGTH_SHORT).show();
                        //Navigation.findNavController(view).navigate(R.id.action_registerP3_to_registerP4Parents);
                    }
                    break;

            }

        }
    };
}