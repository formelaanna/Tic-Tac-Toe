package com.example.hp.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LinearLayout layout;
    GridLayout board;
    TextView message;
    //0-yellow token, 1- red token, 2 - no token
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};//winning positions
    int tokenCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.playAgainLayout);
        board = findViewById(R.id.boardLayout);
        message = findViewById(R.id.whoWinsTextView);
    }

    public void drop(View view) {

        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1000f);
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2) {

            tokenCounter++;

            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState
                        [winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    String winner = "Red";
                    if (gameState[winningPosition[0]] == 0)
                        winner = "Yellow";
                    message.setText(winner + " wins!!");
                    board.setVisibility(View.INVISIBLE);
                    layout.setVisibility(View.VISIBLE);
                    System.out.println(gameState[winningPosition[0]]);
                }


                if (tokenCounter == 9 && (gameState[winningPosition[0]] != gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2)) {
                    message.setText(" Draw");
                    board.setVisibility(View.INVISIBLE);
                    layout.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view) {
        tokenCounter = 0;
        layout.setVisibility(View.INVISIBLE);
        board.setVisibility(View.VISIBLE);
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        for (int i = 0; i < board.getChildCount(); i++) {

            ((ImageView) board.getChildAt(i)).setImageResource(0);
        }
    }
}