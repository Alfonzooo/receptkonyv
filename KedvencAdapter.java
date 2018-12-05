package com.example.pozsikmt.receptkonyv_v2.adapter;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pozsikmt.receptkonyv_v2.R;
import com.example.pozsikmt.receptkonyv_v2.data.ReceptItem;


import java.util.ArrayList;
import java.util.List;

public class KedvencAdapter
        extends RecyclerView.Adapter<KedvencAdapter.KedvencViewHolder> {

    private final List<ReceptItem> favitems;

    private KedvencClickListener listener;

    public KedvencAdapter(KedvencClickListener listener) {
        this.listener = listener;
        favitems = new ArrayList<>();
    }

    @NonNull
    @Override
    public KedvencViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_kedvenc_list, parent, false);
        return new KedvencViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull KedvencViewHolder holder, int position) {
        ReceptItem favitem = favitems.get(position);
        holder.favnameTextView.setText(favitem.name);
        holder.favdescriptionTextView.setText(favitem.description);
        holder.favcategoryTextView.setText(favitem.category.name());
        holder.faviconImageView.setImageResource(holder.getfavImageResource(favitem.category));

        holder.item = favitem;
    }

    @Override
    public int getItemCount() {
        return favitems.size();
    }

    public interface KedvencClickListener{
        void onItemChanged(ReceptItem item);
        void onItemRemoved(ReceptItem item);
    }

    class KedvencViewHolder extends RecyclerView.ViewHolder {

        ImageView faviconImageView;
        TextView favnameTextView;
        TextView favdescriptionTextView;
        TextView favcategoryTextView;


        ReceptItem item;

        KedvencViewHolder(View itemView) {
            super(itemView);
            faviconImageView = itemView.findViewById(R.id.ReceptItemIconImageView);
            favnameTextView = itemView.findViewById(R.id.ReceptItemNameTextView);
            favdescriptionTextView = itemView.findViewById(R.id.ReceptItemDescriptionTextView);
            favcategoryTextView = itemView.findViewById(R.id.ReceptItemCategoryTextView);
        }

        private @DrawableRes
        int getfavImageResource(ReceptItem.Category category) {
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
    }

        public void update(List<ReceptItem> receptItems) {
            favitems.clear();
            favitems.addAll(receptItems);
            notifyDataSetChanged();
        }

}