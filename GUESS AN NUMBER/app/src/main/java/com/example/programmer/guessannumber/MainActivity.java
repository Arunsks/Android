package com.example.programmer.guessannumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int randomNumber;

    public void generateRandomNumber(){

        Random ran = new Random();

        randomNumber = ran.nextInt(20) + 1;


    }


    public void guess(View view){

        EditText editText = (EditText) findViewById(R.id.editText);

        Button button = (Button) findViewById(R.id.button);

        int editTextValue = Integer.parseInt(editText.getText().toString());

        if(editTextValue > randomNumber){

            Toast.makeText(MainActivity.this,"lower" , Toast.LENGTH_SHORT).show();

        }

        else if(editTextValue < randomNumber){

            Toast.makeText(MainActivity.this,"higher" , Toast.LENGTH_SHORT).show();

        }

        else {

            Toast.makeText(MainActivity.this,"You got it ! Try again" , Toast.LENGTH_SHORT).show();

            generateRandomNumber();
        }


        Log.i("message" , "Button is clicked");

       Log.i("RandomNumber" , Integer.toString(randomNumber));

        Log.i("Edit text" , editText.getText().toString());


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateRandomNumber();

    }
}
