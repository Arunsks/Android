package com.example.programmer.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public void CurrencyConverter(View view){

        Log.i("TAG","button clicked");

        EditText editText = (EditText) findViewById(R.id.editText);

        Log.i("Edittext", editText.getText().toString());

        String amountInDollars  = editText.getText().toString();

        Double amountInDollarDouble = (Double) Double.parseDouble(amountInDollars);

        Double amountInIndianValue = amountInDollarDouble * 71.38;


        String amountInIndianValueDouble = String.format("%.2f", amountInIndianValue);

        Log.i("Indian value " ,amountInIndianValueDouble);

        Toast.makeText(MainActivity.this , "$ " + amountInDollars + " ₹ = " + amountInIndianValueDouble, Toast.LENGTH_LONG).show();


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
