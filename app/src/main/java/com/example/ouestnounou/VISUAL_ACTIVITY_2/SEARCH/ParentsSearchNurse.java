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
import android.widget.ListView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.ouestnounou.DAO.CalendarEventDAO;
import com.example.ouestnounou.DAO.ChildrenDAO;
import com.example.ouestnounou.MODEL.Children;
import com.example.ouestnounou.MODEL.Nurse;
import com.example.ouestnounou.R;

import java.util.ArrayList;

public class ParentsSearchNurse extends Fragment {

    private ListView eventList;
    private ArrayList<String> events;
    private ArrayAdapter<String> adapter;
    private Button validation;
    private ChildrenDAO childrenDAO;
    private Spinner spinner;
    private static final String ARG_NURSE = "nurse_id";
    int id_nurse;
    Children child;

    public ParentsSearchNurse() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id_nurse = getArguments().getInt(ARG_NURSE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.e_fragment_parents_search_nurse, container, false);

        spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item);

        validation =  view.findViewById(R.id.validation);
        validation.setOnClickListener(click_event);

        SharedPreferences prefs = getContext().getSharedPreferences("session", MODE_PRIVATE);
        int id_parents = prefs.getInt("id", 0);

        // Ajout des éléments au ArrayAdapter
        childrenDAO = new ChildrenDAO(getContext());
        ArrayList<Children> childrens = childrenDAO.getChildrensByParentIdWithoutNurse(id_parents);

        for (Children child : childrens) {
            adapter.add(child.getFist_name() + " " + child.getLast_name());
        }

        // Associer l'adapter au spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                String[] words = selectedItem.split(" ");
                child = childrenDAO.getChildrenByNameEasy(words[0], words[1]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


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
                    child.setNurse_accepted(-1);
                    childrenDAO.update(child);
                    Navigation.findNavController(getView()).navigate(R.id.action_parentsSearchNurse_to_contract);

                    break;
            }
        }
    };

}
