package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainGameActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_home, btn_playAgain;
    TextView winText;
    Boolean playerOneActive;
    Button[] btn = new Button[9];
    int rounds = 0;
    int[] gameBoard = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[][] winPos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        btn_home = findViewById(R.id.btn_home);
        btn_playAgain = findViewById(R.id.btn_playAgain);
        winText = findViewById(R.id.winId);

        btn[0] = findViewById(R.id.btn0);
        btn[1] = findViewById(R.id.btn1);
        btn[2] = findViewById(R.id.btn2);
        btn[3] = findViewById(R.id.btn3);
        btn[4] = findViewById(R.id.btn4);
        btn[5] = findViewById(R.id.btn5);
        btn[6] = findViewById(R.id.btn6);
        btn[7] = findViewById(R.id.btn7);
        btn[8] = findViewById(R.id.btn8);

        playerOneActive = true;
        btn_playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainGameActivity.this, MainActivity.class);
                startActivity(intent3);
            }
        });
        for (int i = 0; i < btn.length; i++) {

            btn[i].setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        } else if (checkWinner()) {
            return;
        }

        String buttonId = ((Button) v).getResources().getResourceEntryName(v.getId());
        int gameBoardPointer = Integer.parseInt(buttonId.substring((buttonId.length() - 1)));

        if (playerOneActive) {
            ((Button) v).setText("X");
            // ((Button) v).setTextColor(Color.parseColor("#FFFFFFF"));
            gameBoard[gameBoardPointer] = 1;
        } else {
            ((Button) v).setText("0");
            ((Button) v).setTextColor(Color.parseColor("#cc1234"));
            gameBoard[gameBoardPointer] = 2;
        }
        rounds++;
        String firstName = "Player 1", secondName = "Player 2";
        String[] player = getIntent().getStringArrayExtra("PlayerNames");
        firstName = player[0];
        secondName = player[1];
        if (checkWinner()) {
            if (playerOneActive) {
                winText.setText(firstName + " Win !!");
            } else {
                winText.setText(secondName + " Win !!");
            }
        } else if (rounds == 9) {
            winText.setText("Draw !!");
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        } else {
            playerOneActive = !playerOneActive;
        }
    }
    private void playAgain() {
        rounds = 0;
        playerOneActive = true;
        for (int i = 0; i < btn.length; i++) {
            gameBoard[i] = 0;
            btn[i].setText("");
        }
        winText.setText("");
    }

    private boolean checkWinner() {
        boolean winRes = false;

        for (int[] winP : winPos) {
            if (gameBoard[winP[0]] != 0 && gameBoard[winP[0]] == gameBoard[winP[1]] && gameBoard[winP[1]] == gameBoard[winP[2]]) {
                winRes = true;
                break;
            }
        }

        return winRes;
    }
}