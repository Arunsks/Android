package com.example.programmer.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Databasehelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "students.db";
    public static final String TABLE_NAME = "students";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";

    public Databasehelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , " +

                "NAME TEXT , SURNAME TEXT , MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name , String surname , String mark){

        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2 ,name);
        values.put(COL_3 ,surname);
        values.put(COL_4 ,mark);
        long result = db.insert(TABLE_NAME , null , values);

        if(result == -1)
            return  false;
        else
            return true;
    }

    public Cursor getdata(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(" select * from " + TABLE_NAME , null);
        return  res;
    }

    public boolean updateData(String id ,String name , String surname , String mark){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values  = new ContentValues();

        values.put(COL_1 ,id);
        values.put(COL_2 ,name);
        values.put(COL_3 ,surname);
        values.put(COL_4 ,mark);

        db.update(TABLE_NAME, values , "ID = ? " , new String[]{id});

        return true;
    }

    public Integer deleteData(String id){

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME , "ID = ? " , new String[]{id});
    }


}
