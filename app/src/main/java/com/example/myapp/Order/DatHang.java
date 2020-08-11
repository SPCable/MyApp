package com.example.myapp.Order;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


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
        List<FoodOrder> foodorder_sps = new ArrayList<>();
        foodorder_sps.add(new FoodOrder("Thịt bò Mỹ-Canada-Nga", "500000"));
        foodorder_sps.add(new FoodOrder("Thịt bò Úc", "500000"));
        foodorder_sps.add(new FoodOrder("Thịt Cừu", "500000"));
        foodorder_sps.add(new FoodOrder("Thịt Trâu", "500000"));
        foodorder_sps.add(new FoodOrder("Thịt Heo", "500000"));
        foodorder_sps.add(new FoodOrder("Thịt Gà", "500000"));
        foodorder_sps.add(new FoodOrder("Thịt Dê","500000"));
        foodorder_sps.add(new FoodOrder("Hải Sản", "500000"));
        foodorder_sps.add(new FoodOrder("Sản phẩm khác", "500000"));
        setFoodRecycler(foodorder_sps);



    }

    private void setFoodRecycler(List<FoodOrder> foodorder_spList)
    {
        foodRecycler = findViewById(R.id.bill_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        foodRecycler.setLayoutManager(layoutManager);
        FoodOrderAdapter = new FoodOrderAdapter(this,foodorder_spList);
        foodRecycler.setAdapter(FoodOrderAdapter);
    }

}
