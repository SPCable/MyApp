package com.example.myapp.TTSP;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ListProAdapter extends RecyclerView.Adapter<ListProAdapter.ListProductViewHolder> {
    OnItemListener onItemListener;

    Context context;
    List<ListProduct> listProductList;

    public ListProAdapter(Context context, List<ListProduct> listProductList, OnItemListener onItemListener) {
        this.context = context;
        this.listProductList = listProductList;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ListProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_product_item, parent, false);

        return new ListProductViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListProductViewHolder holder, int position) {

        ListProduct listProduct =  listProductList.get(position);

        holder.name.setText(listProduct.getName());
        holder.brand.setText(listProduct.getBrand());
        holder.price.setText(listProduct.getPrice());

        String Img = listProduct.getImage();

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
        return listProductList.size();
    }

    public static final class ListProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView productImage;
        TextView name, price, brand;
        OnItemListener onItemListener;

        public ListProductViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_img);
            name = itemView.findViewById(R.id.product_name);
            brand = itemView.findViewById(R.id.product_brand);
            price = itemView.findViewById(R.id.product_price);

            this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemListener.OnItemClick(getAdapterPosition());
            System.out.println("1");
        }
    }


}
