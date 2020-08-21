package com.example.myapp.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.myapp.R;

public class Payments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);
    }
    public void onBackPressed(){
        super.onBackPressed();
    }


    public void btnBack(View view) {
        super.onBackPressed();
    }
}