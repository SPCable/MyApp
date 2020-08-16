package com.example.myapp.TTSP;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.R;

import java.util.ArrayList;

public class ListProAdapter extends RecyclerView.Adapter<ListProAdapter.LPAdapterViewHolder> {

    private ArrayList<String> mimageViewInfoProduct = new ArrayList<>();
    private ArrayList<String> mtxtProduct = new ArrayList<>();
    private ArrayList<String> mtxtBrand = new ArrayList<>();
    private ArrayList<String> mtxtPriceProduct = new ArrayList<>();
    private Context mContext;

    private OnLPAListener mOnLPAListener;

    public ListProAdapter(Context mContext, ArrayList<String> mimageViewInfoProduct, ArrayList<String> mtxtProduct, ArrayList<String> mtxtBrand, ArrayList<String> mtxtPriceProduct, OnLPAListener onLPAListener) {
        this.mimageViewInfoProduct = mimageViewInfoProduct;
        this.mtxtProduct = mtxtProduct;
        this.mtxtBrand = mtxtBrand;
        this.mtxtPriceProduct = mtxtPriceProduct;
        this.mContext = mContext;
        this.mOnLPAListener = onLPAListener;
    }

    @NonNull
    @Override
    public LPAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dssp_layout, parent, false);
        LPAdapterViewHolder holder = new LPAdapterViewHolder(view, mOnLPAListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LPAdapterViewHolder holder, int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(mimageViewInfoProduct.get(position))
                .into(holder.imageViewInfoProduct);

        holder.txtProduct.setText(mtxtProduct.get(position));
        holder.txtBrand.setText(mtxtBrand.get(position));
        holder.txtPriceProduct.setText(mtxtPriceProduct.get(position));



//        holder.ReLayProductInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Intent intent = Intent()
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mimageViewInfoProduct.size();
    }

    public class LPAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageViewInfoProduct;
        TextView txtProduct;
        TextView txtBrand;
        TextView txtPriceProduct;
        RelativeLayout ReLayProductInfo;

        OnLPAListener onLPAListener;

        public LPAdapterViewHolder(@NonNull View itemView, OnLPAListener onLPAListener) {
            super(itemView);

            imageViewInfoProduct=itemView.findViewById(R.id.imgSearch);
            txtProduct=itemView.findViewById(R.id.txtProduct);
            txtBrand=itemView.findViewById(R.id.txtBrand);
            txtPriceProduct=itemView.findViewById(R.id.txtPriceProduct);
            ReLayProductInfo=itemView.findViewById(R.id.ReLayProductInfo);

            this.onLPAListener = onLPAListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onLPAListener.onLPAClick(getAdapterPosition());
        }
    }
    public interface OnLPAListener{
        void onLPAClick(int position);
    }
}
