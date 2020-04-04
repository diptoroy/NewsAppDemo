package com.atc.newsappfinalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.atc.newsappfinalproject.R;


public class SplashActivity extends AppCompatActivity {

    ImageView logo;
    ProgressBar splash_progressBar;

    private static int SPLASH_OUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo = findViewById(R.id.icon);
        splash_progressBar = findViewById(R.id.splash_progressBar);
        splash_progressBar.setVisibility(View.VISIBLE);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    splash_progressBar.setVisibility(View.INVISIBLE);
                    Intent next = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(next);
                    finish();

            }
        },SPLASH_OUT);
    }

}
