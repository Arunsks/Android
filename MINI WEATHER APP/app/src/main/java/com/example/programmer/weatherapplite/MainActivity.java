package com.example.programmer.weatherapplite;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    TextView resultTextView;

    EditText cityEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityEditText = (EditText) findViewById(R.id.editText);
        resultTextView  = (TextView) findViewById(R.id.resultTextView);

    }

    public void getWeather(View view){

        DownloadTask task = new DownloadTask();
        task.execute("https://openweathermap.org/data/2.5/weather?q="+ cityEditText.getText().toString() +"&appid=b6907d289e10d714a6e88b30761fae22");

    }

    public class DownloadTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            URL url;
            HttpsURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpsURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data= reader.read();

                while(data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return  result;

            } catch (Exception e) {
                e.printStackTrace();

                Toast.makeText(getApplicationContext(),"Enter the correct city",Toast.LENGTH_SHORT).show();


                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                String weather = jsonObject.getString("weather");
                Log.i("WEATHER" , weather);
                JSONArray arr = new JSONArray(weather);

                for(int i=0; i < arr.length(); i++){
                    JSONObject object = arr.getJSONObject(i);
                    String message = "";
                    String main = "";
                    String description = "";

                    main = object.getString("main");
                    description = object.getString("description");

                    if(!main.equals("") && !description.equals("")){

                        message += main + ":" + description;
                        resultTextView.setText(message);

                    }else{

                        Toast.makeText(getApplicationContext(),"Enter the correct city",Toast.LENGTH_SHORT).show();

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();

                Toast.makeText(getApplicationContext(),"Enter the correct city",Toast.LENGTH_SHORT).show();

            }
        }
    }
}
