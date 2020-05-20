package com.example.coronaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    //Vars
    public static final String DB_Name = "States.db";
    public static final String Table_Name = "diagnosis";
    public static final String Col_1 = "Date";
    public static final String Col_2 = "Temperature";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creating DB
        db.execSQL("create table " + Table_Name + " (Date TEXT, Temperature TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //If the table already exists
        db.execSQL("DROP TABLE IF EXISTS "+Table_Name);
        onCreate(db);
    }

    boolean insertData(String user, String data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_1, user);
        contentValues.put(Col_2, data);
        long result = db.insert(Table_Name, null, contentValues);
        if (result == -1){
            return false;
        }else
            return true;
    }

    Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from "+Table_Name, null);
    }
}
