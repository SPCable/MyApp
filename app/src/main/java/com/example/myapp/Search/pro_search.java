package com.example.myapp.Search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapp.R;

import java.util.ArrayList;
import java.util.List;

public class pro_search extends AppCompatActivity {

    RecyclerView rv_search;
    ItemSearchAdapter itemSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_search);


        List<ItemSearch> itemSearchList = new ArrayList<>();

        itemSearchList.add(new ItemSearch("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)",R.drawable.slice_beef));
        itemSearchList.add(new ItemSearch("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)",R.drawable.slice_beef));
        itemSearchList.add(new ItemSearch("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)",R.drawable.slice_beef));
        itemSearchList.add(new ItemSearch("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)",R.drawable.slice_beef));
        itemSearchList.add(new ItemSearch("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)",R.drawable.slice_beef));
        itemSearchList.add(new ItemSearch("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)",R.drawable.slice_beef));
        itemSearchList.add(new ItemSearch("BA CHỈ BÒ-BEEF SHORT PLADE (XX:USA)",R.drawable.slice_beef));

        setItemSearchRecycler(itemSearchList);


    }


    private void setItemSearchRecycler(List<ItemSearch> itemSearchList){

        rv_search = findViewById(R.id.rvSearch);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv_search.setLayoutManager(layoutManager);
        itemSearchAdapter = new ItemSearchAdapter(this, itemSearchList);
        rv_search.setAdapter(itemSearchAdapter);

    }

}