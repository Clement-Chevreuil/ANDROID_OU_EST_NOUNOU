package com.example.ouestnounou.VISUAL_ACTIVITY_2_PARENTS.CALENDAR;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.ouestnounou.DAO.CalendarEventDAO;
import com.example.ouestnounou.MODEL.CalendarEvent;
import com.example.ouestnounou.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarGestionningAdd extends Fragment {

    private CalendarView calendarView;
    private TextView textViewStartTime, textViewEndTime;
    private Button buttonStartTime, buttonEndTime, buttonSubmit;
    private String selectedDateString, selectedStartTime, selectedEndTime;
    private static final String ARG_DATE = "date";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.e_fragment_calendar_gestion, container, false);

        // Find views
        calendarView = view.findViewById(R.id.calendarView);
        textViewStartTime = view.findViewById(R.id.textViewStartTime);
        textViewEndTime = view.findViewById(R.id.textViewEndTime);
        buttonStartTime = view.findViewById(R.id.buttonStartTime);
        buttonEndTime = view.findViewById(R.id.buttonEndTime);
        buttonSubmit = view.findViewById(R.id.buttonSubmit);

        // Set default values for start and end time text views
        textViewStartTime.setText("Start Time: Not Selected");
        textViewEndTime.setText("End Time: Not Selected");


        if (getArguments() != null) {

            selectedDateString = getArguments().getString(ARG_DATE);
            String[] date_array = selectedDateString.split("/");
            // Create a Calendar instance and set the selected date

            Calendar calendar = Calendar.getInstance();

            Calendar cal = Calendar.getInstance();
            cal.set(Integer.valueOf(date_array[2]), Integer.valueOf(date_array[1]) - 1, Integer.valueOf(date_array[0]));
            Date date = cal.getTime();
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
                    CalendarEvent calendarEvent = new CalendarEvent(selectedDateString, selectedStartTime,selectedEndTime);
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

        return view;
    }
}