package com.example.myapp.TTSP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.myapp.MainActivity;
import com.example.myapp.R;
import com.example.myapp.Search.pro_search;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

public class ttsanphamtt extends AppCompatActivity {

    RecyclerView rv_DSSP;
    ListProAdapter listProAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttsanphamtt);

        List<ListProduct> listProductList = new ArrayList<>();

        listProductList.add(new ListProduct("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)","Hiệu : Excel, National,Harris…","200,000,000₫",R.drawable.slice_beef));
        listProductList.add(new ListProduct("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)","Hiệu : Excel, National,Harris…","200,000,000₫",R.drawable.slice_beef));
        listProductList.add(new ListProduct("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)","Hiệu : Excel, National,Harris…","200,000,000₫",R.drawable.slice_beef));
        listProductList.add(new ListProduct("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)","Hiệu : Excel, National,Harris…","200,000,000₫",R.drawable.slice_beef));
        listProductList.add(new ListProduct("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)","Hiệu : Excel, National,Harris…","200,000,000₫",R.drawable.slice_beef));
        listProductList.add(new ListProduct("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)","Hiệu : Excel, National,Harris…","200,000,000₫",R.drawable.slice_beef));
        listProductList.add(new ListProduct("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)","Hiệu : Excel, National,Harris…","200,000,000₫",R.drawable.slice_beef));

        setListProRecycler(listProductList);


    }


    private void setListProRecycler(List<ListProduct> listProductList){

        rv_DSSP = findViewById(R.id.rvDSSP);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv_DSSP.setLayoutManager(layoutManager);
        listProAdapter = new ListProAdapter(this, listProductList);
        rv_DSSP.setAdapter(listProAdapter);

    }


    public void onBackPressed(){
        super.onBackPressed();
        Animatoo.animateSlideRight(this);
    }


    public void btnBack(View view) {
        super.onBackPressed();
        Animatoo.animateSlideRight(this);
    }

    public void btnSearch(View view) {
        if(view == findViewById(R.id.btnSearch_infoSP)){
            startActivity(new Intent(this, pro_search.class));
            Animatoo.animateSlideUp(this);
        }
    }
}