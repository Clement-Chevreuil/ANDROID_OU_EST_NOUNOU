package com.example.ouestnounou.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    //MULTIPLE TABLE
    private static final String mail = "mail";
    private static final String password = "password";
    private static final String id = "id";
    private static final String birth = "birth";
    private static final String city = "city";
    private static final String country = "country";
    private static final String adress = "adress";
    private static final String firstName = "first_name";
    private static final String lastName = "last_name";
    private static final String postalCode = "postal_code";
    private static final String phone = "phone";
    private static final String sex = "sex";


    private static final String nameTableParents = "Parents";

    private static final String nameTableNurse = "Nurse";
    private static final String ageMin = "age_min";
    private static final String ageMax = "age_max";
    private static final String nbChildren = "nb_children";

    private static final String nameTableChildren = "Children";
    private static final String nameTableSchool = "School";
    private static final String name = "name";

    private static final String nameTableCalendarEvent = "CalendarEvent";
    private static final String datePropose = "datePropose";
    private static final String accepted = "accepted";
    private static final String startTime = "startTime";
    private static final String endTime = "endTime";


    private static final String reqCreateNurse =
            "CREATE TABLE " + nameTableNurse + " ("
                    + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + firstName + " TEXT,"
                    + lastName + " TEXT,"
                    + phone + " TEXT,"
                    + birth + " TEXT,"
                    + sex + " TEXT,"
                    + city + " TEXT,"
                    + adress + " TEXT,"
                    + postalCode + " TEXT,"
                    + country + " TEXT,"
                    + ageMin + " INTEGER,"
                    + ageMax + " INTEGER,"
                    + nbChildren + " INTEGER,"
                    + mail + " TEXT,"
                    + password + " TEXT);";
    private static final String reqCreateParents =
            "CREATE TABLE " + nameTableParents + " ("
                    + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + firstName + " TEXT,"
                    + lastName + " TEXT,"
                    + phone + " TEXT,"
                    + birth + " TEXT,"
                    + sex + " TEXT,"
                    + city + " TEXT,"
                    + adress + " TEXT,"
                    + postalCode + " TEXT,"
                    + country + " TEXT,"
                    + mail + " TEXT,"
                    + password + " TEXT);";
    private static final String reqCreateSchool =
            "CREATE TABLE " + nameTableSchool + " ("
                    + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + name + " TEXT,"
                    + city + " TEXT,"
                    + adress + " TEXT,"
                    + postalCode + " TEXT,"
                    + country + " TEXT,"
                    + phone + " TEXT,"
                    + mail + " TEXT);";
    private static final String reqCreateChildren =
            "CREATE TABLE " + nameTableChildren + " ("
                    + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nurse_accepted" + " INTEGER NULL,"
                    + "id_parents" + " INTEGER,"
                    + "id_nurse" + " INTEGER NULL,"
                    + firstName + " TEXT,"
                    + lastName + " TEXT,"
                    + sex + " TEXT,"
                    + birth + " TEXT);";

    private static final String reqCreateCalendarEvent =
            "CREATE TABLE " + nameTableCalendarEvent + " ("
                    + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "id_children" + " INTEGER,"
                    + accepted + " TEXT NULL,"
                    + datePropose + " TEXT,"
                    + startTime + " TEXT,"
                    + endTime + " TEXT);";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(reqCreateChildren);
        db.execSQL(reqCreateSchool);
        db.execSQL(reqCreateParents);
        db.execSQL(reqCreateNurse);
        db.execSQL(reqCreateCalendarEvent);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String reqSuppNurse = "DROP TABLE IF EXISTS " + nameTableNurse + reqCreateNurse;
        String reqSuppParents = "DROP TABLE IF EXISTS " + nameTableParents + reqCreateParents;
        String reqSuppSchool = "DROP TABLE IF EXISTS " + nameTableSchool + reqCreateSchool;
        String reqSuppChildren = "DROP TABLE IF EXISTS " + nameTableChildren + reqCreateChildren;
        String reqSuppCalendarEvent = "DROP TABLE IF EXISTS " + nameTableCalendarEvent + reqCreateCalendarEvent;

        db.execSQL(reqSuppNurse);
        db.execSQL(reqSuppParents);
        db.execSQL(reqSuppSchool);
        db.execSQL(reqSuppChildren);
        db.execSQL(reqSuppCalendarEvent);

        onCreate(db);
    }
}
