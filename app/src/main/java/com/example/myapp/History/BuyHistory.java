package com.example.myapp.History;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.myapp.R;

public class BuyHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_history);
    }




    public void onBackPressed(){
        super.onBackPressed();
    }
    public void btnBack(View view) {
        super.onBackPressed();
    }
}