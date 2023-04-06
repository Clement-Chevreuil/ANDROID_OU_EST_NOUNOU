package com.example.ouestnounou.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.ouestnounou.MODEL.CalendarEvent;
import com.example.ouestnounou.MODEL.Children;
import com.example.ouestnounou.MODEL.Nurse;
import com.example.ouestnounou.MODEL.Parents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChildrenDAO extends DAOBase{

    private static final String nameTableChildren = "Children";
    private static final String nameTableParents = "Parents";
    private static final String nameTableNurse = "Nurse";
    private static final String id = "id";
    private static final String idParents = "idParents";
    private static final String idNurse = "idNurse";
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

        values.put(firstName, children.getFirstName());
        values.put(last_name, children.getLastName());
        values.put(birth, String.valueOf(children.getBirth()));
        values.put(sex, children.getSex());
        values.put(idParents, children.getParents().getId());

        mDb.insert(nameTableChildren, null, values);
        close();
    }
    public void update(Children children) {
        open();
        ContentValues values = new ContentValues();
        values.put(firstName, children.getFirstName());
        values.put(last_name, children.getLastName());
        values.put(birth, String.valueOf(children.getBirth()));
        values.put(sex, children.getSex());
        if(children.getParents() != null)
        {
            values.put(idParents, children.getParents().getId());
        }

        if(children.getNurse().getId() == -1){
            values.put(idNurse, -1);
        }
        else{
            values.put(idNurse, children.getNurse().getId());
        }

        if(children.getNurseAccepted() >= 1)
        {
            values.put("nurse_accepted", children.getNurseAccepted());
        }

        mDb.update(nameTableChildren, values, id  + " = ?", new String[] {String.valueOf(children.getId())});
        close();
    }
    public void delete(long idChildren) {
        open();
        String selectQuery = "SELECT * FROM CalendarEvent WHERE " + " id_children " + " = ?";
        Cursor cursor;
        cursor = mDb.rawQuery(selectQuery, new String[] { String.valueOf(idChildren) });
        if (cursor.moveToFirst()) {
            do {
                int dataIdChildren = cursor.getInt(cursor.getColumnIndex("id_children"));
                mDb.delete("CalendarEvent",  "id_children" +  " = ?", new String[] { String.valueOf(dataIdChildren) });
            } while (cursor.moveToNext());
        }
        cursor.close();
        mDb.delete(nameTableChildren, id + " = ?", new String[] {String.valueOf(idChildren)});
        close();
    }
    public ArrayList<Children> getChildrens() {
        ArrayList<Children> allChildren = new ArrayList<Children>();

        this.open();
        //Cursor unCurseur = mDb.rawQuery("SELECT * FROM " + nameTableChildren + " c JOIN " + nameTableParents + " p ON c.id = p.id;", null);
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM " + nameTableChildren + " c JOIN " + nameTableParents + " p ON c." + idParents + " = p." + id + " JOIN " + nameTableNurse + " n ON p." + idNurse + " = n." + id + ";", null);
        if (unCurseur.moveToFirst()) {
            do {
                Children children = new Children();
                children.setId(unCurseur.getInt(unCurseur.getColumnIndex(id)));
                children.setFirstName(unCurseur.getString(unCurseur.getColumnIndex(firstName)));
                children.setLastName(unCurseur.getString(unCurseur.getColumnIndex(last_name)));
                children.setBirth(unCurseur.getString(unCurseur.getColumnIndex(birth)));
                children.setSex(unCurseur.getString(unCurseur.getColumnIndex(sex)));

                Parents parents = new Parents();
                parents.setId(unCurseur.getInt(unCurseur.getColumnIndex("p.id")));
                parents.setFirstName(unCurseur.getString(unCurseur.getColumnIndex("p.first_name")));
                parents.setLastName(unCurseur.getString(unCurseur.getColumnIndex("p.last_name")));
                parents.setBirth(unCurseur.getString(unCurseur.getColumnIndex("p.birth")));
                parents.setCity(unCurseur.getString(unCurseur.getColumnIndex("p.city")));
                parents.setCountry(unCurseur.getString(unCurseur.getColumnIndex("p.country")));
                parents.setaddress(unCurseur.getString(unCurseur.getColumnIndex("p.address")));
                parents.setPostalCode(unCurseur.getString(unCurseur.getColumnIndex("p.postal_code")));
                parents.setMail(unCurseur.getString(unCurseur.getColumnIndex("p.mail")));
                parents.setPhone(unCurseur.getString(unCurseur.getColumnIndex("p.phone")));
                parents.setSex(unCurseur.getString(unCurseur.getColumnIndex("p.sex")));

                Nurse nurse = new Nurse();
                nurse.setId(unCurseur.getInt(unCurseur.getColumnIndex("n.id")));
                nurse.setFirstName(unCurseur.getString(unCurseur.getColumnIndex("n.first_name")));
                nurse.setLastName(unCurseur.getString(unCurseur.getColumnIndex("n.last_name")));
                nurse.setBirth(unCurseur.getString(unCurseur.getColumnIndex("n.birth")));
                nurse.setCity(unCurseur.getString(unCurseur.getColumnIndex("n.city")));
                nurse.setCountry(unCurseur.getString(unCurseur.getColumnIndex("n.country")));
                nurse.setaddress(unCurseur.getString(unCurseur.getColumnIndex("n.address")));
                nurse.setPostalCode(unCurseur.getString(unCurseur.getColumnIndex("n.postal_code")));
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
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM " + nameTableChildren + " c JOIN " + nameTableParents + " p ON c." + idParents + " = p." + id + " JOIN " + nameTableNurse + " n ON p." + idNurse + " = n." + id + " WHERE c." + id + " = " + id + ";", null);
        if (unCurseur.moveToFirst()) {
            children = new Children();
            children.setId(id);
            children.setFirstName(unCurseur.getString(unCurseur.getColumnIndex(firstName)));
            children.setLastName(unCurseur.getString(unCurseur.getColumnIndex(last_name)));
            children.setBirth(unCurseur.getString(unCurseur.getColumnIndex(birth)));
            children.setSex(unCurseur.getString(unCurseur.getColumnIndex(sex)));

            Parents parents = new Parents();
            parents.setId(unCurseur.getInt(unCurseur.getColumnIndex("p.id")));
            parents.setFirstName(unCurseur.getString(unCurseur.getColumnIndex("p.first_name")));
            parents.setLastName(unCurseur.getString(unCurseur.getColumnIndex("p.last_name")));
            parents.setBirth(unCurseur.getString(unCurseur.getColumnIndex("p.birth")));
            parents.setCity(unCurseur.getString(unCurseur.getColumnIndex("p.city")));
            parents.setCountry(unCurseur.getString(unCurseur.getColumnIndex("p.country")));
            parents.setaddress(unCurseur.getString(unCurseur.getColumnIndex("p.address")));
            parents.setPostalCode(unCurseur.getString(unCurseur.getColumnIndex("p.postal_code")));
            parents.setMail(unCurseur.getString(unCurseur.getColumnIndex("p.mail")));
            parents.setPhone(unCurseur.getString(unCurseur.getColumnIndex("p.phone")));
            parents.setSex(unCurseur.getString(unCurseur.getColumnIndex("p.sex")));

            Nurse nurse = new Nurse();
            nurse.setId(unCurseur.getInt(unCurseur.getColumnIndex("n.id")));
            nurse.setFirstName(unCurseur.getString(unCurseur.getColumnIndex("n.first_name")));
            nurse.setLastName(unCurseur.getString(unCurseur.getColumnIndex("n.last_name")));
            nurse.setBirth(unCurseur.getString(unCurseur.getColumnIndex("n.birth")));
            nurse.setCity(unCurseur.getString(unCurseur.getColumnIndex("n.city")));
            nurse.setCountry(unCurseur.getString(unCurseur.getColumnIndex("n.country")));
            nurse.setaddress(unCurseur.getString(unCurseur.getColumnIndex("n.address")));
            nurse.setPostalCode(unCurseur.getString(unCurseur.getColumnIndex("n.postal_code")));
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
            children.setFirstName(unCurseur.getString(unCurseur.getColumnIndex(firstName)));
            children.setLastName(unCurseur.getString(unCurseur.getColumnIndex(last_name)));
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
            children.setFirstName(cursor.getString(cursor.getColumnIndex(this.firstName)));
            children.setLastName(cursor.getString(cursor.getColumnIndex(this.last_name)));
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
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + nameTableChildren + " c " + "WHERE c." + idParents + " = ?;", new String[]{String.valueOf(parentId)});

        if (cursor.moveToFirst()) {
            do {
                Children children = new Children();
                children.setId(cursor.getInt(cursor.getColumnIndex("id")));
                children.setFirstName(cursor.getString(cursor.getColumnIndex("first_name")));
                children.setLastName(cursor.getString(cursor.getColumnIndex("last_name")));
                children.setBirth(cursor.getString(cursor.getColumnIndex("birth")));
                children.setSex(cursor.getString(cursor.getColumnIndex("sex")));

                Nurse nurse = new Nurse();
                nurse.setId(cursor.getInt(cursor.getColumnIndex("idNurse")));
                children.setNurse(nurse);

                Parents parents = new Parents();
                parents.setId(cursor.getInt(cursor.getColumnIndex("idParents")));
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
    public ArrayList<Children> getChildrensByParentIdWithoutNurse(int parentId) {
        ArrayList<Children> allChildren = new ArrayList<>();

        open();
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + nameTableChildren + " c " +
                        "WHERE c." + idParents + " = ? AND c.idNurse IS NULL OR c.idNurse = -1;",
                new String[]{String.valueOf(parentId)});

        if (cursor.moveToFirst()) {
            do {
                Children children = new Children();
                children.setId(cursor.getInt(cursor.getColumnIndex("id")));
                children.setFirstName(cursor.getString(cursor.getColumnIndex("first_name")));
                children.setLastName(cursor.getString(cursor.getColumnIndex("last_name")));
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
    public ArrayList<Children> getChildrensByParentsIdWithNurse(int parentId) {
        ArrayList<Children> allChildren = new ArrayList<>();

        open();
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + nameTableChildren + " c " +
                        "WHERE c." + idParents + " = ? AND c.idNurse IS NOT NULL;",
                new String[]{String.valueOf(parentId)});

        if (cursor.moveToFirst()) {
            do {
                Children children = new Children();
                children.setId(cursor.getInt(cursor.getColumnIndex("id")));
                children.setFirstName(cursor.getString(cursor.getColumnIndex("first_name")));
                children.setLastName(cursor.getString(cursor.getColumnIndex("last_name")));
                children.setBirth(cursor.getString(cursor.getColumnIndex("birth")));
                children.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                children.setNurseAccepted(cursor.getInt(cursor.getColumnIndex("nurse_accepted")));

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
                        "WHERE c." + idNurse + " = ? AND c.nurse_accepted = 1;",
                new String[]{String.valueOf(nurseId)});

        if (cursor.moveToFirst()) {
            do {
                Children children = new Children();
                children.setId(cursor.getInt(cursor.getColumnIndex("id")));
                children.setFirstName(cursor.getString(cursor.getColumnIndex("first_name")));
                children.setLastName(cursor.getString(cursor.getColumnIndex("last_name")));
                children.setBirth(cursor.getString(cursor.getColumnIndex("birth")));
                children.setSex(cursor.getString(cursor.getColumnIndex("sex")));


                Parents parents = new Parents();

                parents.setId(cursor.getInt(cursor.getColumnIndex("idParents")));
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
    public ArrayList<Children> getChildrensByNurseIdExistingNurse(int nurseId) {
        ArrayList<Children> allChildren = new ArrayList<>();

        open();
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + nameTableChildren + " c " +
                        "WHERE c." + idNurse + " = ? AND c.nurse_accepted = 1;",
                new String[]{String.valueOf(nurseId)});

        if (cursor.moveToFirst()) {
            do {
                Children children = new Children();
                children.setId(cursor.getInt(cursor.getColumnIndex("id")));
                children.setFirstName(cursor.getString(cursor.getColumnIndex("first_name")));
                children.setLastName(cursor.getString(cursor.getColumnIndex("last_name")));
                children.setBirth(cursor.getString(cursor.getColumnIndex("birth")));
                children.setSex(cursor.getString(cursor.getColumnIndex("sex")));

                Nurse nurse = new Nurse();

                nurse.setId(cursor.getInt(cursor.getColumnIndex("idNurse")));
                Log.e("test", String.valueOf(nurse.getId()));
                children.setNurse(nurse);

                Parents parents = new Parents();
                parents.setId(cursor.getInt(cursor.getColumnIndex("idParents")));
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
                        "WHERE c." + idNurse + " = ? AND c.nurse_accepted IS NULL OR c.nurse_accepted = -1;",
                new String[]{String.valueOf(nurseId)});

        if (cursor.moveToFirst()) {
            do {
                Children children = new Children();
                children.setId(cursor.getInt(cursor.getColumnIndex("id")));
                children.setFirstName(cursor.getString(cursor.getColumnIndex("first_name")));
                children.setLastName(cursor.getString(cursor.getColumnIndex("last_name")));
                children.setBirth(cursor.getString(cursor.getColumnIndex("birth")));
                children.setSex(cursor.getString(cursor.getColumnIndex("sex")));

                Parents parents = new Parents();

                parents.setId(cursor.getInt(cursor.getColumnIndex("idParents")));
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
