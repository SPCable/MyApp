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

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    Context context;
    List<Food_sp> food_spList;

    public FoodAdapter(Context context, List<Food_sp> food_spList) {
        this.context = context;
        this.food_spList = food_spList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_item,parent,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        holder.foodImg.setImageResource(food_spList.get(position).ImgUrl);
        holder.foodName.setText(food_spList.get(position).nameFood);

    }

    @Override
    public int getItemCount() {
        return food_spList.size();
    }

    public  static final class FoodViewHolder extends RecyclerView.ViewHolder
    {
        ImageView foodImg;
        TextView foodName;


        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImg = itemView.findViewById(R.id.food_img);
            foodName = itemView.findViewById(R.id.food_name);

        }
    }
}
