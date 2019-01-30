package com.example.programmer.sounds;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    public void sounds(View view){

        Button buttonpressed = (Button) view;

        MediaPlayer mPlayer = MediaPlayer.create(this, getResources().getIdentifier(buttonpressed.getTag().toString(), "raw", getPackageName()));

        mPlayer.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
