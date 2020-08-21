package com.example.myapp.TTSP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapp.R;
import com.example.myapp.Search.pro_search;

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
    }
    public void btnBack(View view) {
        super.onBackPressed();
    }
    public void btnSearch(View view) {
        if(view == findViewById(R.id.btnSearch_infoSP)){
            startActivity(new Intent(this, pro_search.class));
        }
    }
}