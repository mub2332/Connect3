package com.example.mmah65.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow, 1 = red
    int activePlayer = 0;
    // 2 means unplayed
    int[] gameState = {
            2,
            2,
            2,
            2,
            2,
            2,
            2,
            2,
            2
    };
    // win positions
    int[][] winPositions = {
        {0, 1, 2},
        {3, 4, 5},
        {6, 7, 8},
        {0, 4, 8},
        {2, 4, 6},
        {0, 3, 6},
        {1, 4, 7},
        {2, 5, 8}
    };

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (counter.getDrawable() == null && gameState[tappedCounter] == 2) {
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            gameState[tappedCounter] = activePlayer;
            counter.animate().translationYBy(1000f).rotation(360f).setDuration(300);

            checkForWinner();
        }
    }

    private void checkForWinner() {
        for (int[] pos: winPositions) {
            if (gameState[pos[0]] == gameState[pos[1]] &&
                gameState[pos[1]] == gameState[pos[2]] &&
                gameState[pos[0]] != 2) {

                String winner = gameState[pos[0]] == 0 ? "Red" : "Yellow";
                TextView winMessage = findViewById(R.id.winMessage);
                winMessage.setText(winner + " has won!");

                Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                LinearLayout layout = findViewById(R.id.playAgainLayout);
                layout.startAnimation(slideUp);
                layout.setVisibility(View.VISIBLE);
            }
        }
    }

    public void playAgain() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
