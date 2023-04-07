package com.example.ouestnounou.VISUAL_ACTIVITY_2.SEARCH;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.ouestnounou.DAO.ChildrenDAO;
import com.example.ouestnounou.MODEL.Children;
import com.example.ouestnounou.MODEL.Nurse;
import com.example.ouestnounou.R;

import java.util.ArrayList;

public class ParentsSearchNurse extends Fragment {
    private Button validation;
    private ChildrenDAO childrenDAO;
    private Spinner spinner;
    int id_nurse, id_parents;
    Children child;
    SharedPreferences prefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id_nurse = getArguments().getInt("nurse_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.e_fragment_parents_search_nurse, container, false);
        prefs = getContext().getSharedPreferences("session", MODE_PRIVATE);
        id_parents = prefs.getInt("id", 0);
        childrenDAO = new ChildrenDAO(getContext());

        //LIEN AVEC LA VUE
        validation =  view.findViewById(R.id.validation);
        spinner = view.findViewById(R.id.spinner);

        //ON CLICK
        validation.setOnClickListener(click_event);

        //SPINNER
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item);
        ArrayList<Children> childrens = childrenDAO.getChildrensByParentIdWithoutNurse(id_parents);
        for (Children child : childrens) {
            adapter.add(child.getFirstName() + " " + child.getLastName());
        }
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(spinnerItemListener);

        return view;
    }

    private View.OnClickListener click_event = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.validation:
                    Nurse nurse = new Nurse();
                    nurse.setId(id_nurse);
                    child.setNurse(nurse);
                    child.setNurseAccepted(-1);
                    childrenDAO.update(child);
                    Navigation.findNavController(getView()).navigate(R.id.action_parentsSearchNurse_to_contract);

                    break;
            }
        }
    };
    private AdapterView.OnItemSelectedListener spinnerItemListener = new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String selectedItem = adapterView.getItemAtPosition(i).toString();
            String[] words = selectedItem.split(" ");
            child = childrenDAO.getChildrenByNameEasy(words[0], words[1]);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

}
