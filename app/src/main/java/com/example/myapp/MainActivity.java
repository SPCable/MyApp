package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView foodRecycler;
    RecyclerView bannerRecycler;
    RecyclerView saleRecycler;
    RecyclerView bsRecycler;
    FoodAdapter foodAdapter;
    BannerAdapter bannerAdapter;
    SaleAdapter saleAdapter;
    BSAdapter bsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Food_sp> food_sps = new ArrayList<>();
        food_sps.add(new Food_sp("Thịt bò Mỹ-Canada-Nga", R.drawable.beefsteak));
        food_sps.add(new Food_sp("Thịt bò Úc", R.drawable.beefsteak));
        food_sps.add(new Food_sp("Thịt Cừu", R.drawable.beefsteak));
        food_sps.add(new Food_sp("Thịt Trâu", R.drawable.beefsteak));
        food_sps.add(new Food_sp("Thịt Heo", R.drawable.beefsteak));
        food_sps.add(new Food_sp("Thịt Gà", R.drawable.beefsteak));
        food_sps.add(new Food_sp("Thịt Dê", R.drawable.beefsteak));
        food_sps.add(new Food_sp("Hải Sản", R.drawable.beefsteak));
        food_sps.add(new Food_sp("Sản phẩm khác", R.drawable.beefsteak));
        setFoodRecycler(food_sps);

        List<banner> banners = new ArrayList<>();
        banners.add(new banner(R.drawable.beef1));
        banners.add(new banner(R.drawable.beef1));
        banners.add(new banner(R.drawable.beef1));
        banners.add(new banner(R.drawable.beef1));
        banners.add(new banner(R.drawable.beef1));
        setBannerRecycler(banners);

        List<sale> sales = new ArrayList<>();
        sales.add(new sale("ĐÙI CỪU CÓ XƯƠNG CHUMP ON (XX:ÚC)", "₫280,000.00","₫250,000.00",R.drawable.mini_beef));
        sales.add(new sale("ĐÙI CỪU CÓ XƯƠNG CHUMP ON (XX:ÚC)", "₫280,000.00","₫250,000.00",R.drawable.mini_beef));
        sales.add(new sale("ĐÙI CỪU CÓ XƯƠNG CHUMP ON (XX:ÚC)", "₫280,000.00","₫250,000.00",R.drawable.mini_beef));
        sales.add(new sale("ĐÙI CỪU CÓ XƯƠNG CHUMP ON (XX:ÚC)", "₫280,000.00","₫250,000.00",R.drawable.mini_beef));
        sales.add(new sale("ĐÙI CỪU CÓ XƯƠNG CHUMP ON (XX:ÚC)", "₫280,000.00","₫250,000.00",R.drawable.mini_beef));
        sales.add(new sale("ĐÙI CỪU CÓ XƯƠNG CHUMP ON (XX:ÚC)", "₫280,000.00","₫250,000.00",R.drawable.mini_beef));
        setSaleRecycler(sales);

        List<bestseller> bestsellers = new ArrayList<>();
        bestsellers.add(new bestseller("Thịt bò Mỹ-Canada-Nga", R.drawable.beef1,"đ100 000"));
        bestsellers.add(new bestseller("Thịt bò Mỹ-Canada-Nga", R.drawable.beefsteak,"đ100 000"));
        bestsellers.add(new bestseller("Thịt bò Mỹ-Canada-Nga", R.drawable.beefsteak,"đ100 000"));
        bestsellers.add(new bestseller("Thịt bò Mỹ-Canada-Nga", R.drawable.beefsteak,"đ100 000"));
        setBsRecycler(bestsellers);

    }

    private void setFoodRecycler(List<Food_sp> food_spList)
    {
        foodRecycler = findViewById(R.id.food_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        foodRecycler.setLayoutManager(layoutManager);
        foodAdapter = new FoodAdapter(this,food_spList);
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
}