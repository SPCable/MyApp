package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BSAdapter extends RecyclerView.Adapter<BSAdapter.BSViewHolder> {
    Context context;
    List<bestseller> bestsellerList;

    public BSAdapter(Context context, List<bestseller> bestsellerList) {
        this.context = context;
        this.bestsellerList = bestsellerList;
    }

    @NonNull
    @Override
    public BSAdapter.BSViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bs_item,parent,false);
        return new BSAdapter.BSViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BSAdapter.BSViewHolder holder, int position) {
        holder.img.setImageResource(bestsellerList.get(position).Img);
        holder.price.setText(bestsellerList.get(position).Price);
        holder.name.setText(bestsellerList.get(position).Name);
    }

    @Override
    public int getItemCount() {
        return bestsellerList.size();
    }

    public static final class BSViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView price;
        ImageView img;

        public BSViewHolder(@NonNull View itemview)
        {
            super(itemview);
            name = itemview.findViewById(R.id.bs_name);
            price =  itemview.findViewById(R.id.bs_price);
            img =  itemview.findViewById(R.id.bs_img);
        }
    }
}
