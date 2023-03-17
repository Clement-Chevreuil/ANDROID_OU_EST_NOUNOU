package com.example.ouestnounou.VISUAL_ACTIVITY_2_PARENTS.CALENDAR;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.ouestnounou.DAO.CalendarEventDAO;
import com.example.ouestnounou.MODEL.CalendarEvent;
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
    private String selectedDateString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.e_fragment_calendar_gestionning, container, false);

        calendarEventDAO = new CalendarEventDAO(getContext());

        // Récupération des éléments du layout
        calendarView = rootView.findViewById(R.id.calendarView);
        eventList = rootView.findViewById(R.id.eventList);
        addEventButton = rootView.findViewById(R.id.addEventButton);

        // Initialisation de la liste d'événements
        events = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice, events);
        eventList.setAdapter(adapter);

        // Ajout des événements pour la date actuelle
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Ajout d'un écouteur d'événement pour le CalendarView
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {


                // Create a Calendar instance and set the selected date
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);

                // Get the selected date as a Date object
                Date selectedDate = calendar.getTime();

                // Format the date as a string
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                selectedDateString = dateFormat.format(selectedDate);

                List<CalendarEvent> mEvents = calendarEventDAO.getAllEventsByDate(selectedDateString);

                // Créer l'adapter pour les événements
                CalendarEventAdapter adapter = new CalendarEventAdapter(getContext(), mEvents);

                // Associer l'adapter à la ListView
                eventList.setAdapter(adapter);

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

        return rootView;
    }

}