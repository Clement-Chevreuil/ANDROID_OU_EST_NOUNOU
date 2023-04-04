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
    private static final String last_name = "last_name";
    private static final String birth = "birth";
    private static final String mail = "mail";
    private static final String password = "password";
    private static final String city = "city";
    private static final String country = "country";
    private static final String adress = "adress";
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

        values.put(firstName, nurse.getFist_name());
        values.put(last_name, nurse.getLast_name());
        values.put(birth, String.valueOf(nurse.getBirth()));
        values.put(mail, nurse.getMail());
        values.put(password, nurse.getPassword());
        values.put(city, nurse.getCity());
        values.put(country, nurse.getCountry());
        values.put(adress, nurse.getAdress());
        values.put(postalCode, nurse.getPostal_code());
        values.put(phone, nurse.getPhone());
        values.put(sex, nurse.getSex());
        values.put(ageMin, nurse.getAge_min());
        values.put(ageMax, nurse.getAge_max());
        values.put(nbChildren, nurse.getNb_children());

        mDb.insert(nameTableNurse, null, values);
        close();
    }

    public void update(Nurse nurse) {
        open();
        ContentValues values = new ContentValues();
        values.put(firstName, nurse.getFist_name());
        values.put(last_name, nurse.getLast_name());
        values.put(birth, String.valueOf(nurse.getBirth()));
        values.put(mail, nurse.getMail());
        values.put(password, nurse.getPassword());
        values.put(city, nurse.getCity());
        values.put(country, nurse.getCountry());
        values.put(adress, nurse.getAdress());
        values.put(postalCode, nurse.getPostal_code());
        values.put(phone, nurse.getPhone());
        values.put(sex, nurse.getSex());
        values.put(ageMin, nurse.getAge_min());
        values.put(ageMax, nurse.getAge_max());
        values.put(nbChildren, nurse.getNb_children());
        mDb.update(nameTableNurse, values, id  + " = ?", new String[] {String.valueOf(nurse.getId())});
        close();
    }

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
                nurse.setFist_name(unCurseur.getString(unCurseur.getColumnIndex(firstName)));
                nurse.setLast_name(unCurseur.getString(unCurseur.getColumnIndex(last_name)));
                nurse.setBirth(unCurseur.getString(unCurseur.getColumnIndex(birth)));
                nurse.setPassword(unCurseur.getString(unCurseur.getColumnIndex(password)));
                nurse.setCity(unCurseur.getString(unCurseur.getColumnIndex(city)));
                nurse.setCountry(unCurseur.getString(unCurseur.getColumnIndex(country)));
                nurse.setAdress(unCurseur.getString(unCurseur.getColumnIndex(adress)));
                nurse.setPostal_code(unCurseur.getString(unCurseur.getColumnIndex(postalCode)));
                nurse.setPhone(unCurseur.getString(unCurseur.getColumnIndex(phone)));
                nurse.setSex(unCurseur.getString(unCurseur.getColumnIndex(sex)));
                nurse.setAge_min(unCurseur.getInt(unCurseur.getColumnIndex(ageMin)));
                nurse.setAge_max(unCurseur.getInt(unCurseur.getColumnIndex(ageMax)));
                nurse.setNb_children(unCurseur.getInt(unCurseur.getColumnIndex(nbChildren)));
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
                nurse.setFist_name(unCurseur.getString(unCurseur.getColumnIndex(firstName)));
                nurse.setLast_name(unCurseur.getString(unCurseur.getColumnIndex(last_name)));
                nurse.setBirth(unCurseur.getString(unCurseur.getColumnIndex(birth)));
                nurse.setPassword(unCurseur.getString(unCurseur.getColumnIndex(password)));
                nurse.setCity(unCurseur.getString(unCurseur.getColumnIndex(city)));
                nurse.setCountry(unCurseur.getString(unCurseur.getColumnIndex(country)));
                nurse.setAdress(unCurseur.getString(unCurseur.getColumnIndex(adress)));
                nurse.setPostal_code(unCurseur.getString(unCurseur.getColumnIndex(postalCode)));
                nurse.setPhone(unCurseur.getString(unCurseur.getColumnIndex(phone)));
                nurse.setSex(unCurseur.getString(unCurseur.getColumnIndex(sex)));
                nurse.setAge_min(unCurseur.getInt(unCurseur.getColumnIndex(ageMin)));
                nurse.setAge_max(unCurseur.getInt(unCurseur.getColumnIndex(ageMax)));
                nurse.setNb_children(unCurseur.getInt(unCurseur.getColumnIndex(nbChildren)));
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
            nurse.setFist_name(cursor.getString(cursor.getColumnIndex(firstName)));
            nurse.setLast_name(cursor.getString(cursor.getColumnIndex(last_name)));
            nurse.setBirth(cursor.getString(cursor.getColumnIndex(birth)));
            nurse.setPassword(cursor.getString(cursor.getColumnIndex(password)));
            nurse.setCity(cursor.getString(cursor.getColumnIndex(city)));
            nurse.setCountry(cursor.getString(cursor.getColumnIndex(country)));
            nurse.setAdress(cursor.getString(cursor.getColumnIndex(adress)));
            nurse.setPostal_code(cursor.getString(cursor.getColumnIndex(postalCode)));
            nurse.setPhone(cursor.getString(cursor.getColumnIndex(phone)));
            nurse.setSex(cursor.getString(cursor.getColumnIndex(sex)));
            nurse.setAge_min(cursor.getInt(cursor.getColumnIndex(ageMin)));
            nurse.setAge_max(cursor.getInt(cursor.getColumnIndex(ageMax)));
            nurse.setNb_children(cursor.getInt(cursor.getColumnIndex(nbChildren)));
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
