package com.example.programmer.ecommerce;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.programmer.ecommerce.Recent.Recent;
import com.example.programmer.ecommerce.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

   private EditText PhoneNumber,PassWord;
   private Button loginbtn;
   private ProgressDialog loadingBar;
   private com.rey.material.widget.CheckBox chckRemberMe;
   private String ParentDbName = "Users";

   private TextView AdminLink,NotAnAdminLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Paper.init(this);
        chckRemberMe = (com.rey.material.widget.CheckBox) findViewById(R.id.login_checkbox);

        loginbtn = (Button) findViewById(R.id.login_button);
        PhoneNumber = (EditText)findViewById(R.id.login_phonenumber);
        PassWord = (EditText)findViewById(R.id.login_password);
        loadingBar = new ProgressDialog(this);

        AdminLink = (TextView) findViewById(R.id.Admin);
        NotAnAdminLink = (TextView) findViewById(R.id.Not_Admin);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
            }
        });


        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginbtn.setText("Admin Login");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAnAdminLink.setVisibility(View.VISIBLE);
                ParentDbName = "Admins";

            }
        });

        NotAnAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginbtn.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAnAdminLink.setVisibility(View.INVISIBLE);
                ParentDbName = "Users";

            }
        });


    }

    private void login() {

        String phone = PhoneNumber.getText().toString();
        String password = PassWord.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "PhoneNumber Is Empty...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password Is Empty...", Toast.LENGTH_SHORT).show();
        } else {

            AccessToAnAccount(password,phone);

            loadingBar.setTitle("Logging in");
            loadingBar.setMessage("Please wait until logging into an account...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();


        }

       }

    private void AccessToAnAccount(final String password, final String phone) {

        if(chckRemberMe.isChecked()){

            Paper.book().write(Recent.UserPhoneKey , phone);
            Paper.book().write(Recent.UserPassKey,password);

        }

        final DatabaseReference rootref;
        rootref = FirebaseDatabase.getInstance().getReference();

        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(ParentDbName).child(phone).exists()){

                    Users userdata = dataSnapshot.child(ParentDbName).child(phone).getValue(Users.class);

                    if(userdata.getPhone().equals(phone)){

                        if(userdata.getPassword().equals(password)) {

                            if (ParentDbName.equals("Admins")) {


                                Toast.makeText(LoginActivity.this, "Wait Admin,Your are logging in", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(LoginActivity.this, AdminCategoryActivity.class);
                                startActivity(intent);

                            }
                            else if (ParentDbName.equals("Users")){



                                Toast.makeText(LoginActivity.this, "logged in Successfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Recent.currentOnlineUser = userdata;

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }


                        }

                        else{
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this,"Password Incorrect",Toast.LENGTH_SHORT).show();
                            }
                }

                    else{
                        loadingBar.dismiss();
                        Toast.makeText(LoginActivity.this,"Account with this" + phone +"Number doesn't exists",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}


