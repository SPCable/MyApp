package com.example.myapp.Order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;

import java.util.List;


public class FoodOrderAdapter extends RecyclerView.Adapter<FoodOrderAdapter.FoodOrderViewHolder> {

    Context context;
    List<FoodOrder> foodorder_spList;

    public FoodOrderAdapter(Context context, List<FoodOrder> foodorder_spList) {
        this.context = context;
        this.foodorder_spList = foodorder_spList;
    }

    @NonNull
    @Override
    public FoodOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item,parent,false);
        return new FoodOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodOrderAdapter.FoodOrderViewHolder holder, final int position) {
        final FoodOrder foodOrder = foodorder_spList.get(position);
        final  String id = foodOrder.getId();
        holder.foodName.setText(foodorder_spList.get(position).nameFood);
        holder.foodprice.setText(foodorder_spList.get(position).foodPrice);
        holder.foodCount.setText(foodorder_spList.get(position).foodCount);
        holder.foodpriceO.setText(foodorder_spList.get(position).foodPriceO);
    }


    @Override
    public int getItemCount() {
        return foodorder_spList.size();
    }

    final class FoodOrderViewHolder extends RecyclerView.ViewHolder
    {
        Button btndel;
        TextView foodprice;
        TextView foodName;
        TextView foodCount;
        TextView foodpriceO;


        public FoodOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.food_name);
            foodprice = itemView.findViewById(R.id.food_price);
            foodCount = itemView.findViewById(R.id.foodCount);
            foodpriceO = itemView.findViewById(R.id.priceold);
        }
    }
}
