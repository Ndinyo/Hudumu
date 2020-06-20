package com.example.franklin.hudumu.utils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.franklin.hudumu.R;
import com.example.franklin.hudumu.authentication.LoginHuduma;

public class SplashScreen extends AppCompatActivity {
    Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        handler =  new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LoginHuduma.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}
