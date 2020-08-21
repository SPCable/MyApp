package com.example.myapp.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.myapp.R;

public class Payments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);
    }
    public void onBackPressed(){
        super.onBackPressed();
        Animatoo.animateSlideRight(this);
    }


    public void btnBack(View view) {
        super.onBackPressed();
        Animatoo.animateSlideRight(this);
    }
}