package com.example.ouestnounou.VISUAL_ACTIVITY_2.CONTRACT;

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
import com.example.ouestnounou.MODEL.Children;
import com.example.ouestnounou.R;

import java.util.ArrayList;

public class Contract extends Fragment {

    ChildrenDAO childrenDAO;
    ListView children_list;
    Button add_children;

    public Contract() {
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
        int id = prefs.getInt("id", 0);
        String category = prefs.getString("category", "");

        if(category.equals(getResources().getString(R.string.nurse))){
            ArrayList<Children> childrens = childrenDAO.getChildrensByNurseIdWaiting(id);
            ChildrenNurseAdapter adapter = new ChildrenNurseAdapter(getContext(), childrens);
            children_list.setAdapter(adapter);
        }
        else{
            ArrayList<Children> childrens = childrenDAO.getChildrensByParentsIdWithNurse(id);
            ChildrenParentsAdapter adapter = new ChildrenParentsAdapter(getContext(), childrens);
            children_list.setAdapter(adapter);
        }

        return v;
    }
}