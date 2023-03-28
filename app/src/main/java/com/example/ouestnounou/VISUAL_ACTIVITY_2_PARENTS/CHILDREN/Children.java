package com.example.ouestnounou.VISUAL_ACTIVITY_2_PARENTS.CHILDREN;

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
import com.example.ouestnounou.VISUAL_ACTIVITY_2_PARENTS.CALENDAR.CalendarEventAdapter;

import java.util.ArrayList;

public class Children extends Fragment {
        ChildrenDAO childrenDAO;
        ListView children_list;
        Button add_children;
    public Children() {
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
        View v =  inflater.inflate(R.layout.e_fragment_children, container, false);

        children_list = v.findViewById(R.id.children_list);
        add_children = v.findViewById(R.id.add_children);

        add_children.setOnClickListener(click_event);


        childrenDAO = new ChildrenDAO(getContext());
        ArrayList<com.example.ouestnounou.MODEL.Children> childrens = childrenDAO.getChildrens();

        // Créer l'adapter pour les événements
        ChildrenAdapter adapter = new ChildrenAdapter(getContext(), childrens);

        // Associer l'adapter à la ListView
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