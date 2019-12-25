package com.example.programmer.sqlite;

import android.Manifest;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Databasehelper myDb;
    EditText editName , editSurName , editMark ,editId;
    Button addbtn,viewbtn,updatebtn,deletebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = (EditText) findViewById(R.id.edit_name);
        editSurName = (EditText) findViewById(R.id.edit_surname);
        editMark = (EditText) findViewById(R.id.edit_mark);
        addbtn = (Button) findViewById(R.id.addbtn);
        editId = (EditText) findViewById(R.id.edit_id);
        viewbtn = (Button) findViewById(R.id.viewbtn);
        updatebtn = (Button) findViewById(R.id.updatebtn);
        deletebtn = (Button) findViewById(R.id.deletebtn);

        myDb = new Databasehelper(this);

        addAll();
        viewAll();
        updateAll();
        deleteAll();
    }

    public void deleteAll(){
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Integer deletedRows =  myDb.deleteData(editId.getText().toString());

               if(deletedRows > 0)
                   Toast.makeText(MainActivity.this, "DATA DELETED", Toast.LENGTH_SHORT).show();
                else
                   Toast.makeText(MainActivity.this, "DATA NOT DELETED", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void updateAll(){
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isUpdated=  myDb.updateData(editId.getText().toString(),
                        editName.getText().toString() ,
                        editSurName.getText().toString(),
                        editMark.getText().toString());


                if(isUpdated == true)
                    Toast.makeText(MainActivity.this, "DATA UPDATED", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "DATA NOT UPDATED", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void addAll(){
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isInserted =  myDb.insertData(editName.getText().toString() ,
                        editSurName.getText().toString(),
                        editMark.getText().toString());

                if(isInserted == true)
                    Toast.makeText(MainActivity.this, "DATA INSERTED", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "DATA NOT INSERTED", Toast.LENGTH_SHORT).show();

            }
        });

    }


    public void viewAll()
    {
        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = myDb.getdata();

                if(res.getCount() == 0){
                    showmessage("Error" , "Nothing found");
                }

                else {

                    StringBuffer buffer = new StringBuffer();

                    while(res.moveToNext()){

                        buffer.append("id: " + res.getString(0)+ "\n");
                        buffer.append("name: " + res.getString(1)+ "\n");
                        buffer.append("surname: " + res.getString(2)+ "\n");
                        buffer.append("mark : " + res.getString(3)+ "\n\n");
                    }

                    showmessage("Data" , buffer.toString());

                }

            }


        });
    }


    public void showmessage(String title , String message){

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(true);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.show();
    }

}
