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

import com.example.myapp.FoodAdapter;
import com.example.myapp.Food_sp;
import com.example.myapp.R;
import com.example.myapp.bestseller;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemSearchAdapter extends RecyclerView.Adapter<ItemSearchAdapter.ItemSearchViewHolder> implements Filterable {

    OnSearchListener mOnSearchListener;

    Context context;
    ArrayList<ItemSearch> itemSearchList, filterList;
    FilterProduct filter;

    public ItemSearchAdapter(Context context, ArrayList<ItemSearch> itemSearchList, OnSearchListener onSearchListener){
        this.context = context;
        this.itemSearchList = itemSearchList;
        this.filterList = itemSearchList;
        this.mOnSearchListener = onSearchListener;
    }
    @NonNull
    @Override
    public ItemSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.search_item,parent,false);
        return new ItemSearchViewHolder(view, mOnSearchListener);

    }


    @Override
    public void onBindViewHolder(@NonNull ItemSearchViewHolder holder, int position) {
        ItemSearch itemSearch = itemSearchList.get(position);
        holder.productName.setText(itemSearchList.get(position).name);

        String Img =  itemSearch.getImage();

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

    public class ItemSearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView productImage;
        TextView productName;
        OnSearchListener onSearchListener;

        public ItemSearchViewHolder(@NonNull View itemView, OnSearchListener onSearchListener) {
            super(itemView);

            productImage = itemView.findViewById(R.id.search_image);
            productName = itemView.findViewById(R.id.search_name);


            this.onSearchListener = onSearchListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onSearchListener.onSearchClick(getAdapterPosition());
        }
    }

    public interface OnSearchListener{
        void onSearchClick(int position);
    }
}