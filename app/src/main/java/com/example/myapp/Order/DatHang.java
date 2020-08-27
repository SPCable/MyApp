package com.example.myapp.Order;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

import com.example.myapp.R;

public class DatHang extends AppCompatActivity {

    RecyclerView foodRecycler;
    FoodOrderAdapter FoodOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_hang);
    }

    private void setFoodRecycler(List<FoodOrder> foodorder_spList)
    {
        foodRecycler = findViewById(R.id.bill_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        foodRecycler.setLayoutManager(layoutManager);
        FoodOrderAdapter = new FoodOrderAdapter(this,foodorder_spList);
        foodRecycler.setAdapter(FoodOrderAdapter);
    }




    public void onBackPressed(){
        super.onBackPressed();
    }
    public void btnBack(View view) {
        super.onBackPressed();
    }
}
