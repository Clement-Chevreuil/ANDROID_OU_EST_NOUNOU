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

    private static final String TABLE_NAME = "CalendarEvent";
    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "datePropose";
    private static final String KEY_START_TIME = "startTime";
    private static final String KEY_END_TIME = "endTime";
    private static final String KEY_ACCEPTED = "accepted";

    public CalendarEventDAO(Context context) {
        super(context);
    }
    

    public void addEvent(CalendarEvent event) {
        open();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, event.getDate());
        values.put(KEY_START_TIME, event.getStartTime());
        values.put(KEY_END_TIME, event.getEndTime());
        values.put("id_children", event.getChildren().getId());
        mDb.insert(TABLE_NAME, null, values);
        close();
    }

/*    public void updateEvent(CalendarEvent event) {
        open();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, event.getDate());
        values.put(KEY_START_TIME, event.getStartTime());
        values.put(KEY_END_TIME, event.getEndTime());
        values.put(KEY_ACCEPTED, event.isAccepted() ? 1 : 0);
        mDb.update(TABLE_NAME, values, KEY_ID + " = ?", new String[] { String.valueOf(event.getId()) });
        close();
    }*/

    public void deleteEvent(CalendarEvent event) {
        open();
        mDb.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(event.getId()) });
        close();
    }


    public List<CalendarEvent> getAllEvents() {
        List<CalendarEvent> events = new ArrayList<CalendarEvent>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        open();
        Cursor cursor = mDb.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                CalendarEvent event = new CalendarEvent();
                event.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                event.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                event.setStartTime(cursor.getString(cursor.getColumnIndex(KEY_START_TIME)));
                event.setEndTime(cursor.getString(cursor.getColumnIndex(KEY_END_TIME)));
                event.setAccepted(cursor.getInt(cursor.getColumnIndex(KEY_ACCEPTED)) == 1);
                events.add(event);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return events;
    }

    public List<CalendarEvent> getAllEventsByDate(String date) {
        List<CalendarEvent> events = new ArrayList<CalendarEvent>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_DATE + " = ?";
        open();
        Cursor cursor = mDb.rawQuery(selectQuery, new String[] { date });
        if (cursor.moveToFirst()) {
            do {
                CalendarEvent event = new CalendarEvent();
                event.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                event.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                event.setStartTime(cursor.getString(cursor.getColumnIndex(KEY_START_TIME)));
                event.setEndTime(cursor.getString(cursor.getColumnIndex(KEY_END_TIME)));
                event.setAccepted(cursor.getInt(cursor.getColumnIndex(KEY_ACCEPTED)) == 1);

                Children child = new Children();
                child.setId(cursor.getInt(cursor.getColumnIndex("id_children")));

                event.setChildren(child);

                events.add(event);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return events;
    }

    public List<CalendarEvent> getAllEventsByDateAndChild(String date, Integer id_children) {
        List<CalendarEvent> events = new ArrayList<CalendarEvent>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_DATE + " = ?";
        if (id_children != -1) {
            selectQuery += " AND " + "id_children" + " = ?";
        }
        open();
        Cursor cursor;
        if (id_children != -1 ) {
            cursor = mDb.rawQuery(selectQuery, new String[] { date, String.valueOf(id_children) });
        } else {
            cursor = mDb.rawQuery(selectQuery, new String[] { date });
        }
        if (cursor.moveToFirst()) {
            do {
                CalendarEvent event = new CalendarEvent();
                event.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                event.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                event.setStartTime(cursor.getString(cursor.getColumnIndex(KEY_START_TIME)));
                event.setEndTime(cursor.getString(cursor.getColumnIndex(KEY_END_TIME)));
                event.setAccepted(cursor.getInt(cursor.getColumnIndex(KEY_ACCEPTED)) == 1);

                Children child = new Children();
                child.setId(cursor.getInt(cursor.getColumnIndex("id_children")));
                event.setChildren(child);

                events.add(event);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return events;
    }

    public ArrayList<CalendarEvent> getAllCalendarGestions() {
        ArrayList<CalendarEvent> allCalendarGestions = new ArrayList<>();

        open();
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                CalendarEvent calendarEvent = new CalendarEvent();
                calendarEvent.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                calendarEvent.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                calendarEvent.setStartTime(cursor.getString(cursor.getColumnIndex(KEY_START_TIME)));
                calendarEvent.setEndTime(cursor.getString(cursor.getColumnIndex(KEY_END_TIME)));
                calendarEvent.setAccepted(cursor.getInt(cursor.getColumnIndex(KEY_ACCEPTED)) > 0);
                allCalendarGestions.add(calendarEvent);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return allCalendarGestions;
    }

    public ArrayList<CalendarEvent> getAllCalendarGestionsByDate(String date) {
        ArrayList<CalendarEvent> allCalendarGestions = new ArrayList<>();

        open();
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_DATE + " = ?", new String[]{date});

        if (cursor.moveToFirst()) {
            do {
                CalendarEvent calendarEvent = new CalendarEvent();
                calendarEvent.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                calendarEvent.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                calendarEvent.setStartTime(cursor.getString(cursor.getColumnIndex(KEY_START_TIME)));
                calendarEvent.setEndTime(cursor.getString(cursor.getColumnIndex(KEY_END_TIME)));
                calendarEvent.setAccepted(cursor.getInt(cursor.getColumnIndex(KEY_ACCEPTED)) > 0);
                allCalendarGestions.add(calendarEvent);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return allCalendarGestions;
    }

    public void updateCalendarGestion(CalendarEvent calendarEvent) {
        open();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, calendarEvent.getDate());
        values.put(KEY_START_TIME, calendarEvent.getStartTime());
        values.put(KEY_END_TIME, calendarEvent.getEndTime());
        values.put(KEY_ACCEPTED, calendarEvent.isAccepted() ? 1 : 0);

        mDb.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(calendarEvent.getId())});
        close();
    }

    public void deleteCalendarGestion(int id) {
        open();
        mDb.delete(TABLE_NAME, KEY_ID + " = ?", new String[]{String.valueOf(id)});
        close();
    }
}