package com.example.myapp.Search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


import com.example.myapp.R;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class pro_search extends AppCompatActivity {

    RecyclerView rv_search;
    EditText editText;

    ItemSearchAdapter itemSearchAdapter;
    ArrayList<ItemSearch> itemSearchesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_search);

        editText = findViewById(R.id.editTimKiemSP);
        rv_search = findViewById(R.id.rvSearch);

        LoadSearch();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    itemSearchAdapter.getFilter().filter(charSequence);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void LoadSearch(){
        itemSearchesList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("saleList");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemSearchesList.clear();
                for (DataSnapshot ds : snapshot.getChildren())
                {
                    ItemSearch is = ds.getValue(ItemSearch.class);
                    itemSearchesList.add(is);
                }
                setItemSearchRecycler(itemSearchesList);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void setItemSearchRecycler(ArrayList<ItemSearch> itemSearchList)
    {

        rv_search = findViewById(R.id.rvSearch);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rv_search.setLayoutManager(layoutManager);
        itemSearchAdapter = new ItemSearchAdapter(this, itemSearchList);
        rv_search.setAdapter(itemSearchAdapter);
    }
    public void onBackPressed(){
        super.onBackPressed();
    }
}