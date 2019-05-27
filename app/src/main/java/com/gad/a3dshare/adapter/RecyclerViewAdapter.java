package com.gad.a3dshare.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gad.a3dshare.R;
import com.gad.a3dshare.api.model.Design;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecycleViewHolder> {
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
        String imageUri = data.get(position).getImageUri();
        String description = data.get(position).getDescription();
        String dimensions = "Dimensions: " + data.get(position).getLength() + " X " +
                data.get(position).getHeight() + " X " + data.get(position).getWidth();
        String price = "Price: $" + data.get(position).getPrice();

        recycleViewHolder.designNameTextView.setText(name);
        Picasso.get().load(imageUri).into(recycleViewHolder.designImageView);
        recycleViewHolder.designDescriptionTextView.setText(description);
        recycleViewHolder.designDimensionsTextView.setText(dimensions);
        recycleViewHolder.designPriceTextView.setText(price);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class RecycleViewHolder extends RecyclerView.ViewHolder {

        final TextView designNameTextView;
        final TextView designDescriptionTextView;
        final TextView designDimensionsTextView;
        final TextView designPriceTextView;
        final ImageView designImageView;

        RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            this.designNameTextView = itemView.findViewById(R.id.design_name);
            this.designImageView = itemView.findViewById(R.id.image_view);
            this.designDescriptionTextView = itemView.findViewById(R.id.design_description);
            this.designDimensionsTextView = itemView.findViewById(R.id.design_dimension);
            this.designPriceTextView = itemView.findViewById(R.id.design_price);
        }
    }
}
