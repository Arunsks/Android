package com.example.programmer.adaptiveicons;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public void gopip(View view){

        enterPictureInPictureMode();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);

        TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);

        if(isInPictureInPictureMode){
            button.setVisibility(View.INVISIBLE);
            getSupportActionBar().hide();
            textView.setText("00:33:45:69");
        }
        else{
            button.setVisibility(View.VISIBLE);
            getSupportActionBar().show();
            textView.setText("Hello World!");
        }

    }
}
