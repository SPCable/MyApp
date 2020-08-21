package com.example.myapp.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapp.History.BuyHistory;
import com.example.myapp.R;

public class TaiKhoan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan);
    }





    public void onBackPressed(){
        super.onBackPressed();
    }
    public void btnBack(View view) {
        super.onBackPressed();
    }

    public void btnLichSuMua(View view) {
        if(view == findViewById(R.id.btnLichsumua)){
            startActivity(new Intent(this, BuyHistory.class));
        }
    }
    public void btnQuanLyThanhToan(View view) {
        if(view == findViewById(R.id.btnQuanlythanhtoan)){
            startActivity(new Intent(this, Payments.class));
        }
    }
}