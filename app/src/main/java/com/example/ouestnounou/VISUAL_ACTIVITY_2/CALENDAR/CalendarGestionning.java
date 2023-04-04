package com.example.ouestnounou.VISUAL_ACTIVITY_2.CALENDAR;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.ouestnounou.DAO.CalendarEventDAO;
import com.example.ouestnounou.DAO.ChildrenDAO;
import com.example.ouestnounou.MODEL.CalendarEvent;
import com.example.ouestnounou.MODEL.Children;
import com.example.ouestnounou.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarGestionning extends Fragment {

    private CalendarView calendarView;
    private ListView eventList;
    private ArrayList<String> events;
    private ArrayAdapter<String> adapter;
    private Button addEventButton;
    private CalendarEventDAO calendarEventDAO;
    private ChildrenDAO childrenDAO;
    private String selectedDateString;
    private Spinner spinner;

    int year;
    int month;
    int dayOfMonth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.e_fragment_calendar_gestionning, container, false);


        SharedPreferences prefs = getContext().getSharedPreferences("session", MODE_PRIVATE);
        String category = prefs.getString("category", "");
        int id_category = prefs.getInt("id", 0);

        calendarEventDAO = new CalendarEventDAO(getContext());

        // Récupération des éléments du layout
        calendarView = rootView.findViewById(R.id.calendarView);
        eventList = rootView.findViewById(R.id.eventList);
        addEventButton = rootView.findViewById(R.id.addEventButton);



        // Initialisation de la liste d'événements
        events = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice, events);
        eventList.setAdapter(adapter);

        spinner = rootView.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item);

        int id_parents = prefs.getInt("id", 0);

        // Ajout des éléments au ArrayAdapter

        childrenDAO = new ChildrenDAO(getContext());
        ArrayList<Children> childrens;

        if(category.equals(getResources().getString(R.string.nurse))){
            addEventButton.setVisibility(View.GONE);
            childrens = childrenDAO.getChildrensByNurseId(id_parents);
        }
        else{
            childrens = childrenDAO.getChildrensByParentId(id_parents);
        }



        adapter.add("");
        for (Children child : childrens) {
            adapter.add(child.getFist_name() + " " + child.getLast_name());
        }

        // Associer l'adapter au spinner
        spinner.setAdapter(adapter);

        // Ajout des événements pour la date actuelle
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.set(year, month, dayOfMonth);
        // Get the selected date as a Date object
        Date selectedDate = calendar.getTime();
        // Format the date as a string
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        selectedDateString = dateFormat.format(selectedDate);
        List<CalendarEvent> mEvents = calendarEventDAO.getAllEventsByDate(selectedDateString);
        // Créer l'adapter pour les événements
        CalendarEventAdapter adapterDate = new CalendarEventAdapter(getContext(), mEvents);
        // Associer l'adapter à la ListView
        eventList.setAdapter(adapterDate);

        // Ajout d'un écouteur d'événement pour le CalendarView
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year2, int month2, int dayOfMonth2) {


                year = year2;
                month = month2;
                dayOfMonth = dayOfMonth2;

                String selectedItem = spinner.getSelectedItem().toString();
                Children child;
                if( ! selectedItem.isEmpty()){
                    String[] words = selectedItem.split(" ");
                    child = childrenDAO.getChildrenByNameEasy(words[0], words[1]);
                }
                else {
                    child = new Children();
                    child.setId(-1);
                }

                Calendar calendar = Calendar.getInstance();

                calendar.set(year2, month2, dayOfMonth2);
                // Get the selected date as a Date object
                Date selectedDate = calendar.getTime();
                // Format the date as a string
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                selectedDateString = dateFormat.format(selectedDate);

                List<CalendarEvent> mEvents = calendarEventDAO.getAllEventsByDateAndChild(selectedDateString, child.getId());
                // Créer l'adapter pour les événements
                CalendarEventAdapter adapterDate = new CalendarEventAdapter(getContext(), mEvents);
                // Associer l'adapter à la ListView
                eventList.setAdapter(adapterDate);

            }

        });

        // Ajout d'un écouteur d'événement pour le bouton
        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers un autre fragment ici
                Bundle bundle = new Bundle();
                bundle.putString("date", selectedDateString);
                Navigation.findNavController(v).navigate(R.id.action_calendar_gestionning_to_calendar_parents_add, bundle);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                String selectedItem = parent.getItemAtPosition(position).toString();
                Children child;
                if( ! selectedItem.isEmpty()){
                    String[] words = selectedItem.split(" ");
                    child = childrenDAO.getChildrenByNameEasy(words[0], words[1]);
                }
                else {
                    child = new Children();
                    child.setId(-1);
                }


                calendar.set(year, month, dayOfMonth);
                // Get the selected date as a Date object
                Date selectedDate = calendar.getTime();
                // Format the date as a string
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                selectedDateString = dateFormat.format(selectedDate);

                List<CalendarEvent> mEvents = calendarEventDAO.getAllEventsByDateAndChild(selectedDateString, child.getId());
                // Créer l'adapter pour les événements
                CalendarEventAdapter adapterDate = new CalendarEventAdapter(getContext(), mEvents);
                // Associer l'adapter à la ListView
                eventList.setAdapter(adapterDate);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return rootView;
    }




}