package com.example.plantiside;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    TextView splashTextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        splashTextview = findViewById(R.id.splashName);
        Animation txtAnimation = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        splashTextview.setAnimation(txtAnimation);

        Intent toMain =new Intent(SplashActivity.this,MainActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splashTextview.startAnimation(txtAnimation);
                startActivity(toMain);
                finish();
            }
        },3000);
    }
}