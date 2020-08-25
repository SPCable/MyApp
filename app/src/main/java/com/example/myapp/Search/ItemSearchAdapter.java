package com.example.myapp.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Food_sp;
import com.example.myapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ItemSearchAdapter extends RecyclerView.Adapter<ItemSearchAdapter.ItemSearchViewHolder>{

    Context context;
    List<ItemSearch> itemSearchList;

    public ItemSearchAdapter(Context context, List<ItemSearch> itemSearchList) {
        this.context = context;
        this.itemSearchList = itemSearchList;
    }

    @NonNull
    @Override
    public ItemSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.search_item, parent, false);

        return new ItemSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemSearchViewHolder holder, int position) {
//        holder.productImage.setImageResource(itemSearchList.get(position).getImg());
//        holder.name.setText(itemSearchList.get(position).getName());


        ItemSearch itemSearch = itemSearchList.get(position);
        String name = itemSearch.getName();
        String Img = itemSearch.getImg();

        holder.name.setText(name);

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

    public static final class ItemSearchViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView name;

        public ItemSearchViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.search_image);
            name = itemView.findViewById(R.id.search_name);
        }
    }
}
