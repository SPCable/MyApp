package com.example.myapp.Order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;

import java.util.List;

import p32929.androideasysql_library.Column;
import p32929.androideasysql_library.EasyDB;


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
    public void onBindViewHolder(@NonNull final FoodOrderAdapter.FoodOrderViewHolder holder, final int position) {
        final FoodOrder foodOrder = foodorder_spList.get(position);
        final  String id = foodOrder.getId();
        final String cost = foodOrder.getFoodPrice();
        holder.foodName.setText(foodorder_spList.get(position).nameFood);
        holder.foodprice.setText(foodorder_spList.get(position).foodPrice);
        holder.foodCount.setText(foodorder_spList.get(position).foodCount);
        holder.foodpriceO.setText(foodorder_spList.get(position).foodPriceO);

        try
        {
            holder.btndel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EasyDB easyDB = EasyDB.init(context,"ITEM_DB")
                            .setTableName("ITEMS_TABLE")
                            .addColumn(new Column("ID", new String[]{"text","unique"}))
                            .addColumn(new Column("itemName", new String[]{"text","not null"}))
                            .addColumn(new Column("itemPrice", new String[]{"text","not null"}))
                            .addColumn(new Column("itemFinal", new String[]{"text","not null"}))
                            .addColumn(new Column("itemNumber", new String[]{"text","not null"}))
                            .doneTableColumn();

                    easyDB.deleteRow(1, id);

                    foodorder_spList.remove(position);
                    notifyItemChanged(position);
                    notifyDataSetChanged();

                    Integer tx = Integer.parseInt(((DatHang)context).tongtien.getText().toString().trim().replace(" đ", ""));
                    Integer total = tx - Integer.parseInt(cost.replace(" đ",""));

                    ((DatHang)context).tongtien.setText(total.toString()+" đ");
                }
            });
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }


    @Override
    public int getItemCount() {
        return foodorder_spList.size();
    }

    final class FoodOrderViewHolder extends RecyclerView.ViewHolder
    {
        ImageButton btndel;
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
            btndel = itemView.findViewById(R.id.btnDel);
        }
    }
}
