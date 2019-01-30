package com.example.programmer.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activeplayer = 0;

    int gamestate[] = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    boolean gameactive = true;

    public void imageTapped(View view){

        ImageView counter = (ImageView) view;

        counter.setTranslationY(-1000);

        int Tappedvalue = Integer.parseInt(counter.getTag().toString());


        if(gamestate[Tappedvalue] == 2 && gameactive) {

            gamestate[Tappedvalue] = activeplayer;

            if (activeplayer == 0) {

                counter.setImageResource(R.drawable.red);
                activeplayer = 1;

            } else {

                counter.setImageResource(R.drawable.black);
                activeplayer = 0;

            }

        }

        counter.animate().translationYBy(1000).rotation(3600).setDuration(300);

        for(int[] winingposition : winningPositions){

            if(gamestate[winingposition [0]] == gamestate[winingposition[1]] && gamestate[winingposition[1]] == gamestate[winingposition[2]] && gamestate[winingposition[0]] != 2 ){

                gameactive = false;

                String winner = "";

               if(activeplayer == 1){

                   winner = "Red";
               }
               else{

                   winner = "Black";
               }

                Button button = (Button) findViewById(R.id.button);

                TextView textView = (TextView) findViewById(R.id.textView);

                textView.setText(winner + " has won");

                button.setVisibility(view.VISIBLE);

                textView.setVisibility(view.VISIBLE);

            }
        }

    }


    public void playAgain(View view) {

        Button playAgainButton = (Button) findViewById(R.id.button);

        TextView winnerTextView = (TextView) findViewById(R.id.textView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridlayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }

        for (int i=0; i<gamestate.length; i++) {

            gamestate[i] = 2;

        }

        activeplayer = 0;

        gameactive = true;

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
