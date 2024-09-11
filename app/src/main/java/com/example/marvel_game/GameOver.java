package com.example.marvel_game;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameOver extends AppCompatActivity {
    private Button PlayAgain;
    private TextView tv;
    private String score;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_over);
        score = getIntent().getExtras().get("score").toString();
        PlayAgain = (Button) findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.displayScore);
        PlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(GameOver.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
        tv.setText("Score = "+ score);
    }
}