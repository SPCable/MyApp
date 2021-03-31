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

import com.squareup.picasso.Picasso;

import java.util.List;

public class BSAdapter extends RecyclerView.Adapter<BSAdapter.BSViewHolder> {

    OnBestListener mOnBestListener;

    Context context;
    List<bestseller> bestsellerList;

    public BSAdapter(Context context, List<bestseller> bestsellerList, OnBestListener onBestListener) {
        this.context = context;
        this.bestsellerList = bestsellerList;
        this.mOnBestListener = onBestListener;
    }


    @NonNull
    @Override
    public BSAdapter.BSViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bs_item,parent,false);
        return new BSAdapter.BSViewHolder(view, mOnBestListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BSAdapter.BSViewHolder holder, int position) {

        bestseller best = bestsellerList.get(position);
        holder.price.setText(bestsellerList.get(position).Price);
        holder.name.setText(bestsellerList.get(position).Name);

        String Img =  best.getImg();

        try
        {
            Picasso.get().load(Img).placeholder(R.drawable.beefsteak).into(holder.img);
        }
        catch (Exception ex)
        {
            holder.img.setImageResource(R.drawable.beef1);
        }
    }

    @Override
    public int getItemCount() {
        return bestsellerList.size();
    }

    public static final class BSViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView name;
        TextView price;
        ImageView img;

        OnBestListener onBestListener;

        public BSViewHolder(@NonNull View itemview, OnBestListener onBestListener)
        {
            super(itemview);
            name = itemview.findViewById(R.id.bs_name);
            price =  itemview.findViewById(R.id.bs_price);
            img =  itemview.findViewById(R.id.bs_img);

            this.onBestListener = onBestListener;
            itemview.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onBestListener.onBestClick(getAdapterPosition());
        }
    }
    public interface OnBestListener{
        void onBestClick(int position);
    }
}
