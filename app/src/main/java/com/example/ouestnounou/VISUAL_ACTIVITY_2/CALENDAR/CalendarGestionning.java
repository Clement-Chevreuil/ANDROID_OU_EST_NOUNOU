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

    //SPINNER
    private ArrayList<Children> childrens;
    private ArrayAdapter<String> adapterSpinner;
    private Spinner childrenSpinner;
    String selectedItem;

    //CALENDAR
    private ArrayList<String> events;
    private ArrayAdapter<String> adapter;
    List<CalendarEvent> mEvents;
    private CalendarView calendarView;
    private ListView calendarListView;
    private String selectedDateString;
    Calendar calendar;
    Date selectedDate;
    SimpleDateFormat dateFormat;
    CalendarEventAdapter calendarEventAdapter;
    int year, month, dayOfMonth, id_category;

    //OTHERS
    private Button addEventButton;
    private CalendarEventDAO calendarEventDAO;
    private ChildrenDAO childrenDAO;
    private String category;
    SharedPreferences prefs;
    Children child;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.e_fragment_calendar_gestionning, container, false);
        calendarEventDAO = new CalendarEventDAO(getContext());
        childrenDAO = new ChildrenDAO(getContext());

        prefs = getContext().getSharedPreferences("session", MODE_PRIVATE);
        category = prefs.getString("category", "");
        id_category = prefs.getInt("id", 0);

        calendarView = rootView.findViewById(R.id.calendarView);
        calendarListView = rootView.findViewById(R.id.eventList);
        addEventButton = rootView.findViewById(R.id.addEventButton);
        childrenSpinner = rootView.findViewById(R.id.spinner);


        // GESTION DU SPINNER
        if(category.equals(getResources().getString(R.string.nurse))){
            addEventButton.setVisibility(View.GONE);
            childrens = childrenDAO.getChildrensByNurseId(id_category);
        }
        else{
            childrens = childrenDAO.getChildrensByParentId(id_category);
        }
        adapterSpinner = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item);
        adapterSpinner.add("");
        for (Children child : childrens) { adapterSpinner.add(child.getFirstName() + " " + child.getLastName()); }
        childrenSpinner.setAdapter(adapterSpinner);


        // GESTION DU CALENDRIER INITIALISATION ET REMPLISSAGE POUR LA DATE D'AUJOURD'HUI
        events = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice, events);
        calendarListView.setAdapter(adapter);
        // Ajout des événements pour la date actuelle
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year, month, dayOfMonth);
        selectedDate = calendar.getTime();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        selectedDateString = dateFormat.format(selectedDate);
        mEvents = calendarEventDAO.getAllEventsByDate(selectedDateString);
        calendarEventAdapter = new CalendarEventAdapter(getContext(), mEvents);
        calendarListView.setAdapter(calendarEventAdapter);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year2, int month2, int dayOfMonth2) {

                year = year2;
                month = month2;
                dayOfMonth = dayOfMonth2;

                selectedItem = childrenSpinner.getSelectedItem().toString();
                if( ! selectedItem.isEmpty()){
                    String[] words = selectedItem.split(" ");
                    child = childrenDAO.getChildrenByNameEasy(words[0], words[1]);
                }
                else {
                    child = new Children();
                    child.setId(-1);
                }

                calendar = Calendar.getInstance();
                calendar.set(year2, month2, dayOfMonth2);
                selectedDate = calendar.getTime();
                dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                selectedDateString = dateFormat.format(selectedDate);
                mEvents = calendarEventDAO.getAllEventsByDateAndChild(selectedDateString, child.getId());
                calendarEventAdapter = new CalendarEventAdapter(getContext(), mEvents);
                calendarListView.setAdapter(calendarEventAdapter);

            }

        });
        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers un autre fragment ici
                Bundle bundle = new Bundle();
                bundle.putString("date", selectedDateString);
                Navigation.findNavController(v).navigate(R.id.action_calendar_gestionning_to_calendar_parents_add, bundle);
            }
        });
        childrenSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                selectedItem = parent.getItemAtPosition(position).toString();
                if( ! selectedItem.isEmpty()){
                    String[] words = selectedItem.split(" ");
                    child = childrenDAO.getChildrenByNameEasy(words[0], words[1]);
                }
                else {
                    child = new Children();
                    child.setId(-1);
                }

                calendar.set(year, month, dayOfMonth);
                selectedDate = calendar.getTime();
                dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                selectedDateString = dateFormat.format(selectedDate);
                mEvents = calendarEventDAO.getAllEventsByDateAndChild(selectedDateString, child.getId());
                calendarEventAdapter = new CalendarEventAdapter(getContext(), mEvents);
                calendarListView.setAdapter(calendarEventAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return rootView;
    }




}