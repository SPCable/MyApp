package com.example.myapp;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    OnFoodListener mOnFoodListener;

    Context context;
    List<Food_sp> food_spList;


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


        Food_sp food_sp = food_spList.get(position);
        String name = food_sp.getNameFood();
        String Img = food_sp.getImgUrl();

        holder.foodName.setText(name);

        try
        {
            Picasso.get().load(Img).placeholder(R.drawable.beefsteak).into(holder.foodImg);
        }
        catch (Exception ex)
        {
            holder.foodImg.setImageResource(R.drawable.beef1);
        }

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
            onFoodListener.onFoodClick(getAdapterPosition());
        }
    }

    public interface OnFoodListener{
        void onFoodClick(int position);
    }
}
