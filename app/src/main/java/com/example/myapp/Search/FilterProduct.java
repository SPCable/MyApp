package com.example.myapp.Search;

import android.widget.Filter;

import java.util.ArrayList;

public class FilterProduct extends Filter {


    ItemSearchAdapter adapter;
    ArrayList<ItemSearch> filterList;

    public FilterProduct(ItemSearchAdapter adapter, ArrayList<ItemSearch> filterList) {
        this.adapter = adapter;
        this.filterList = filterList;
    }


    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {

        FilterResults results = new FilterResults();

        if(charSequence != null && charSequence.length() > 0)
        {
            charSequence = charSequence.toString().toUpperCase();

            ArrayList<ItemSearch> filteredModels = new ArrayList<>();
            for(int i=0; i<filterList.size(); i++)
            {
                if(filterList.get(i).getName().toUpperCase().contains(charSequence))
                {
                    filteredModels.add(filterList.get(i));
                }
            }

            results.count = filteredModels.size();
            results.values = filteredModels;
        }
        else
        {
            results.count = filterList.size();
            results.values = filterList;
        }


        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

        adapter.itemSearchList = (ArrayList<ItemSearch>) filterResults.values;

        adapter.notifyDataSetChanged();

    }
}
