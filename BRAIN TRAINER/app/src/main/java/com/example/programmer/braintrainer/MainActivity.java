package com.example.programmer.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button gobutton , button0, button1, button2, button3;
    TextView timerTextView, scoreTextView, resultTextView, sumTextView, playAgainButton,rulesTextView;
    ConstraintLayout gameLayout;
    ArrayList<Integer> answers;
    int locationOfAnswer,score,totalNoOfQuestions;

    boolean activeState = true;

    public void generateQuestions() {

            Random rand = new Random();
            int a = rand.nextInt(21);
            int b = rand.nextInt(21);
            sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));
            locationOfAnswer = rand.nextInt(4);
            answers.clear();

            for (int i = 0; i < 4; i++) {
                if (i == locationOfAnswer) {
                    answers.add(a + b);
                } else {
                    int wrongNumber = rand.nextInt(41);

                    while (wrongNumber == a + b) {
                        wrongNumber = rand.nextInt(41);
                    }
                    answers.add(wrongNumber);
                }
            }
            button0.setText(Integer.toString(answers.get(0)));
            button1.setText(Integer.toString(answers.get(1)));
            button2.setText(Integer.toString(answers.get(2)));
            button3.setText(Integer.toString(answers.get(3)));
        }

        public void buttonTapped(View view) {


            if (activeState == true) {

                if (Integer.toString(locationOfAnswer).equals(view.getTag().toString())) {
                    resultTextView.setText("correct !");
                    score++;
                } else {
                    resultTextView.setText("wrong :( ");
                }
                totalNoOfQuestions++;
                scoreTextView.setText(score + "/" + totalNoOfQuestions);
                generateQuestions();

            }
        }
        public void start(View view){
        gobutton.setVisibility(View.INVISIBLE);
        rulesTextView.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }

    public void playAgain(View view){
        score = 0;
        totalNoOfQuestions = 0;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(totalNoOfQuestions));
        timerTextView.setText("30s");
        generateQuestions();
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        activeState = true;

        new CountDownTimer(30000 + 100 , 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(Long.toString(millisUntilFinished / 1000) + "s");
            }
            @Override
            public void onFinish() {
                resultTextView.setText("your total score is : " + score + "/" + totalNoOfQuestions);
                playAgainButton.setVisibility(View.VISIBLE);
                activeState = false;

            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gobutton = (Button) findViewById(R.id.goButton);
        gameLayout = (ConstraintLayout) findViewById(R.id.gameLayout);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        rulesTextView = (TextView) findViewById(R.id.rulesTextView);
        answers = new ArrayList<Integer>();

        generateQuestions();

        rulesTextView.setVisibility(View.VISIBLE);
        gobutton.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

        rulesTextView.setText(" RULES \n \n " +
                "\t 1.As soon as you hit an Go button the game starts \n \n " +
                "\t 2.There will be maths two digit addition questions with four choices \n \n" +
                "\t 3.for each correct answer you will get one mark  \n \n" +
                "\t 4.And the timer runs constantly you need to answer maximum questions within 30sec \n \n" +
                "\t 5.After 30 secs your total score will be displayed \n \n" +
                "\t 6.Try as maximum as possible by hitting an playButton");

    }
}
