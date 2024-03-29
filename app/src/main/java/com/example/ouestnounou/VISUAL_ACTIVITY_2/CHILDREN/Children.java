package com.example.ouestnounou.VISUAL_ACTIVITY_2.CHILDREN;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.ouestnounou.DAO.ChildrenDAO;
import com.example.ouestnounou.R;

import java.util.ArrayList;

public class Children extends Fragment {
    ChildrenDAO childrenDAO;
    ListView children_list;
    Button add_children;
    SharedPreferences prefs;
    String category;
    int id_category;
    ArrayList<com.example.ouestnounou.MODEL.Children> childrens;
    ChildrenAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.e_fragment_children, container, false);
        childrenDAO = new ChildrenDAO(getContext());

        prefs = getContext().getSharedPreferences("session", MODE_PRIVATE);
        category = prefs.getString("category", "");
        id_category = prefs.getInt("id", 0);

        children_list = v.findViewById(R.id.children_list);
        add_children = v.findViewById(R.id.add_children);
        add_children.setOnClickListener(click_event);

        if(category.equals(getResources().getString(R.string.nurse))){
            add_children.setVisibility(View.GONE);
            childrens = childrenDAO.getChildrensByNurseIdExistingNurse(id_category);
        }
        else{
            childrens = childrenDAO.getChildrensByParentId(id_category);
        }

        //BACKDROP
        adapter = new ChildrenAdapter(getContext(), childrens);
        children_list.setAdapter(adapter);

        return v;
    }
    private View.OnClickListener click_event = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Navigation.findNavController(view).navigate(R.id.action_children_to_addChildren);
        }
    };
}