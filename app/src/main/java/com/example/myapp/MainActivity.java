package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.myapp.Account.TaiKhoan;
import com.example.myapp.Order.DatHang;
import com.example.myapp.TTSP.ttsanphamtt;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements FoodAdapter.OnFoodListener {

    RecyclerView foodRecycler;
    RecyclerView bannerRecycler;
    RecyclerView saleRecycler;
    RecyclerView bsRecycler;
    FoodAdapter foodAdapter;
    BannerAdapter bannerAdapter;
    SaleAdapter saleAdapter;
    BSAdapter bsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Food_sp> food_sps = new ArrayList<>();
        food_sps.add(new Food_sp("1Thịt bò Mỹ-Canada-Nga", R.drawable.beefsteak));
        food_sps.add(new Food_sp("2Thịt bò Úc", R.drawable.beefsteak));
        food_sps.add(new Food_sp("3Thịt Cừu", R.drawable.beefsteak));
        food_sps.add(new Food_sp("4Thịt Trâu", R.drawable.beefsteak));
        food_sps.add(new Food_sp("5Thịt Heo", R.drawable.beefsteak));
        food_sps.add(new Food_sp("6Thịt Gà", R.drawable.beefsteak));
        food_sps.add(new Food_sp("7Thịt Dê", R.drawable.beefsteak));
        food_sps.add(new Food_sp("8Hải Sản", R.drawable.beefsteak));
        food_sps.add(new Food_sp("9Sản phẩm khác", R.drawable.beefsteak));
        setFoodRecycler(food_sps);

        List<banner> banners = new ArrayList<>();
        banners.add(new banner(R.drawable.beef1));
        banners.add(new banner(R.drawable.beef1));
        banners.add(new banner(R.drawable.beef1));
        banners.add(new banner(R.drawable.beef1));
        banners.add(new banner(R.drawable.beef1));
        setBannerRecycler(banners);

        List<sale> sales = new ArrayList<>();
        sales.add(new sale("aĐÙI CỪU CÓ XƯƠNG CHUMP ON (XX:ÚC)", "₫280,000.00", "₫250,000.00", R.drawable.mini_beef));
        sales.add(new sale("bĐÙI CỪU CÓ XƯƠNG CHUMP ON (XX:ÚC)", "₫280,000.00", "₫250,000.00", R.drawable.mini_beef));
        sales.add(new sale("cĐÙI CỪU CÓ XƯƠNG CHUMP ON (XX:ÚC)", "₫280,000.00", "₫250,000.00", R.drawable.mini_beef));
        sales.add(new sale("dĐÙI CỪU CÓ XƯƠNG CHUMP ON (XX:ÚC)", "₫280,000.00", "₫250,000.00", R.drawable.mini_beef));
        sales.add(new sale("eĐÙI CỪU CÓ XƯƠNG CHUMP ON (XX:ÚC)", "₫280,000.00", "₫250,000.00", R.drawable.mini_beef));
        sales.add(new sale("fĐÙI CỪU CÓ XƯƠNG CHUMP ON (XX:ÚC)", "₫280,000.00", "₫250,000.00", R.drawable.mini_beef));
        setSaleRecycler(sales);

        List<bestseller> bestsellers = new ArrayList<>();
        bestsellers.add(new bestseller("aThịt bò Mỹ-Canada-Nga", R.drawable.beef1, "đ100 000"));
        bestsellers.add(new bestseller("bThịt bò Mỹ-Canada-Nga", R.drawable.beefsteak, "đ100 000"));
        bestsellers.add(new bestseller("cThịt bò Mỹ-Canada-Nga", R.drawable.beefsteak, "đ100 000"));
        bestsellers.add(new bestseller("dThịt bò Mỹ-Canada-Nga", R.drawable.beefsteak, "đ100 000"));
        bestsellers.add(new bestseller("eThịt bò Mỹ-Canada-Nga", R.drawable.beefsteak, "đ100 000"));
        bestsellers.add(new bestseller("fThịt bò Mỹ-Canada-Nga", R.drawable.beefsteak, "đ100 000"));
        bestsellers.add(new bestseller("gThịt bò Mỹ-Canada-Nga", R.drawable.beefsteak, "đ100 000"));
        setBsRecycler(bestsellers);


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
            Animatoo.animateSlideLeft(this);
        }
    }

    public void btnMenu(View view) {
        if(view == findViewById(R.id.btnMenu_main)){
            startActivity(new Intent(this, TaiKhoan.class));
            Animatoo.animateSlideLeft(this);
        }
    }

    @Override
    public void onFoodClick(int position) {
        if(position == 0) {
            Intent intent = new Intent(this, ttsanphamtt.class);
            startActivity(intent);
            Animatoo.animateSlideLeft(this);
        }
    }
    /////////////////////////////////////////////////////////////////

}