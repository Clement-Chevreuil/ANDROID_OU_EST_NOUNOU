package com.example.ouestnounou.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.ouestnounou.MODEL.Children;
import com.example.ouestnounou.MODEL.Nurse;
import com.example.ouestnounou.MODEL.Parents;

import java.util.ArrayList;
import java.util.Collections;

public class ChildrenDAO extends DAOBase{

    private static final String nameTableChildren = "Children";
    private static final String nameTableParents = "Parents";
    private static final String nameTableNurse = "Nurse";
    private static final String id = "id";
    private static final String id_parents = "id_parents";

    private static final String id_nurse = "id_parents";
    private static final String firstName = "first_name";
    private static final String last_name = "last_name";
    private static final String birth = "birth";
    private static final String sex = "sex";


    public ChildrenDAO(Context pContext) {
        super(pContext);
    }

    public void add(Children children) {

        open();
        ContentValues values = new ContentValues();

        values.put(firstName, children.getFist_name());
        values.put(last_name, children.getLast_name());
        values.put(birth, String.valueOf(children.getBirth()));
        values.put(sex, children.getSex());
        values.put(id_parents, children.getParents().getId());

        mDb.insert(nameTableChildren, null, values);
        close();
    }

    public void update(Children children) {
        open();
        ContentValues values = new ContentValues();
        values.put(firstName, children.getFist_name());
        values.put(last_name, children.getLast_name());
        values.put(birth, String.valueOf(children.getBirth()));
        values.put(sex, children.getSex());
        values.put(id_parents, children.getParents().getId());
        if(children.getNurse().getId() == -1){
            values.put(id_nurse, (Integer) null);
        }
        else{
            values.put(id_nurse, children.getNurse().getId());
        }

        values.put("nurse_accepted", children.getNurse_accepted());
        mDb.update(nameTableChildren, values, id  + " = ?", new String[] {String.valueOf(children.getId())});
        close();
    }

    public void delete(long id_children) {
        open();
        mDb.delete(nameTableChildren, id + " = ?", new String[] {String.valueOf(id_children)});
        close();
    }

    public ArrayList<Children> getChildrens() {
        ArrayList<Children> allChildren = new ArrayList<Children>();

        this.open();
        //Cursor unCurseur = mDb.rawQuery("SELECT * FROM " + nameTableChildren + " c JOIN " + nameTableParents + " p ON c.id = p.id;", null);
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM " + nameTableChildren + " c JOIN " + nameTableParents + " p ON c." + id_parents + " = p." + id + " JOIN " + nameTableNurse + " n ON p." + id_nurse + " = n." + id + ";", null);
        if (unCurseur.moveToFirst()) {
            do {
                Children children = new Children();
                children.setId(unCurseur.getInt(unCurseur.getColumnIndex(id)));
                children.setFist_name(unCurseur.getString(unCurseur.getColumnIndex(firstName)));
                children.setLast_name(unCurseur.getString(unCurseur.getColumnIndex(last_name)));
                children.setBirth(unCurseur.getString(unCurseur.getColumnIndex(birth)));
                children.setSex(unCurseur.getString(unCurseur.getColumnIndex(sex)));

                Parents parents = new Parents();
                parents.setId(unCurseur.getInt(unCurseur.getColumnIndex("p.id")));
                parents.setFist_name(unCurseur.getString(unCurseur.getColumnIndex("p.first_name")));
                parents.setLast_name(unCurseur.getString(unCurseur.getColumnIndex("p.last_name")));
                parents.setBirth(unCurseur.getString(unCurseur.getColumnIndex("p.birth")));
                parents.setCity(unCurseur.getString(unCurseur.getColumnIndex("p.city")));
                parents.setCountry(unCurseur.getString(unCurseur.getColumnIndex("p.country")));
                parents.setAdress(unCurseur.getString(unCurseur.getColumnIndex("p.adress")));
                parents.setPostal_code(unCurseur.getString(unCurseur.getColumnIndex("p.postal_code")));
                parents.setMail(unCurseur.getString(unCurseur.getColumnIndex("p.mail")));
                parents.setPhone(unCurseur.getString(unCurseur.getColumnIndex("p.phone")));
                parents.setSex(unCurseur.getString(unCurseur.getColumnIndex("p.sex")));

                Nurse nurse = new Nurse();
                nurse.setId(unCurseur.getInt(unCurseur.getColumnIndex("n.id")));
                nurse.setFist_name(unCurseur.getString(unCurseur.getColumnIndex("n.first_name")));
                nurse.setLast_name(unCurseur.getString(unCurseur.getColumnIndex("n.last_name")));
                nurse.setBirth(unCurseur.getString(unCurseur.getColumnIndex("n.birth")));
                nurse.setCity(unCurseur.getString(unCurseur.getColumnIndex("n.city")));
                nurse.setCountry(unCurseur.getString(unCurseur.getColumnIndex("n.country")));
                nurse.setAdress(unCurseur.getString(unCurseur.getColumnIndex("n.adress")));
                nurse.setPostal_code(unCurseur.getString(unCurseur.getColumnIndex("n.postal_code")));
                nurse.setMail(unCurseur.getString(unCurseur.getColumnIndex("n.mail")));
                nurse.setPhone(unCurseur.getString(unCurseur.getColumnIndex("n.phone")));
                nurse.setSex(unCurseur.getString(unCurseur.getColumnIndex("n.sex")));

                children.setNurse(nurse);
                children.setParents(parents);
                allChildren.add(children);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(allChildren);
        }
        else
        {
            this.close();
            return null;
        }
        this.close();
        return allChildren;

    }

    public Children getChildrenByID(int id) {
        Children children = null;

        this.open();
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM " + nameTableChildren + " c JOIN " + nameTableParents + " p ON c." + id_parents + " = p." + id + " JOIN " + nameTableNurse + " n ON p." + id_nurse + " = n." + id + " WHERE c." + id + " = " + id + ";", null);
        if (unCurseur.moveToFirst()) {
            children = new Children();
            children.setId(id);
            children.setFist_name(unCurseur.getString(unCurseur.getColumnIndex(firstName)));
            children.setLast_name(unCurseur.getString(unCurseur.getColumnIndex(last_name)));
            children.setBirth(unCurseur.getString(unCurseur.getColumnIndex(birth)));
            children.setSex(unCurseur.getString(unCurseur.getColumnIndex(sex)));

            Parents parents = new Parents();
            parents.setId(unCurseur.getInt(unCurseur.getColumnIndex("p.id")));
            parents.setFist_name(unCurseur.getString(unCurseur.getColumnIndex("p.first_name")));
            parents.setLast_name(unCurseur.getString(unCurseur.getColumnIndex("p.last_name")));
            parents.setBirth(unCurseur.getString(unCurseur.getColumnIndex("p.birth")));
            parents.setCity(unCurseur.getString(unCurseur.getColumnIndex("p.city")));
            parents.setCountry(unCurseur.getString(unCurseur.getColumnIndex("p.country")));
            parents.setAdress(unCurseur.getString(unCurseur.getColumnIndex("p.adress")));
            parents.setPostal_code(unCurseur.getString(unCurseur.getColumnIndex("p.postal_code")));
            parents.setMail(unCurseur.getString(unCurseur.getColumnIndex("p.mail")));
            parents.setPhone(unCurseur.getString(unCurseur.getColumnIndex("p.phone")));
            parents.setSex(unCurseur.getString(unCurseur.getColumnIndex("p.sex")));

            Nurse nurse = new Nurse();
            nurse.setId(unCurseur.getInt(unCurseur.getColumnIndex("n.id")));
            nurse.setFist_name(unCurseur.getString(unCurseur.getColumnIndex("n.first_name")));
            nurse.setLast_name(unCurseur.getString(unCurseur.getColumnIndex("n.last_name")));
            nurse.setBirth(unCurseur.getString(unCurseur.getColumnIndex("n.birth")));
            nurse.setCity(unCurseur.getString(unCurseur.getColumnIndex("n.city")));
            nurse.setCountry(unCurseur.getString(unCurseur.getColumnIndex("n.country")));
            nurse.setAdress(unCurseur.getString(unCurseur.getColumnIndex("n.adress")));
            nurse.setPostal_code(unCurseur.getString(unCurseur.getColumnIndex("n.postal_code")));
            nurse.setMail(unCurseur.getString(unCurseur.getColumnIndex("n.mail")));
            nurse.setPhone(unCurseur.getString(unCurseur.getColumnIndex("n.phone")));
            nurse.setSex(unCurseur.getString(unCurseur.getColumnIndex("n.sex")));

            children.setNurse(nurse);
            children.setParents(parents);
        }
        this.close();
        return children;
    }

    public Children getChildrenByIDEasy(int id) {
        Children children = null;

        this.open();
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM " + nameTableChildren + " WHERE id = " + id + ";", null);
        if (unCurseur.moveToFirst()) {
            children = new Children();
            children.setId(id);
            children.setFist_name(unCurseur.getString(unCurseur.getColumnIndex(firstName)));
            children.setLast_name(unCurseur.getString(unCurseur.getColumnIndex(last_name)));
            children.setBirth(unCurseur.getString(unCurseur.getColumnIndex(birth)));
            children.setSex(unCurseur.getString(unCurseur.getColumnIndex(sex)));

        }
        this.close();
        return children;
    }

    public Children getChildrenByNameEasy(String firstName, String lastName) {
        Children children = null;

        this.open();
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + nameTableChildren + " WHERE " + this.firstName + " = ? AND " + this.last_name + " = ?;", new String[] { firstName, lastName });
        if (cursor.moveToFirst()) {
            children = new Children();
            children.setId(cursor.getInt(cursor.getColumnIndex(id)));
            children.setFist_name(cursor.getString(cursor.getColumnIndex(this.firstName)));
            children.setLast_name(cursor.getString(cursor.getColumnIndex(this.last_name)));
            children.setBirth(cursor.getString(cursor.getColumnIndex(birth)));
            children.setSex(cursor.getString(cursor.getColumnIndex(sex)));
        }
        cursor.close();
        this.close();
        return children;
    }

    public ArrayList<Children> getChildrensByParentId(int parentId) {
        ArrayList<Children> allChildren = new ArrayList<>();

        open();
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + nameTableChildren + " c " +
                        "WHERE c." + id_parents + " = ?;",
                new String[]{String.valueOf(parentId)});

        if (cursor.moveToFirst()) {
            do {
                Children children = new Children();
                children.setId(cursor.getInt(cursor.getColumnIndex("id")));
                children.setFist_name(cursor.getString(cursor.getColumnIndex("first_name")));
                children.setLast_name(cursor.getString(cursor.getColumnIndex("last_name")));
                children.setBirth(cursor.getString(cursor.getColumnIndex("birth")));
                children.setSex(cursor.getString(cursor.getColumnIndex("sex")));

                allChildren.add(children);
            } while (cursor.moveToNext());
            Collections.shuffle(allChildren);
        } else {
            close();
            return null;
        }
        close();
        return allChildren;
    }

    public ArrayList<Children> getChildrensByNurseId(int nurseId) {
        ArrayList<Children> allChildren = new ArrayList<>();

        open();
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + nameTableChildren + " c " +
                        "WHERE c." + id_nurse + " = ? AND c.nurse_accepted = 1;",
                new String[]{String.valueOf(nurseId)});

        if (cursor.moveToFirst()) {
            do {
                Children children = new Children();
                children.setId(cursor.getInt(cursor.getColumnIndex("id")));
                children.setFist_name(cursor.getString(cursor.getColumnIndex("first_name")));
                children.setLast_name(cursor.getString(cursor.getColumnIndex("last_name")));
                children.setBirth(cursor.getString(cursor.getColumnIndex("birth")));
                children.setSex(cursor.getString(cursor.getColumnIndex("sex")));

                Parents parents = new Parents();

                parents.setId(cursor.getInt(cursor.getColumnIndex("id_parents")));
                children.setParents(parents);

                allChildren.add(children);
            } while (cursor.moveToNext());
            Collections.shuffle(allChildren);
        } else {
            close();
            return null;
        }
        close();
        return allChildren;
    }


    public ArrayList<Children> getChildrensByNurseIdWaiting(int nurseId) {
        ArrayList<Children> allChildren = new ArrayList<>();

        open();
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + nameTableChildren + " c " +
                        "WHERE c." + id_nurse + " = ? AND c.nurse_accepted IS NULL;",
                new String[]{String.valueOf(nurseId)});

        if (cursor.moveToFirst()) {
            do {
                Children children = new Children();
                children.setId(cursor.getInt(cursor.getColumnIndex("id")));
                children.setFist_name(cursor.getString(cursor.getColumnIndex("first_name")));
                children.setLast_name(cursor.getString(cursor.getColumnIndex("last_name")));
                children.setBirth(cursor.getString(cursor.getColumnIndex("birth")));
                children.setSex(cursor.getString(cursor.getColumnIndex("sex")));

                Parents parents = new Parents();

                parents.setId(cursor.getInt(cursor.getColumnIndex("id_parents")));
                children.setParents(parents);

                allChildren.add(children);
            } while (cursor.moveToNext());
            Collections.shuffle(allChildren);
        } else {
            close();
            return null;
        }
        close();
        return allChildren;
    }



}
