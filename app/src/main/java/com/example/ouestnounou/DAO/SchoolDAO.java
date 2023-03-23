package com.example.ouestnounou.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.ouestnounou.MODEL.School;

import java.util.ArrayList;
import java.util.Collections;

public class SchoolDAO extends DAOBase{

    private static final String nameTableSchool = "School";
    private static final String id = "id";
    private static final String mail = "mail";
    private static final String city = "city";
    private static final String country = "country";
    private static final String address = "address";
    private static final String postalCode = "postal_code";
    private static final String phone = "phone";
    private static final String name = "name";


    public SchoolDAO(Context pContext) {
        super(pContext);
    }

    public void add(School school) {

        open();
        ContentValues values = new ContentValues();

        values.put(name, school.getName());
        values.put(mail, school.getMail());
        values.put(city, school.getCity());
        values.put(country, school.getCountry());
        values.put(address, school.getAdress());
        values.put(postalCode, school.getPostal_code());
        values.put(phone, school.getPhone().toString());

        mDb.insert(nameTableSchool, null, values);
        close();
    }

    public void update(School school) {
        open();
        ContentValues values = new ContentValues();
        values.put(name, school.getName());
        values.put(mail, school.getMail());
        values.put(city, school.getCity());
        values.put(country, school.getCountry());
        values.put(address, school.getAdress());
        values.put(postalCode, school.getPostal_code());
        values.put(phone, school.getPhone().toString());
        mDb.update(nameTableSchool, values, id  + " = ?", new String[] {String.valueOf(school.getId())});
        close();
    }

    public void delete(long id) {
        open();
        mDb.delete(nameTableSchool, id + " = ?", new String[] {String.valueOf(id)});
        close();
    }

    @SuppressLint("Range")
    public ArrayList<School> getSchools() {
        ArrayList<School> allSchool = new ArrayList<School>();

        this.open();
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM School;", null);
        if (unCurseur.moveToFirst()) {
            do {
                School school = new School();
                school.setId(unCurseur.getInt(unCurseur.getColumnIndex(id)));
                school.setMail(unCurseur.getString(unCurseur.getColumnIndex(mail)));
                allSchool.add(school);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(allSchool);
        }
        else
        {
            this.close();
            return null;
        }
        this.close();
        return allSchool;

    }
}
