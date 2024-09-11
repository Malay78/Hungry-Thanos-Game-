package com.example.marvel_game;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
   Thread thread = new Thread(){
       @Override
       public void run() {
           try {
                sleep(3000);
           }
           catch (Exception e){
                e.printStackTrace();
           }
           finally {
               Intent i = new Intent(SplashActivity.this, Rules.class);
               startActivity(i);
           }
       }
   };
   thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}