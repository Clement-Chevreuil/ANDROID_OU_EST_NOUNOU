package com.example.ouestnounou;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String nameTableUser = "User";
    private static final String idUser = "id";
    private static final String mail = "mail";
    private static final String password = "password";



    private static final String reqCreateUser = "CREATE TABLE " + nameTableUser + " (" + idUser + " INTEGER PRIMARY KEY AUTOINCREMENT," + mail + " TEXT," + password + " TEXT);";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("test base", "insertion " + reqCreateUser);
        db.execSQL(reqCreateUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String reqSuppUser = "DROP TABLE IF EXISTS " + nameTableUser + reqCreateUser;
        db.execSQL(reqSuppUser);
        onCreate(db);
    }
}
