package com.example.programmer.firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button button,logout;

    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        Toast.makeText(this, "Alrdy In", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.email);

        password = (EditText) findViewById(R.id.password);

        logout = (Button) findViewById(R.id.logout);

        mAuth = FirebaseAuth.getInstance();


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this,"logut sucess onclick",Toast.LENGTH_SHORT).show();
            }
        });




    }

    public void onRegister(View view) {

        final String myEmail = email.getText().toString();
        final String myPass = email.getText().toString();

        mAuth.createUserWithEmailAndPassword(myEmail, myPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("TAG", "createUserWithEmail:success");

                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("TAG", "createUserWithEmail:failure", task.getException());

                            Toast.makeText(MainActivity.this, "failure", Toast.LENGTH_SHORT).show();

                        }


                    }
                });

        }


        public void onLogin(View view){

            final String myEmail = email.getText().toString();
            final String myPass = email.getText().toString();

            mAuth.signInWithEmailAndPassword(myEmail, myPass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.i("TAAG", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(MainActivity.this, "auth sucess", Toast.LENGTH_SHORT).show();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.i("TAG", "failure login", task.getException());

                                Toast.makeText(MainActivity.this, "auth failed", Toast.LENGTH_SHORT).show();

                            }
                        }

                    });

            }





//            public void logout(View view){
//
//                FirebaseAuth.getInstance().signOut();
//
//                Toast.makeText(MainActivity.this,"LOGOUT SUCCESS",Toast.LENGTH_SHORT).show();
//
//            }

        }










