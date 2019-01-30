package com.example.programmer.sqlitebasic;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Events" , MODE_PRIVATE,null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS theEvents (name VARCHAR , year INT(4), id INTEGER PRIMARY KEY)");
            sqLiteDatabase.execSQL("INSERT INTO theEvents (name, year) VALUES ('Arun' , 1998)");
            sqLiteDatabase.execSQL("INSERT INTO theEvents (name, year) VALUES('kumar' , 2000)");
            sqLiteDatabase.execSQL("INSERT INTO theEvents (name, year) VALUES('karthi' , 2001)");
            sqLiteDatabase.execSQL("INSERT INTO theEvents (name, year) VALUES('karan' , 2002)");

            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM theEvents " , null);

            int nameIndex = c.getColumnIndex("name");
            int yearIndex = c.getColumnIndex("year");
            int idIndex = c.getColumnIndex("id");

            c.moveToFirst();

            while(c != null){

                Log.i("Results - name" , c.getString(nameIndex));
                Log.i("Results - year" , c.getString(yearIndex));
                Log.i("Results - id" , c.getString(idIndex));
                c.moveToNext();

            }

        }catch (Exception e){
            e.printStackTrace();
        }






    }
}
