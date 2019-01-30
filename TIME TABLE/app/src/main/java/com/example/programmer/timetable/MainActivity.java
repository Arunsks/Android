package com.example.programmer.timetable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar ;
    ListView listView;

    public void generateTables(int tablesNumber){


        listView = (ListView) findViewById(R.id.listview);

        ArrayList<String> arrayList = new ArrayList<String>();

        for(int j=1; j<=20 ;j++){

            arrayList.add(tablesNumber + "*" + j + "=" + j * tablesNumber );
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        listView = (ListView) findViewById(R.id.listview);

        int progress = 10;
        int maximum = 20;

        seekBar.setMax(maximum);
        seekBar.setProgress(progress);

        generateTables(progress);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int min = 1;
                int tableNumber ;

                if(progress < min){

                    tableNumber  = min;

                }else {

                    tableNumber = progress;
                }

                Log.i("Integer" , Integer.toString(tableNumber));
                generateTables(tableNumber);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}
