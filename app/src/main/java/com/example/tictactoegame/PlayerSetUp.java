package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PlayerSetUp extends AppCompatActivity {
    Button submit;
    EditText playerOneName, playerTwoName;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_set_up);
        submit = findViewById(R.id.submit_btn);
        playerOneName = findViewById(R.id.player1Name1);
        playerTwoName = findViewById(R.id.player2Name2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String player1Name = playerOneName.getText().toString();
                String player2Name = playerTwoName.getText().toString();

                Intent intent2 = new Intent(PlayerSetUp.this, MainGameActivity.class);
                intent2.putExtra("PlayerNames", new String[]{player1Name, player2Name});
                startActivity(intent2);
            }
        });
    }
}