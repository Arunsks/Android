package com.example.programmer.ecommerce;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity {

    private Button SignUp;
    EditText RegisterUserName, RegisterPassWord, RegiterPhoneNumber;
    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        SignUp = (Button) findViewById(R.id.Register_SignUp_button);
        RegisterUserName = (EditText) findViewById(R.id.Register_UserName_input);
        RegisterPassWord = (EditText) findViewById(R.id.Register_password_input);
        RegiterPhoneNumber = (EditText) findViewById(R.id.Register_phoneNumber_input);
        loadingBar = new ProgressDialog(this);



        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CreateAccount();
            }
        });

    }

    private void CreateAccount() {

        String name = RegisterUserName.getText().toString();
        String phone = RegiterPhoneNumber.getText().toString();
        String password = RegisterPassWord.getText().toString();

        if (TextUtils.isEmpty(name)) {

            Toast.makeText(this, "UserNameIsEmpty...", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(phone)) {

            Toast.makeText(this, "PhoneNumberIsEmpty...", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(password)) {

            Toast.makeText(this, "PasswordIsEmpty...", Toast.LENGTH_SHORT).show();

        } else {

            loadingBar.setTitle("Creating An Account");
            loadingBar.setMessage("Please wait until creating an account...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatePhoneNumber(name,password,phone);

        }

    }

    private void ValidatePhoneNumber(final String name, final String password, final String phone) {

        final DatabaseReference rootref;
        rootref = FirebaseDatabase.getInstance().getReference();

        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(!(dataSnapshot.child("Users").child(phone).exists())) {

                    HashMap<String, Object> hashObject = new HashMap<>();

                    hashObject.put("name", name);
                    hashObject.put("password", password);
                    hashObject.put("phone", phone);

                    rootref.child("Users").child(phone).updateChildren(hashObject)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {

                                        Toast.makeText(RegisterActivity.this, "your account created successfully...", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(intent);

                                    } else {

                                        Toast.makeText(RegisterActivity.this, "Network error please try again later.....", Toast.LENGTH_SHORT);

                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(intent);

                                    }
                                }
                            });
                }

                else
                {

                    Toast.makeText(RegisterActivity.this, "this" + phone + "Number already exists!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {



            }
        });

    }

}
