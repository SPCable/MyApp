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

    private OnFoodListener mOnFoodListener;

    public FoodAdapter(Context context, List<Food_sp> food_spList, OnFoodListener onFoodListener) {
        this.context = context;
        this.food_spList = food_spList;
        this.mOnFoodListener = onFoodListener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_item,parent,false);
        return new FoodViewHolder(view, mOnFoodListener);
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

    public  static final class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView foodImg;
        TextView foodName;

        OnFoodListener onFoodListener;

        public FoodViewHolder(@NonNull View itemView, OnFoodListener onFoodListener) {
            super(itemView);

            foodImg = itemView.findViewById(R.id.food_img);
            foodName = itemView.findViewById(R.id.food_name);
            this.onFoodListener = onFoodListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onFoodListener.onFoodLClick(getAdapterPosition());
        }
    }
    public interface OnFoodListener{
        void onFoodLClick(int position);
    }
}
