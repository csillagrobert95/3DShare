package com.gad.a3dshare.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gad.a3dshare.R;
import com.gad.a3dshare.api.model.Design;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecycleViewHolder>{
    private final List<Design> data;

    public RecyclerViewAdapter(List<Design> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View recyclerRow = layoutInflater.inflate(R.layout.item_design, viewGroup, false);
        return new RecycleViewHolder(recyclerRow);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder recycleViewHolder, int position) {
        String name = data.get(position).getName();
        recycleViewHolder.designNameTextView.setText(name);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class RecycleViewHolder extends RecyclerView.ViewHolder {

        final TextView designNameTextView;

        RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            this.designNameTextView = itemView.findViewById(R.id.design_name);
        }
    }
}
