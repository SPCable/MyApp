package com.example.myapp.StartApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.myapp.MainActivity;
import com.example.myapp.R;

public class LaunchApp extends AppCompatActivity {

    private static  int SPLASH_SCR = 1500;


    Animation topAnim,bottomAnim;
    ImageView img;
    TextView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launch_app);

//    topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
//    bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
//
//    img = findViewById(R.id.imglogo);
//    logo = findViewById(R.id.txtlogo);
//
//    img.setAnimation(topAnim);
//    logo.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LaunchApp.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        },SPLASH_SCR);

    }
}