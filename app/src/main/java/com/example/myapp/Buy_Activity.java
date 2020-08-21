package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.myapp.Order.DatHang;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class Buy_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

    }

    public void orderBtn_main(View view) {
        if(view == findViewById(R.id.btnOrder)){
            startActivity(new Intent(this, DatHang.class));
            Animatoo.animateSlideLeft(this);
        }
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