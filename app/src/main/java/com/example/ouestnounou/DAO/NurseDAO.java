package com.example.ouestnounou.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.ouestnounou.MODEL.Nurse;

import java.util.ArrayList;
import java.util.Collections;

public class NurseDAO extends DAOBase{

    private static final String nameTableNurse = "Nurse";
    private static final String id = "id";
    private static final String firstName = "first_name";
    private static final String lastName = "last_Name";
    private static final String birth = "birth";
    private static final String mail = "mail";
    private static final String password = "password";
    private static final String city = "city";
    private static final String country = "country";
    private static final String address = "address";
    private static final String postalCode = "postal_code";
    private static final String phone = "phone";
    private static final String sex = "sex";
    private static final String ageMin = "age_min";
    private static final String ageMax = "age_max";
    private static final String nbChildren = "nb_children";

    public NurseDAO(Context pContext) {
        super(pContext);
    }
    public void add(Nurse nurse) {

        open();
        ContentValues values = new ContentValues();

        values.put(firstName, nurse.getFirstName());
        values.put(lastName, nurse.getLastName());
        values.put(birth, String.valueOf(nurse.getBirth()));
        values.put(mail, nurse.getMail());
        values.put(password, nurse.getPassword());
        values.put(city, nurse.getCity());
        values.put(country, nurse.getCountry());
        values.put(address, nurse.getaddress());
        values.put(postalCode, nurse.getPostalCode());
        values.put(phone, nurse.getPhone());
        values.put(sex, nurse.getSex());
        values.put(ageMin, nurse.getAgeMin());
        values.put(ageMax, nurse.getAgeMax());
        values.put(nbChildren, nurse.getNbChildren());

        mDb.insert(nameTableNurse, null, values);
        close();
    }
    public void update(Nurse nurse) {
        open();
        ContentValues values = new ContentValues();
        values.put(firstName, nurse.getFirstName());
        values.put(lastName, nurse.getLastName());
        values.put(birth, String.valueOf(nurse.getBirth()));
        values.put(mail, nurse.getMail());
        values.put(password, nurse.getPassword());
        values.put(city, nurse.getCity());
        values.put(country, nurse.getCountry());
        values.put(address, nurse.getaddress());
        values.put(postalCode, nurse.getPostalCode());
        values.put(phone, nurse.getPhone());
        values.put(sex, nurse.getSex());
        values.put(ageMin, nurse.getAgeMin());
        values.put(ageMax, nurse.getAgeMax());
        values.put(nbChildren, nurse.getNbChildren());
        mDb.update(nameTableNurse, values, id  + " = ?", new String[] {String.valueOf(nurse.getId())});
        close();
    }

    //DELETE CHILDREN WITH NURSE_ID
    public void delete(long id) {
        open();
        mDb.delete(nameTableNurse, id + " = ?", new String[] {String.valueOf(id)});
        close();
    }
    public ArrayList<Nurse> getNurses() {
        ArrayList<Nurse> allNurse = new ArrayList<Nurse>();

        this.open();
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM Nurse;", null);
        if (unCurseur.moveToFirst()) {
            do {
                Nurse nurse = new Nurse();
                nurse.setId(unCurseur.getInt(unCurseur.getColumnIndex(id)));
                nurse.setMail(unCurseur.getString(unCurseur.getColumnIndex(mail)));
                nurse.setFirstName(unCurseur.getString(unCurseur.getColumnIndex(firstName)));
                nurse.setLastName(unCurseur.getString(unCurseur.getColumnIndex(lastName)));
                nurse.setBirth(unCurseur.getString(unCurseur.getColumnIndex(birth)));
                nurse.setPassword(unCurseur.getString(unCurseur.getColumnIndex(password)));
                nurse.setCity(unCurseur.getString(unCurseur.getColumnIndex(city)));
                nurse.setCountry(unCurseur.getString(unCurseur.getColumnIndex(country)));
                nurse.setaddress(unCurseur.getString(unCurseur.getColumnIndex(address)));
                nurse.setPostalCode(unCurseur.getString(unCurseur.getColumnIndex(postalCode)));
                nurse.setPhone(unCurseur.getString(unCurseur.getColumnIndex(phone)));
                nurse.setSex(unCurseur.getString(unCurseur.getColumnIndex(sex)));
                nurse.setAgeMin(unCurseur.getInt(unCurseur.getColumnIndex(ageMin)));
                nurse.setAgeMax(unCurseur.getInt(unCurseur.getColumnIndex(ageMax)));
                nurse.setNbChildren(unCurseur.getInt(unCurseur.getColumnIndex(nbChildren)));
                allNurse.add(nurse);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(allNurse);
        }
        else
        {
            this.close();
            return null;
        }
        this.close();
        return allNurse;

    }
    public ArrayList<Nurse> getNursesNotComplete() {
        ArrayList<Nurse> allNurse = new ArrayList<Nurse>();

        this.open();
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM Nurse WHERE (SELECT COUNT(*) FROM Children WHERE Children.id_nurse = Nurse.id) != Nurse.nb_children;", null);
        if (unCurseur.moveToFirst()) {
            do {
                Nurse nurse = new Nurse();
                nurse.setId(unCurseur.getInt(unCurseur.getColumnIndex(id)));
                nurse.setMail(unCurseur.getString(unCurseur.getColumnIndex(mail)));
                nurse.setFirstName(unCurseur.getString(unCurseur.getColumnIndex(firstName)));
                nurse.setLastName(unCurseur.getString(unCurseur.getColumnIndex(lastName)));
                nurse.setBirth(unCurseur.getString(unCurseur.getColumnIndex(birth)));
                nurse.setPassword(unCurseur.getString(unCurseur.getColumnIndex(password)));
                nurse.setCity(unCurseur.getString(unCurseur.getColumnIndex(city)));
                nurse.setCountry(unCurseur.getString(unCurseur.getColumnIndex(country)));
                nurse.setaddress(unCurseur.getString(unCurseur.getColumnIndex(address)));
                nurse.setPostalCode(unCurseur.getString(unCurseur.getColumnIndex(postalCode)));
                nurse.setPhone(unCurseur.getString(unCurseur.getColumnIndex(phone)));
                nurse.setSex(unCurseur.getString(unCurseur.getColumnIndex(sex)));
                nurse.setAgeMin(unCurseur.getInt(unCurseur.getColumnIndex(ageMin)));
                nurse.setAgeMax(unCurseur.getInt(unCurseur.getColumnIndex(ageMax)));
                nurse.setNbChildren(unCurseur.getInt(unCurseur.getColumnIndex(nbChildren)));
                allNurse.add(nurse);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(allNurse);
        }
        else
        {
            this.close();
            return null;
        }
        this.close();
        return allNurse;

    }
    public Nurse getNurseById(int idNurse) {
        Nurse nurse = null;

        this.open();
        Cursor cursor = mDb.rawQuery("SELECT * FROM Nurse WHERE id = ?;", new String[] { String.valueOf(idNurse) });

        if (cursor.moveToFirst()) {
            nurse = new Nurse();
            nurse.setId(cursor.getInt(cursor.getColumnIndex(id)));
            nurse.setMail(cursor.getString(cursor.getColumnIndex(mail)));
            nurse.setFirstName(cursor.getString(cursor.getColumnIndex(firstName)));
            nurse.setLastName(cursor.getString(cursor.getColumnIndex(lastName)));
            nurse.setBirth(cursor.getString(cursor.getColumnIndex(birth)));
            nurse.setPassword(cursor.getString(cursor.getColumnIndex(password)));
            nurse.setCity(cursor.getString(cursor.getColumnIndex(city)));
            nurse.setCountry(cursor.getString(cursor.getColumnIndex(country)));
            nurse.setaddress(cursor.getString(cursor.getColumnIndex(address)));
            nurse.setPostalCode(cursor.getString(cursor.getColumnIndex(postalCode)));
            nurse.setPhone(cursor.getString(cursor.getColumnIndex(phone)));
            nurse.setSex(cursor.getString(cursor.getColumnIndex(sex)));
            nurse.setAgeMin(cursor.getInt(cursor.getColumnIndex(ageMin)));
            nurse.setAgeMax(cursor.getInt(cursor.getColumnIndex(ageMax)));
            nurse.setNbChildren(cursor.getInt(cursor.getColumnIndex(nbChildren)));
        }

        cursor.close();
        this.close();
        return nurse;
    }
    public Nurse connexion(String mail_data, String password_data){

        this.open();
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM Nurse WHERE mail = '" + mail_data + "' AND password = '" + password_data + "' ;", null);
        Nurse user = new Nurse();
        int nbrRec = unCurseur.getCount();
        if (nbrRec != 0)
        {
            unCurseur.moveToFirst();
            user.setId(unCurseur.getInt(unCurseur.getColumnIndex(id)));
            user.setMail(unCurseur.getString(unCurseur.getColumnIndex(mail)));
            user.setPassword(unCurseur.getString(unCurseur.getColumnIndex(password)));
        }
        else
        {
            this.close();
            user = null;
            return user;
        }

        this.close();
        return user;
    }

}
