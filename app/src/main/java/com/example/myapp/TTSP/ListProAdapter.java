package com.example.myapp.TTSP;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;

import java.util.List;


public class ListProAdapter extends RecyclerView.Adapter<ListProAdapter.ListProductViewHolder> {

    Context context;
    List<ListProduct> listProductList;

    public ListProAdapter(Context context, List<ListProduct> listProductList) {
        this.context = context;
        this.listProductList = listProductList;
    }

    @NonNull
    @Override
    public ListProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_product_item, parent, false);

        return new ListProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListProductViewHolder holder, int position) {

        holder.productImage.setImageResource(listProductList.get(position).getImage());
        holder.name.setText(listProductList.get(position).getName());
        holder.brand.setText(listProductList.get(position).getBrand());
        holder.price.setText(listProductList.get(position).getPrice());


    }

    @Override
    public int getItemCount() {
        return listProductList.size();
    }

    public static final class ListProductViewHolder extends RecyclerView.ViewHolder{

        ImageView productImage;
        TextView name, price, brand;

        public ListProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_img);
            name = itemView.findViewById(R.id.product_name);
            brand = itemView.findViewById(R.id.product_brand);
            price = itemView.findViewById(R.id.product_price);


        }
    }


}
