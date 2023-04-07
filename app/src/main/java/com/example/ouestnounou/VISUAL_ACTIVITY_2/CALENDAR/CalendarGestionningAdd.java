package com.example.ouestnounou.VISUAL_ACTIVITY_2.CALENDAR;

import static android.content.Context.MODE_PRIVATE;

import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import java.util.Locale;

public class CalendarGestionningAdd extends Fragment {
    //CALENDAR
    private CalendarView calendarView;
    private String selectedDateString;

    //SPINNER
    private Spinner spinner;
    ArrayAdapter<String> adapter;

    //OTHERS
    private TextView textViewStartTime, textViewEndTime;
    private Button buttonStartTime, buttonEndTime, buttonSubmit;
    private String selectedStartTime, selectedEndTime;
    private ChildrenDAO childrenDAO;
    Children child;
    SharedPreferences prefs;
    int idCategory;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.e_fragment_calendar_gestion, container, false);
        childrenDAO = new ChildrenDAO(getContext());

        prefs = getContext().getSharedPreferences("session", MODE_PRIVATE);
        idCategory = prefs.getInt("id", 0);

        // Find views
        calendarView = view.findViewById(R.id.calendarView);
        textViewStartTime = view.findViewById(R.id.textViewStartTime);
        textViewEndTime = view.findViewById(R.id.textViewEndTime);
        buttonStartTime = view.findViewById(R.id.buttonStartTime);
        buttonEndTime = view.findViewById(R.id.buttonEndTime);
        buttonSubmit = view.findViewById(R.id.buttonSubmit);
        spinner = view.findViewById(R.id.spinner);

        textViewStartTime.setText("Start Time: Not Selected");
        textViewEndTime.setText("End Time: Not Selected");

        //GESTION SPINNER
        ArrayList<Children> childrens = childrenDAO.getChildrensByParentId(idCategory);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item);
        for (Children child : childrens) {
            adapter.add(child.getFirstName() + " " + child.getLastName());
        }
        spinner.setAdapter(adapter);

        //GESTION DE LA DATE DU CALENDRIER A PARTIR DE LA PAGE CALENDARGESTIONNING
        if (getArguments() != null) {
            selectedDateString = getArguments().getString("date");
            String[] date_array = selectedDateString.split("/");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.valueOf(date_array[2]), Integer.valueOf(date_array[1]) - 1, Integer.valueOf(date_array[0]));
            Date date = calendar.getTime();
            calendarView.setDate(date.getTime());
        }

        // Set click listeners for start and end time buttons
        buttonStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get current time
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // Create time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                selectedStartTime = String.format("%02d:%02d", hourOfDay, minute);
                                textViewStartTime.setText("Start Time: " + selectedStartTime);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        buttonEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get current time
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // Create time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                selectedEndTime = String.format("%02d:%02d", hourOfDay, minute);
                                textViewEndTime.setText("End Time: " + selectedEndTime);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        // Set click listener for submit button
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get selected date from calendar view

                // Validate start and end time selections
                if (selectedStartTime == null || selectedEndTime == null) {
                    Toast.makeText(getContext(), "Please select start and end times", Toast.LENGTH_SHORT).show();
                } else {
                    CalendarEvent calendarEvent = new CalendarEvent(selectedDateString, selectedStartTime,selectedEndTime, false, child);
                    CalendarEventDAO calendarEventDAO = new CalendarEventDAO(getContext());
                    calendarEventDAO.addEvent(calendarEvent);
                    Navigation.findNavController(view).navigate(R.id.action_calendar_parents_add_to_calendar_parents_gestionning);
                }
            }
        });

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

            }
        });

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
}