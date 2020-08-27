package com.example.myapp.TTSP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp.Food_sp;
import com.example.myapp.R;
import com.example.myapp.Search.pro_search;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ttsanphamtt extends AppCompatActivity implements OnItemListener {

    RecyclerView rv_DSSP;
    ListProAdapter listProAdapter;
    private String productId;
    private String productImg;
    private String productName;

    ArrayList<ListProduct> listProducts;
    TextView nameTop, nameTop1;
    ImageView imgTop;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttsanphamtt);

        nameTop = findViewById(R.id.txtListInfoProduct);
        nameTop1 = findViewById(R.id.textView18);
        imgTop = findViewById(R.id.imageView10);

        productId =  getIntent().getStringExtra("ProductUi");
        productName =  getIntent().getStringExtra("nameProduct");
        productImg =  getIntent().getStringExtra("imageFood");

        nameTop.setText(productName);
        nameTop1.setText(productName);

        try
        {
            Picasso.get().load(productImg).placeholder(R.drawable.beefsteak).into(imgTop);
        }
        catch (Exception ex)
        {
            imgTop.setImageResource(R.drawable.beef1);
        }



        loadProduct();

    }


    private void setListProRecycler(List<ListProduct> listProductList){

        rv_DSSP = findViewById(R.id.rvDSSP);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv_DSSP.setLayoutManager(layoutManager);
        listProAdapter = new ListProAdapter(this, listProductList, this);
        rv_DSSP.setAdapter(listProAdapter);

    }

    private void loadProduct()
    {
        System.out.println(productId);
        listProducts = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("productList").child(productId).child("Product");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listProducts.clear();
                for(DataSnapshot ds : snapshot.getChildren()) {
                    ListProduct listProduct = ds.getValue(ListProduct.class);
                    listProducts.add(listProduct);
                }
                setListProRecycler(listProducts);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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

    @Override
    public void OnItemClick(int position) {
        
    }
}