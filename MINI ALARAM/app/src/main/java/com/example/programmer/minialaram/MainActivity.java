package com.example.programmer.minialaram;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar ;
    TextView textView;
    Button button ;
    MediaPlayer mediaPlayer;
    Boolean countIsActive = false;
    CountDownTimer countDownTimer;

    public void resetTimer(){

        textView.setText("0:30");
        button.setText("GO!");
        countIsActive = false;
        seekBar.setEnabled(true);
        seekBar.setProgress(30);
        countDownTimer.cancel();

    }
    public void buttontapped(View view) {

        if(countIsActive) {

            resetTimer();
        }

        else {
            seekBar.setEnabled(false);
            button.setText("reset");
            countIsActive = true;

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {

                    timeCounter((int) millisUntilFinished / 1000);
                }
                @Override
                public void onFinish() {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }
    }
        public void timeCounter(int seconds){

            int minutes = seconds / 60;
            int second  = seconds - (minutes *60);
            String secondString = Integer.toString(second);

            if(second <= 9){
                secondString = "0" + secondString;
            }
            textView.setText(Integer.toString(minutes) + ":" + secondString);
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         seekBar = (SeekBar) findViewById(R.id.seekBar);
         textView = (TextView) findViewById(R.id.textView);
         button = (Button) findViewById(R.id.button);

         seekBar.setMax(600);
         seekBar.setProgress(30);

         seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                 timeCounter(progress);
             }
             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {
             }
             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {
             }
         });
    }
}
