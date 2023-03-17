package com.example.ouestnounou.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.ouestnounou.MODEL.Parents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParentsDAO extends DAOBase{

    private static final String nameTableParents = "Parents";
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
    

    public ParentsDAO(Context pContext) {
        super(pContext);
    }

    public void add(Parents parents) {

        open();
        ContentValues values = new ContentValues();

        values.put(firstName, parents.getFist_name());
        values.put(last_name, parents.getLast_name());
        values.put(birth, String.valueOf(parents.getBirth()));
        values.put(mail, parents.getMail());
        values.put(password, parents.getPassword());
        values.put(city, parents.getCity());
        values.put(country, parents.getCountry());
        values.put(adress, parents.getAdress());
        values.put(postalCode, parents.getPostal_code());
        values.put(phone, parents.getPhone());
        values.put(sex, parents.getSex());

        mDb.insert(nameTableParents, null, values);
        close();
    }

    public void update(Parents parents) {
        open();
        ContentValues values = new ContentValues();
        values.put(firstName, parents.getFist_name());
        values.put(last_name, parents.getLast_name());
        values.put(birth, String.valueOf(parents.getBirth()));
        values.put(mail, parents.getMail());
        values.put(password, parents.getPassword());
        values.put(city, parents.getCity());
        values.put(country, parents.getCountry());
        values.put(adress, parents.getAdress());
        values.put(postalCode, parents.getPostal_code());
        values.put(phone, parents.getPhone());
        values.put(sex, parents.getSex());
        mDb.update(nameTableParents, values, id  + " = ?", new String[] {String.valueOf(parents.getId())});
        close();
    }

    public void delete(long id) {
        open();
        mDb.delete(nameTableParents, id + " = ?", new String[] {String.valueOf(id)});
        close();
    }

    public ArrayList<Parents> getParents() {
        ArrayList<Parents> allParents = new ArrayList<Parents>();

        this.open();
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM Parents;", null);
        if (unCurseur.moveToFirst()) {
            do {
                Parents parents = new Parents();
                parents.setId(unCurseur.getInt(unCurseur.getColumnIndex(id)));
                parents.setMail(unCurseur.getString(unCurseur.getColumnIndex(mail)));
                allParents.add(parents);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(allParents);
        }
        else
        {
            this.close();
            return null;
        }
        this.close();
        return allParents;

    }

    public Parents getParent(int id_parents){

        this.open();
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM Parents WHERE id = '" + id_parents + "' ;", null);
        Parents parents = new Parents();
        int nbrRec = unCurseur.getCount();
        if (nbrRec != 0)
        {
            unCurseur.moveToFirst();
            parents.setId(unCurseur.getInt(unCurseur.getColumnIndex(id)));
            parents.setFist_name(unCurseur.getString(unCurseur.getColumnIndex(firstName)));
            parents.setLast_name(unCurseur.getString(unCurseur.getColumnIndex(last_name)));
            parents.setBirth(unCurseur.getString(unCurseur.getColumnIndex(birth)));
            parents.setAdress(unCurseur.getString(unCurseur.getColumnIndex(adress)));
            parents.setCountry(unCurseur.getString(unCurseur.getColumnIndex(country)));
            parents.setCity(unCurseur.getString(unCurseur.getColumnIndex(city)));
            parents.setSex(unCurseur.getString(unCurseur.getColumnIndex(sex)));
            parents.setPostal_code(unCurseur.getString(unCurseur.getColumnIndex(postalCode)));
            parents.setPhone(unCurseur.getString(unCurseur.getColumnIndex(phone)));
            parents.setMail(unCurseur.getString(unCurseur.getColumnIndex(mail)));
            parents.setPassword(unCurseur.getString(unCurseur.getColumnIndex(password)));
        }
        else
        {
            this.close();
            parents = null;
            return parents;
        }

        this.close();
        return parents;
    }

    public Parents connexion(String mail_data, String password_data){

        this.open();
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM Parents WHERE mail = '" + mail_data + "' AND password = '" + password_data + "' ;", null);
        Parents user = new Parents();
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
