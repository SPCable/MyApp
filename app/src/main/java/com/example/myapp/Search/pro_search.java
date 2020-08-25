package com.example.myapp.Search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.myapp.FoodAdapter;
import com.example.myapp.Food_sp;
import com.example.myapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class pro_search extends AppCompatActivity {

    RecyclerView rv_search;
    ItemSearchAdapter itemSearchAdapter;

    private ArrayList<ItemSearch> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_search);


        LoadFoods();



    }


    private void setItemSearchRecycler(List<ItemSearch> itemSearchList)
    {

        rv_search = findViewById(R.id.food_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rv_search.setLayoutManager(layoutManager);
        itemSearchAdapter = new ItemSearchAdapter(this, itemSearchList);
        rv_search.setAdapter(itemSearchAdapter);
    }


    private void LoadFoods() {
        itemList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("productList");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemList.clear();
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    ItemSearch itemSearch = ds.getValue(ItemSearch.class);
                    itemList.add(itemSearch);
                }
                setItemSearchRecycler(itemList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    public boolean onCreateOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.searchmenu,menu);

        MenuItem item = menu.findItem(R.id.searchmenuuu);

        SearchView searchView =(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }

    private void processearch(String s)
    {
        //FirebaseRecyclerOptions<model>
    }







    public void onBackPressed(){
        super.onBackPressed();
    }
}