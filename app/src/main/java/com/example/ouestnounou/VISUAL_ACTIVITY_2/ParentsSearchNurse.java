package com.example.ouestnounou.VISUAL_ACTIVITY_2;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.ouestnounou.DAO.CalendarEventDAO;
import com.example.ouestnounou.DAO.ChildrenDAO;
import com.example.ouestnounou.MODEL.Children;
import com.example.ouestnounou.R;

import java.util.ArrayList;

public class ParentsSearchNurse extends Fragment {

    private ListView eventList;
    private ArrayList<String> events;
    private ArrayAdapter<String> adapter;
    private Button validation;
    private ChildrenDAO childrenDAO;
    private Spinner spinner;

    public ParentsSearchNurse() {
        // Required empty public constructor
    }


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
        View view = inflater.inflate(R.layout.e_fragment_parents_search_nurse, container, false);



        spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item);

        SharedPreferences prefs = getContext().getSharedPreferences("session", MODE_PRIVATE);
        int id_parents = prefs.getInt("id", 0);

        // Ajout des éléments au ArrayAdapter

        childrenDAO = new ChildrenDAO(getContext());
        ArrayList<Children> childrens = childrenDAO.getChildrensByParentId(id_parents);

        adapter.add("");
        for (Children child : childrens) {
            adapter.add(child.getFist_name() + " " + child.getLast_name());
        }

        // Associer l'adapter au spinner
        spinner.setAdapter(adapter);

        return view;
    }

}
