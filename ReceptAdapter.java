package com.example.pozsikmt.receptkonyv_v2.adapter;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pozsikmt.receptkonyv_v2.R;
import com.example.pozsikmt.receptkonyv_v2.data.ReceptItem;


import java.util.ArrayList;
import java.util.List;

public class ReceptAdapter
        extends RecyclerView.Adapter<ReceptAdapter.ReceptViewHolder> {

    private final List<ReceptItem> items;

    private ReceptItemClickListener listener;

    public ReceptAdapter(ReceptItemClickListener listener) {
        this.listener = listener;
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public ReceptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_recept_list, parent, false);
        return new ReceptViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceptViewHolder holder, int position) {
        ReceptItem item = items.get(position);
        holder.nameTextView.setText(item.name);
        holder.descriptionTextView.setText(item.description);
        holder.categoryTextView.setText(item.category.name());
        holder.iconImageView.setImageResource(getImageResource(item.category));
        holder.isFavouriteCheckBox.setChecked(item.isFavourite);


        holder.item = item;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface ReceptItemClickListener{
        void onItemChanged(ReceptItem item);
        void onItemRemoved(ReceptItem item);
    }

    class ReceptViewHolder extends RecyclerView.ViewHolder {

        ImageView iconImageView;
        TextView nameTextView;
        TextView descriptionTextView;
        TextView categoryTextView;
        ImageButton removeButton;
        CheckBox isFavouriteCheckBox;

        ReceptItem item;

        ReceptViewHolder(View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.ReceptItemIconImageView);
            nameTextView = itemView.findViewById(R.id.ReceptItemNameTextView);
            descriptionTextView = itemView.findViewById(R.id.ReceptItemDescriptionTextView);
            categoryTextView = itemView.findViewById(R.id.ReceptItemCategoryTextView);
            removeButton = itemView.findViewById(R.id.ReceptItemRemoveButton);
            isFavouriteCheckBox = itemView.findViewById(R.id.ReceptItemFavouriteCheckbox);


            isFavouriteCheckBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                    if(item != null){
                        item.isFavourite = isChecked;
                        listener.onItemChanged(item);
                    }
                }
            });

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem(getLayoutPosition());
                }
            });

        }
    }

    private @DrawableRes
    int getImageResource(ReceptItem.Category category) {
        @DrawableRes int ret;
        switch (category) {
            case APPETIZER:
                ret = R.drawable.profile;
                break;
            case MAIN:
                ret = R.drawable.profile;
                break;
            case DESSERT:
                ret = R.drawable.profile;
                break;
            default:
                ret = 0;
        }
        return ret;
    }

    public void addItem(ReceptItem item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void update(List<ReceptItem> receptItems) {
        items.clear();
        items.addAll(receptItems);
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        ReceptItem toRemove = items.remove(position);
        notifyItemRemoved(position);
        listener.onItemRemoved(toRemove);
    }

}