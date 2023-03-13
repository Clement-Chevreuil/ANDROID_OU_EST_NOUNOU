package com.example.ouestnounou.DAO;

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
    private static final String adress = "adress";
    private static final String postalCode = "postal_code";
    private static final String phone = "phone";
    private static final String name = "name";


    public SchoolDAO(Context pContext) {
        super(pContext);
    }

    public void add(School school) {

        ContentValues values = new ContentValues();

        values.put(name, school.getName());
        values.put(mail, school.getMail());
        values.put(city, school.getCity());
        values.put(country, school.getCountry());
        values.put(adress, school.getAdress());
        values.put(postalCode, school.getPostal_code());
        values.put(phone, school.getPhone());

        mDb.insert(nameTableSchool, null, values);
    }

    public void update(School school) {
        ContentValues values = new ContentValues();
        values.put(name, school.getName());
        values.put(mail, school.getMail());
        values.put(city, school.getCity());
        values.put(country, school.getCountry());
        values.put(adress, school.getAdress());
        values.put(postalCode, school.getPostal_code());
        values.put(phone, school.getPhone());
        mDb.update(nameTableSchool, values, id  + " = ?", new String[] {String.valueOf(school.getId())});
    }

    public void delete(long id) {
        mDb.delete(nameTableSchool, id + " = ?", new String[] {String.valueOf(id)});
    }

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
