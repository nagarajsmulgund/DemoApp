package com.demoapp;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.demoapp.models.DataItem;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataHolder> {
    private List<DataItem> dataItemList = new ArrayList<>();
    private Context context;
    private  RequestOptions requestOptions;

    public DataAdapter(List<DataItem> list, Context context) {
        dataItemList.clear();
        dataItemList.addAll(list);
        this.context = context;

        requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.no_img);
        requestOptions.error(R.drawable.no_img);
    }

    public void updateData(List<DataItem> list) {
        dataItemList.addAll(list);
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_data_item, parent, false);
        return new DataHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {
        DataItem dataItem = dataItemList.get(position);
        holder.title.setText(dataItem.getTitle());
        holder.description.setText(dataItem.getDescription());

        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(dataItem.getImageHref())
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView description;
        public ImageView thumbnail;

        public DataHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }


}