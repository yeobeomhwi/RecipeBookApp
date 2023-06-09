package com.example.recipebookapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private Context context;
    private List<ItemModel> itemModels;

    public ItemAdapter(Context context, List<ItemModel> itemModels) {
        this.context = context;
        this.itemModels = itemModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemModel itemModel = itemModels.get(position);
        holder.foodImg.setImageResource(itemModel.getImage());
        holder.itemTitle.setText(itemModel.getFood_title());
        holder.itemTime.setText(itemModel.getFood_time());
        holder.itemRatingBar.setRating(itemModel.getRating());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecipeActivity.class);
                intent.putExtra("itemTitle", itemModel.getFood_title());
                intent.putExtra("itemTime", itemModel.getFood_time());
                intent.putExtra("itemImage", itemModel.getImage());
                intent.putExtra("itemRating", itemModel.getRating());
                intent.putExtra("itemServings", itemModel.getServings());
                intent.putExtra("itemKcal", itemModel.getKcal());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImg;
        TextView itemTitle;
        TextView itemTime;
        RatingBar itemRatingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImg = itemView.findViewById(R.id.food_img);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemTime = itemView.findViewById(R.id.item_time);
            itemRatingBar = itemView.findViewById(R.id.item_ratingBar);
        }
    }
}
