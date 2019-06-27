package com.example.programmer.attenancesystem;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class StudentsDetail extends AppCompatActivity {

    EditText rollnumber,studentname,department,section;
    Button regiter_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_detail);

        regiter_btn = (Button) findViewById(R.id.registerbtn);

        rollnumber = (EditText) findViewById(R.id.roll_number);
        studentname = (EditText) findViewById(R.id.student_name);
        department = (EditText) findViewById(R.id.department_name);
        section = (EditText) findViewById(R.id.section);



        regiter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateregiterinfo();

            }
        });

    }

    private void validateregiterinfo() {

                String rollNumber = rollnumber.getText().toString();
                String studentName = studentname.getText().toString();
                String departmentName = department.getText().toString();
                String sectionName = section.getText().toString();


                if(TextUtils.isEmpty(rollNumber)){
                    Toast.makeText(StudentsDetail.this,"Roll number is mandatory!",Toast.LENGTH_SHORT).show();
                }

                else if(TextUtils.isEmpty(studentName)){
                    Toast.makeText(StudentsDetail.this,"Student name is mandatory!",Toast.LENGTH_SHORT).show();
                }

                else if(TextUtils.isEmpty(departmentName)){
                    Toast.makeText(StudentsDetail.this,"Department name is mandatory!",Toast.LENGTH_SHORT).show();
                }

                else if(TextUtils.isEmpty(sectionName)){
                    Toast.makeText(StudentsDetail.this,"Section field is mandatory!",Toast.LENGTH_SHORT).show();
                }

                else{
                    registerinfotodatabase(rollNumber,studentName,departmentName,sectionName);
                }
            }

    private void registerinfotodatabase(final String rollNumber, final String studentName, final String departmentName, final String sectionName) {

        final DatabaseReference rootref;
        rootref = FirebaseDatabase.getInstance().getReference();


        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(!(dataSnapshot.child("Students").child(rollNumber).exists())) {

                    HashMap<String,Object> hashObject = new HashMap<>();

                    hashObject.put("Roll number", rollNumber);
                    hashObject.put("student name", studentName);
                    hashObject.put("department name", departmentName);
                    hashObject.put("section", sectionName);

                    rootref.child("Students").child(rollNumber).updateChildren(hashObject)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(StudentsDetail.this, "Registered successfully...", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(StudentsDetail.this, "Network error please try again later.....", Toast.LENGTH_SHORT);
                                    }
                                }
                            });
                }

                else
                {
                    Toast.makeText(StudentsDetail.this, "this" + rollNumber + "Number already exists!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}











