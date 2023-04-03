package com.example.ouestnounou.VISUAL_ACTIVITY_2.NurseContact;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.ouestnounou.DAO.ChildrenDAO;
import com.example.ouestnounou.DAO.ParentsDAO;
import com.example.ouestnounou.MODEL.Children;
import com.example.ouestnounou.MODEL.Parents;
import com.example.ouestnounou.R;
import com.example.ouestnounou.VISUAL_ACTIVITY_2.CHILDREN.ChildrenAdapter;

import java.util.ArrayList;

public class ContractNurse extends Fragment {

    ChildrenDAO childrenDAO;
    ListView children_list;
    Button add_children;

    public ContractNurse() {
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
        View v = inflater.inflate(R.layout.e_fragment_contract_nurse, container, false);

        children_list = v.findViewById(R.id.children_list);

        childrenDAO = new ChildrenDAO(getContext());

        SharedPreferences prefs = getContext().getSharedPreferences("session", MODE_PRIVATE);
        int id_nurse = prefs.getInt("id", 0);

        ArrayList<Children> childrens = childrenDAO.getChildrensByNurseIdWaiting(id_nurse);

        // Créer l'adapter pour les événements
        ChildrenAdapter adapter = new ChildrenAdapter(getContext(), childrens);

        // Associer l'adapter à la ListView
        children_list.setAdapter(adapter);

        return v;
    }
}