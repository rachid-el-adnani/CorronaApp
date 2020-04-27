package com.example.coronaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splashscreen extends AppCompatActivity {

    //variables
    private static int duration = 4000;
    Animation topAnim, bottomAnim;
    ImageView logo, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);

        //Hooks
        logo = findViewById(R.id.logo);
        slogan = findViewById(R.id.slogan);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.animation);

        //Set animation to elements
        logo.setAnimation(topAnim);
        slogan.setAnimation(bottomAnim);

        //Switching to the Registration screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splashscreen.this, Register.class);
                startActivity(intent);
                finish();
            }
        }, duration);

    }
}
