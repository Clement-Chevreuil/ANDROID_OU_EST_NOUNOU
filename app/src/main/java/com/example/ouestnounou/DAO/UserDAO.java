package com.example.ouestnounou.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.ouestnounou.MODEL.User;

import java.util.ArrayList;
import java.util.Collections;

public class UserDAO extends DAOBase {

    private static final String nameTableUser = "User";
    private static final String id = "id";
    private static final String mail = "mail";
    private static final String password = "password";
    private static final String reqCreateUser = "CREATE TABLE " + nameTableUser + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT," + mail + " TEXT," + password + " TEXT);";
    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + nameTableUser + ";";

    public UserDAO(Context pContext) {
        super(pContext);
        testUsers();
    }

    public void testUsers() {
        ArrayList<User> allUser = new ArrayList<User>();
        this.open();
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM User;", null);
        if (unCurseur.moveToFirst()) {

        }
        else
        {
            createUser();
        }
        this.close();

    }

    public void add(User user) {

        ContentValues values = new ContentValues();

        values.put(mail, user.getMail());
        values.put(password, user.getPassword());

        mDb.insert(nameTableUser, null, values);
    }

    /**
     * @param id l'identifiant du métier à supprimer
     */
    public void delete(long id) {
        mDb.delete(nameTableUser, id + " = ?", new String[] {String.valueOf(id)});
    }

    /**
     * @param m le métier modifié
     */
    public void update(User m) {
        ContentValues value = new ContentValues();
        value.put(mail, m.getMail());
        mDb.update(nameTableUser, value, id  + " = ?", new String[] {String.valueOf(m.getId())});
    }

    public void fakeData() {
        this.open();
        User user = new User("c", "c");
        ContentValues values = new ContentValues();

        values.put(mail, user.getMail());
        values.put(password, user.getPassword());

        mDb.insert(nameTableUser, null, values);
        this.close();
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> allUser = new ArrayList<User>();

        /*User user = new User();
        user.setIdUser(1);
        user.setPseudo("kev");
        allUser.add(user);

        User user2 = new User();
        user2.setIdUser(2);
        user2.setPseudo("adam");
        allUser.add(user2);

        User user3 = new User();
        user3.setIdUser(3);
        user3.setPseudo("clem");
        allUser.add(user3);*/

        this.open();
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM User;", null);
        if (unCurseur.moveToFirst()) {
            do {
                User user = new User();
                user.setId(unCurseur.getInt(unCurseur.getColumnIndex(id)));
                user.setMail(unCurseur.getString(unCurseur.getColumnIndex(mail)));
                allUser.add(user);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(allUser);
        }
        else
        {
            createUser();
        }
        this.close();
        return allUser;

    }

    public User connexion(String mail_data, String password_data){

        this.open();
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM User WHERE mail = '" + mail_data + "' AND password = '" + password_data + "' ;", null);
        User user = new User();
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

    public void createUser(){

        this.open();
        User user2 = new User("clement", "clement" );
        add(user2);
        this.close();
    }

    /**
     * @param id l'identifiant du métier à récupérer
     */
    /*public User select(long id) {
        // CODE
    }*/
}
