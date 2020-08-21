package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.SaleViewHoler> {
    Context context;
    List<sale> sales;

    public SaleAdapter(Context context, List<sale> sales) {
        this.context = context;
        this.sales = sales;
    }

    @NonNull
    @Override
    public SaleViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sale_item, parent, false);
        return new SaleAdapter.SaleViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleViewHoler holder, int position) {
        holder.name.setText(sales.get(position).Name);
        holder.img.setImageResource(sales.get(position).Img);
        holder.price.setText(sales.get(position).Price);
        holder.discount.setText(sales.get(position).Discount);
    }


    @Override
    public int getItemCount() {
        return sales.size();
    }

    public final class SaleViewHoler extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView price;
        TextView discount;

        public SaleViewHoler(@NonNull View itemview) {
            super(itemview);
            img = itemview.findViewById(R.id.sale_img);
            name = itemview.findViewById(R.id.sale_name);
            price = itemview.findViewById(R.id.price);
            discount = itemview.findViewById(R.id.sale_price);

        }
    }
}