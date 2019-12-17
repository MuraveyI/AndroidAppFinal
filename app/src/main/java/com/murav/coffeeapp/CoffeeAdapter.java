package com.murav.coffeeapp;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.murav.coffeeapp.model.CoffeeModel;

import java.util.List;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.ViewHolder> {

    public interface ClickListener {
        void onClick(int position);
    }

    private ClickListener clickListener;

    List<CoffeeModel> mlist;

    private Context mContext;

    CoffeeAdapter(List<CoffeeModel> mlist) {
        this.mlist = mlist;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.coffee_model_items, viewGroup, false);

        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CoffeeModel coffeeModel = mlist.get(i);
        Resources res = viewHolder.itemView.getContext().getResources();

        String packageName = viewHolder.itemView.getContext().getPackageName();
        String imageName = (coffeeModel.getImage() == null) ? "allcoffee" : coffeeModel.getImage();
        //viewHolder.item_image.setImageResource(res.getIdentifier(imageName, "drawable", packageName));
        viewHolder.item_name.setText(coffeeModel.getName());
        int descSize = coffeeModel.getDescription().length();
        int lastOrLimited = (descSize < 20) ? descSize : 20;
        viewHolder.item_place.setText(coffeeModel.getDescription().substring(0, lastOrLimited));
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_image;
        TextView item_name, item_place;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_image = itemView.findViewById(R.id.item_image);

            item_name = itemView.findViewById(R.id.item_name);
            item_place = itemView.findViewById(R.id.item_place);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(getAdapterPosition());
                }
            });

        }
    }
}
