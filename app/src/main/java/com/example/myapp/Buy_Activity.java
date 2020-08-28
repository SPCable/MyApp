package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapp.Order.DatHang;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import p32929.androideasysql_library.Column;
import p32929.androideasysql_library.EasyDB;

public class Buy_Activity extends AppCompatActivity {
    private String nameItem, priceItem, imgItem;
    TextView name,price, count, tong;
    ImageView imageView;
    ImageButton btnUp,btnDown;
    Button btnAdd;
    Integer Count = 0;
    Integer tongtien = 0;
    private Context context;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_);
        name = findViewById(R.id.txtListInfoProduct);
        price = findViewById(R.id.priceItem);
        imageView = findViewById(R.id.imageView2);
        btnUp = findViewById(R.id.btnUp);
        btnDown = findViewById(R.id.btnDown);
        btnAdd = findViewById(R.id.btnAdd);
        count = findViewById(R.id.count);
        tong = findViewById(R.id.tongTien);


        nameItem = getIntent().getStringExtra("nameFood");
        imgItem = getIntent().getStringExtra("imgItem");
        priceItem = getIntent().getStringExtra("priceItem");


        try
        {
            Picasso.get().load(imgItem).placeholder(R.drawable.beefsteak).into(imageView);
        }
        catch (Exception ex)
        {
        }

        name.setText(nameItem);
        price.setText(priceItem+" đ" + "x" + Count.toString());
        count.setText("0");
        tong.setText("0"+" đ");

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Count++;
                tongtien = Integer.parseInt(priceItem )* Count;
                price.setText(priceItem+" đ" + " x " + Count.toString());
                count.setText(Count.toString());
                tong.setText(tongtien+" đ");
            }
        });

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Count--;
                tongtien = Integer.parseInt(priceItem) * Count;
                price.setText(priceItem+" đ" + " x " + Count.toString());
                count.setText(Count.toString());
                tong.setText(tongtien+" đ");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Count > 0) {
                    addData(nameItem,priceItem,tongtien.toString(),Count.toString());
                    Toast.makeText(Buy_Activity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    private  int itemId = 1;
    private void addData(String nameItem, String giagoc, String giatra, String soluong) {
            itemId++;
            EasyDB easyDB = EasyDB.init(Buy_Activity.this,"ITEM_DB")
                    .setTableName("ITEMS_TABLE")
                    .addColumn(new Column("itemName", new String[]{"text","not null"}))
                    .addColumn(new Column("itemPrice", new String[]{"text","not null"}))
                    .addColumn(new Column("itemFinal", new String[]{"text","not null"}))
                    .addColumn(new Column("itemNumber", new String[]{"text","not null"}))
                    .doneTableColumn();
            Boolean b= easyDB
                    .addData("itemName",nameItem)
                    .addData("itemPrice",giagoc)
                    .addData("itemFinal",giatra)
                    .addData("itemNumber",soluong)
                    .doneDataAdding();
    }

    public void orderBtn_main(View view) {
        if(view == findViewById(R.id.btnOrder)){
            startActivity(new Intent(this, DatHang.class));
        }
    }





    public void onBackPressed(){
        super.onBackPressed();
    }


    public void btnBack(View view) {
        super.onBackPressed();
        //Animatoo.animateSlideRight(this);
    }
}