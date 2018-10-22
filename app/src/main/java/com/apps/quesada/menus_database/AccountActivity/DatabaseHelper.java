package com.apps.quesada.menus_database.AccountActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("Create table user(nombres text,apellidos text,email text primary key,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL("Drop table if exists user");
    }
    //Insertando en la database
    public boolean insert(String nombres, String apellidos, String email, String password){
       SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombres", nombres);
        contentValues.put("apellidos", apellidos);
        contentValues.put("email", email);
        contentValues.put("password", password);

        long ins = db.insert("user", null,contentValues);
        if (ins==-1) return false;
        else return true;
    }

    //Comprueba si existe email duplicado
    public Boolean chkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email});
        if (cursor.getCount()>0) return false;
        else return true;
    }
    //Comprobando email y password para el login
    public Boolean emailpassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password=?", new String[]{email,password});

        if (cursor.getCount()>0) return true;
        else return false;
    }

    //Recupera nombre e email de la db
    public  Boolean recuperanombreemail(String email, String nombre){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select nombre, email from user", new String[]{nombre, email});

        if (cursor.getCount()>0) return false;
        else return false;
    }
}
