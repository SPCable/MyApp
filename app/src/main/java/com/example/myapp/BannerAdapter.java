package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {
    Context context;
    List<banner> bannerList;

    public BannerAdapter(Context context, List<banner> bannerList) {
        this.context = context;
        this.bannerList = bannerList;
    }


    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item,parent,false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        banner blist = bannerList.get(position);
        String Img = blist.getImgBanner();
        try
        {
            Picasso.get().load(Img).placeholder(R.drawable.beefsteak).into(holder.bannerImg);
        }
        catch (Exception ex)
        {
            holder.bannerImg.setImageResource(R.drawable.beef1);
        }
    }

    @Override
    public int getItemCount() {
        return bannerList.size();
    }

    public  static final class BannerViewHolder extends RecyclerView.ViewHolder
    {
        ImageView bannerImg;


        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);

            bannerImg = itemView.findViewById(R.id.banner_img);

        }
    }
}
