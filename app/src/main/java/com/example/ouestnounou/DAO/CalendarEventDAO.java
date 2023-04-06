package com.example.ouestnounou.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ouestnounou.MODEL.CalendarEvent;
import com.example.ouestnounou.MODEL.Children;

import java.util.ArrayList;
import java.util.List;

public class CalendarEventDAO extends DAOBase {

    private static final String nameTableChildren = "CalendarEvent";
    private static final String id = "id";
    private static final String datePropose = "date_propose";
    private static final String startTime = "start_time";
    private static final String endTime = "end_time";
    private static final String accepted = "accepted";
    private static final String idChildren = "idChildren";

    public CalendarEventDAO(Context context) {
        super(context);
    }

    public void addEvent(CalendarEvent event) {
        open();
        ContentValues values = new ContentValues();
        values.put(datePropose, event.getDate());
        values.put(startTime, event.getStartTime());
        values.put(endTime, event.getEndTime());
        values.put(idChildren, event.getChildren().getId());
        mDb.insert(nameTableChildren, null, values);
        close();
    }
    public void updateEvent(CalendarEvent event) {
        open();
        ContentValues values = new ContentValues();
        values.put(datePropose, event.getDate());
        values.put(startTime, event.getStartTime());
        values.put(endTime, event.getEndTime());
        values.put(accepted, event.isAccepted() ? 1 : 0);
        mDb.update(nameTableChildren, values, id + " = ?", new String[] { String.valueOf(event.getId()) });
        close();
    }
    public void deleteEvent(CalendarEvent event) {
        open();
        mDb.delete(nameTableChildren, id + " = ?", new String[] { String.valueOf(event.getId()) });
        close();
    }
    public List<CalendarEvent> getAllEvents() {
        List<CalendarEvent> events = new ArrayList<CalendarEvent>();
        String selectQuery = "SELECT * FROM " + nameTableChildren;
        open();
        Cursor cursor = mDb.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                CalendarEvent event = new CalendarEvent();
                event.setId(cursor.getInt(cursor.getColumnIndex(id)));
                event.setDate(cursor.getString(cursor.getColumnIndex(datePropose)));
                event.setStartTime(cursor.getString(cursor.getColumnIndex(startTime)));
                event.setEndTime(cursor.getString(cursor.getColumnIndex(endTime)));
                event.setAccepted(cursor.getInt(cursor.getColumnIndex(accepted)) == 1);

                Children child = new Children();
                child.setId(cursor.getInt(cursor.getColumnIndex(idChildren)));
                event.setChildren(child);

                events.add(event);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return events;
    }
    public List<CalendarEvent> getAllEventsByDate(String date) {
        List<CalendarEvent> events = new ArrayList<CalendarEvent>();
        String selectQuery = "SELECT * FROM " + nameTableChildren + " WHERE " + datePropose + " = ?";
        open();
        Cursor cursor = mDb.rawQuery(selectQuery, new String[] { date });
        if (cursor.moveToFirst()) {
            do {
                CalendarEvent event = new CalendarEvent();
                event.setId(cursor.getInt(cursor.getColumnIndex(id)));
                event.setDate(cursor.getString(cursor.getColumnIndex(datePropose)));
                event.setStartTime(cursor.getString(cursor.getColumnIndex(startTime)));
                event.setEndTime(cursor.getString(cursor.getColumnIndex(endTime)));
                event.setAccepted(cursor.getInt(cursor.getColumnIndex(accepted)) == 1);

                Children child = new Children();
                child.setId(cursor.getInt(cursor.getColumnIndex(idChildren)));

                event.setChildren(child);

                events.add(event);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return events;
    }
    public List<CalendarEvent> getAllEventsByDateAndChild(String date, Integer idChildrenData) {
        List<CalendarEvent> events = new ArrayList<CalendarEvent>();
        String selectQuery = "SELECT * FROM " + nameTableChildren + " WHERE " + datePropose + " = ?";
        selectQuery += " AND " + idChildrenData + " = ?";

        open();
        Cursor cursor;
        cursor = mDb.rawQuery(selectQuery, new String[] { date, String.valueOf(idChildren) });

        if (cursor.moveToFirst()) {
            do {
                CalendarEvent event = new CalendarEvent();
                event.setId(cursor.getInt(cursor.getColumnIndex(id)));
                event.setDate(cursor.getString(cursor.getColumnIndex(datePropose)));
                event.setStartTime(cursor.getString(cursor.getColumnIndex(startTime)));
                event.setEndTime(cursor.getString(cursor.getColumnIndex(endTime)));
                event.setAccepted(cursor.getInt(cursor.getColumnIndex(accepted)) == 1);

                Children child = new Children();
                child.setId(cursor.getInt(cursor.getColumnIndex(idChildren)));
                event.setChildren(child);

                events.add(event);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return events;
    }
}