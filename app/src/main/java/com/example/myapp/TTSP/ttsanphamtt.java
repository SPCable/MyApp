package com.example.myapp.TTSP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.myapp.MainActivity;
import com.example.myapp.R;

import java.util.ArrayList;

public class ttsanphamtt extends AppCompatActivity implements ListProAdapter.OnLPAListener {

    private ArrayList<String> mName = new ArrayList<>();
    private ArrayList<String> mBrand = new ArrayList<>();
    private ArrayList<String> mPrice = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttsanphamtt);

        initImageBitmaps();
    }

    private void initImageBitmaps(){



        mName.add("aBA CHỈ BÒ-BEEF PLADE (XX:USA)");
        mBrand.add("Hiệu : Excel, National,Harris…");
        mPrice.add("₫200,000.00");
        mImage.add("https://i.imgur.com/ECQbFw8.png");

        mName.add("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)");
        mBrand.add("Hiệu : Excel, National,Harris…");
        mPrice.add("₫200,000.00");
        mImage.add("https://i.imgur.com/ECQbFw8.png");

        mName.add("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)");
        mBrand.add("Hiệu : Excel, National,Harris…");
        mPrice.add("₫200,000.00");
        mImage.add("https://i.imgur.com/ECQbFw8.png");

        mName.add("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)");
        mBrand.add("Hiệu : Excel, National,Harris…");
        mPrice.add("₫200,000.00");
        mImage.add("https://i.imgur.com/ECQbFw8.png");

        mName.add("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)");
        mBrand.add("Hiệu : Excel, National,Harris…");
        mPrice.add("₫200,000.00");
        mImage.add("https://i.imgur.com/ECQbFw8.png");

        mName.add("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)");
        mBrand.add("Hiệu : Excel, National,Harris…");
        mPrice.add("₫200,000.00");
        mImage.add("https://i.imgur.com/ECQbFw8.png");

        mName.add("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)");
        mBrand.add("Hiệu : Excel, National,Harris…");
        mPrice.add("₫200,000.00");
        mImage.add("https://i.imgur.com/ECQbFw8.png");

        mName.add("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)");
        mBrand.add("Hiệu : Excel, National,Harris…");
        mPrice.add("₫200,000.00");
        mImage.add("https://i.imgur.com/ECQbFw8.png");

        mName.add("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)");
        mBrand.add("Hiệu : Excel, National,Harris…");
        mPrice.add("₫200,000.00");
        mImage.add("https://i.imgur.com/ECQbFw8.png");

        mName.add("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)");
        mBrand.add("Hiệu : Excel, National,Harris…");
        mPrice.add("₫200,000.00");
        mImage.add("https://i.imgur.com/ECQbFw8.png");

        mName.add("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)");
        mBrand.add("Hiệu : Excel, National,Harris…");
        mPrice.add("₫200,000.00");
        mImage.add("https://i.imgur.com/ECQbFw8.png");

        mName.add("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)");
        mBrand.add("Hiệu : Excel, National,Harris…");
        mPrice.add("₫200,000.00");
        mImage.add("https://i.imgur.com/ECQbFw8.png");

        mName.add("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)");
        mBrand.add("Hiệu : Excel, National,Harris…");
        mPrice.add("₫200,000.00");
        mImage.add("https://i.imgur.com/ECQbFw8.png");

        mName.add("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)");
        mBrand.add("Hiệu : Excel, National,Harris…");
        mPrice.add("₫200,000.00");
        mImage.add("https://i.imgur.com/ECQbFw8.png");

        mName.add("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)");
        mBrand.add("Hiệu : Excel, National,Harris…");
        mPrice.add("₫200,000.00");
        mImage.add("https://i.imgur.com/ECQbFw8.png");

        mName.add("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)");
        mBrand.add("Hiệu : Excel, National,Harris…");
        mPrice.add("₫200,000.00");
        mImage.add("https://i.imgur.com/ECQbFw8.png");

        mName.add("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)");
        mBrand.add("Hiệu : Excel, National,Harris…");
        mPrice.add("₫200,000.00");
        mImage.add("https://i.imgur.com/ECQbFw8.png");

        initListProduct();
    }

    private void initListProduct(){
        RecyclerView recyclerView = findViewById(R.id.rvDSSP);
        ListProAdapter adapter = new ListProAdapter(this, mImage, mName, mBrand, mPrice, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onLPAClick(int position) {
        if(position==0) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
}