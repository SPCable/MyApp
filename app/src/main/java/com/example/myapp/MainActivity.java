package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapp.Account.TaiKhoan;
import com.example.myapp.Order.DatHang;
import com.example.myapp.TTSP.ttsanphamtt;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements FoodAdapter.OnFoodListener {
    Button btnOrder_main, btnMenu_main;
    RecyclerView foodRecycler;
    RecyclerView bannerRecycler;
    RecyclerView saleRecycler;
    RecyclerView bsRecycler;
    FoodAdapter foodAdapter;
    BannerAdapter bannerAdapter;
    SaleAdapter saleAdapter;
    BSAdapter bsAdapter;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    private ArrayList<Food_sp> foodList;
    private ArrayList<sale> sales;
    private ArrayList<bestseller> bestsellerArrayList;
    private ArrayList<banner> bannerArrayList;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth =  firebaseAuth.getInstance();

        LoadFoods();
        LoadSales();
        LoadBanner();
        LoadBS();

    }

    private void LoadFoods() {
        foodList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("productList");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                foodList.clear();
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    Food_sp food_sp = ds.getValue(Food_sp.class);
                    foodList.add(food_sp);
                }
                setFoodRecycler(foodList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    private void LoadSales()
    {
        sales = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("saleList");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sales.clear();
                for(DataSnapshot ds : snapshot.getChildren())
                {
                    sale sale = ds.getValue(sale.class);
                    sales.add(sale);
                }
                setSaleRecycler(sales);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void LoadBS()
    {
        bestsellerArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("bestsalerList");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bestsellerArrayList.clear();
                for(DataSnapshot ds : snapshot.getChildren())
                {
                    bestseller bsl = ds.getValue(bestseller.class);
                    bestsellerArrayList.add(bsl);
                }
                setBsRecycler(bestsellerArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void LoadBanner()
    {
        bannerArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("BannerList");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bannerArrayList.clear();
                for(DataSnapshot ds : snapshot.getChildren())
                {
                    banner bn = ds.getValue(banner.class);
                    bannerArrayList.add(bn);
                }
                setBannerRecycler(bannerArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void setFoodRecycler(List<Food_sp> food_spList)
    {

        foodRecycler = findViewById(R.id.food_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        foodRecycler.setLayoutManager(layoutManager);
        foodAdapter = new FoodAdapter(this, food_spList, this);
        foodRecycler.setAdapter(foodAdapter);
    }

    private void setBannerRecycler(List<banner> bannerList)
    {
        bannerRecycler = findViewById(R.id.banner_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        bannerRecycler.setLayoutManager(layoutManager);
        bannerAdapter = new BannerAdapter(this, bannerList);
        bannerRecycler.setAdapter(bannerAdapter);
    }

    private void setSaleRecycler(List<sale> saleList)
    {
        saleRecycler = findViewById(R.id.sale_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        saleRecycler.setLayoutManager(layoutManager);
        saleAdapter = new SaleAdapter(this, saleList);
        saleRecycler.setAdapter(saleAdapter);
    }

    private void setBsRecycler(List<bestseller> bestsellerList)
    {
        bsRecycler = findViewById(R.id.bs_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        bsRecycler.setLayoutManager(layoutManager);
        bsAdapter = new BSAdapter(this, bestsellerList);
        bsRecycler.setAdapter(bsAdapter);
    }



    //////////////////////Click Event////////////////////////////////

    public void orderBtn_main(View view) {
        if(view == findViewById(R.id.btnOrder_main)){
            startActivity(new Intent(this, DatHang.class));
        }
    }

    public void btnMenu(View view) {
        if(view == findViewById(R.id.btnMenu_main)){
            startActivity(new Intent(this, TaiKhoan.class));
        }
    }

    @Override
    public void onFoodClick(int position) {
        if(position == 0) {
            Intent intent = new Intent(this, ttsanphamtt.class);
            startActivity(intent);
        }
    }
    /////////////////////////////////////////////////////////////////

}