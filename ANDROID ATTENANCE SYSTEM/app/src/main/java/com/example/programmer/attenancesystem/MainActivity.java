package com.example.programmer.attenancesystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addStudentBtn,viewStudentDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addStudentBtn = (Button) findViewById(R.id.add_student_btn);
        viewStudentDetail = (Button) findViewById(R.id.view_student_detail);

        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this , StudentsDetail.class);
                startActivity(intent);

            }
        });

        viewStudentDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this , StudentsInfo.class);
                startActivity(intent);

            }
        });



    }
}
