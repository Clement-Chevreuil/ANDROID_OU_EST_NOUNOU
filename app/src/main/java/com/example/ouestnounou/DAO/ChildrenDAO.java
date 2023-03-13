package com.example.ouestnounou.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.ouestnounou.MODEL.Children;

import java.util.ArrayList;
import java.util.Collections;

public class ChildrenDAO extends DAOBase{

    private static final String nameTableChildren = "Children";
    private static final String id = "id";
    private static final String firstName = "first_name";
    private static final String last_name = "last_name";
    private static final String birth = "birth";
    private static final String sex = "sex";


    public ChildrenDAO(Context pContext) {
        super(pContext);
    }

    public void add(Children children) {

        ContentValues values = new ContentValues();

        values.put(firstName, children.getFist_name());
        values.put(last_name, children.getLast_name());
        values.put(birth, String.valueOf(children.getBirth()));
        values.put(sex, children.getSex());

        mDb.insert(nameTableChildren, null, values);
    }

    public void update(Children children) {
        ContentValues values = new ContentValues();
        values.put(firstName, children.getFist_name());
        values.put(last_name, children.getLast_name());
        values.put(birth, String.valueOf(children.getBirth()));
        values.put(sex, children.getSex());
        mDb.update(nameTableChildren, values, id  + " = ?", new String[] {String.valueOf(children.getId())});
    }

    public void delete(long id) {
        mDb.delete(nameTableChildren, id + " = ?", new String[] {String.valueOf(id)});
    }

    public ArrayList<Children> getChildrens() {
        ArrayList<Children> allChildren = new ArrayList<Children>();

        this.open();
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM Children;", null);
        if (unCurseur.moveToFirst()) {
            do {
                Children children = new Children();
                children.setId(unCurseur.getInt(unCurseur.getColumnIndex(id)));
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

}