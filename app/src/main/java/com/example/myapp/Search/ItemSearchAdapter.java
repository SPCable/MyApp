package com.example.myapp.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Food_sp;
import com.example.myapp.R;
import com.example.myapp.bestseller;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemSearchAdapter extends RecyclerView.Adapter<ItemSearchAdapter.ItemSearchViewHolder> implements Filterable {
    Context context;
    ArrayList<ItemSearch> itemSearchList, filterList;
    FilterProduct filter;

    public ItemSearchAdapter(Context context, ArrayList<ItemSearch> itemSearchList){
        this.context = context;
        this.itemSearchList = itemSearchList;
        this.filterList = itemSearchList;
    }
    @NonNull
    @Override
    public ItemSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.search_item,parent,false);
        return new ItemSearchViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemSearchViewHolder holder, int position) {
        ItemSearch itemSearch = itemSearchList.get(position);
        holder.name.setText(itemSearchList.get(position).Name);

        String Img =  itemSearch.getImg();

        try
        {
            Picasso.get().load(Img).placeholder(R.drawable.beefsteak).into(holder.productImage);
        }
        catch (Exception ex)
        {
            holder.productImage.setImageResource(R.drawable.beef1);
        }
    }

    @Override
    public int getItemCount() {
        return itemSearchList.size();
    }

    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter = new FilterProduct(this, filterList);
        }
        return filter;
    }

    public class ItemSearchViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView name;

        public ItemSearchViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.search_image);
            name = itemView.findViewById(R.id.search_name);
        }
    }
}