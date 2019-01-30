package com.example.programmer.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText city;
    TextView result;

    //https://api.openweathermap.org/data/2.5/weather?q=chennai&appid=48631c79856ad4e31ab3af6b34635ab4

    String baseURL = "https://api.openweathermap.org/data/2.5/weather?q=";
    String API = "&appid=48631c79856ad4e31ab3af6b34635ab4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        city = (EditText) findViewById(R.id.getcity);
        result = (TextView) findViewById(R.id.result);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

       //         Log.i("Tap","ButtonTapped");


                String myURL = baseURL + city.getText().toString() + API;

                Log.i("URL","URL: " + myURL);



              JsonObjectRequest  jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myURL, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {

                                Log.i("JSON", "JSON : " + jsonObject);


                                try {
                                    String info = jsonObject.getString("weather");

                                    Log.i("INFO", "INFO " + info);


                                    JSONArray ar = new JSONArray(info);

                                    for (int i = 0; i < ar.length(); i++) {

                                        JSONObject parObj = ar.getJSONObject(i);

                                        String myWeather = parObj.getString("main");

                                        result.setText(myWeather);

                                        Log.i("ID","ID : " +parObj.getString("id"));
                                        Log.i("MAIN","MAIN" + parObj.getString("main"));
                                    }

                                }
                                catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("EROR","something went Wrong" + error);
                            }
                        }

                );

                MySingleton.getInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);

            }
        });


    }
}
