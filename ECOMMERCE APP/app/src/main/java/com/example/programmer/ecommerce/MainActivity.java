package com.example.programmer.ecommerce;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.programmer.ecommerce.Recent.Recent;
import com.example.programmer.ecommerce.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private Button login,joinnow;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingBar = new ProgressDialog(this);
        login = (Button) findViewById(R.id.login);
        joinnow = (Button) findViewById(R.id.join_now);
        Paper.init(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


        joinnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });

        String UserPhoneKey = Paper.book().read(Recent.UserPhoneKey);
        String UserPasssKey = Paper.book().read(Recent.UserPassKey);


        if(UserPhoneKey!= "" && UserPasssKey!= ""){

            if(!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserPasssKey)) {

                AllowAccess(UserPhoneKey, UserPasssKey);

                loadingBar.setTitle("Already logged in");
                loadingBar.setMessage("Please wait, getting into an already logged in account...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
            }


        }

    }

    private void AllowAccess(final String phone, final String password) {

        final DatabaseReference rootref;
        rootref = FirebaseDatabase.getInstance().getReference();

        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child("Users").child(phone).exists()){

                    Users userdata = dataSnapshot.child("Users").child(phone).getValue(Users.class);

                    if(userdata.getPhone().equals(phone)){

                        if(userdata.getPassword().equals(password)){

                            Toast.makeText(MainActivity.this,"logged in Successfully",Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);

                        }

                        else{
                            loadingBar.dismiss();
                            Toast.makeText(MainActivity.this,"Password Incorrect",Toast.LENGTH_SHORT).show();
                        }
                    }

                    else{
                        loadingBar.dismiss();
                        Toast.makeText(MainActivity.this,"Account with this" + phone +"Number doesn't exists",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
