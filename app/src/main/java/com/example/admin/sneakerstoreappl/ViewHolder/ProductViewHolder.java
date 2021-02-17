package com.example.admin.sneakerstoreappl.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.sneakerstoreappl.Interfaces.ItemClickListener;
import com.example.admin.sneakerstoreappl.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtProductName, txtProductDescription, txtProductPrice;
    public ImageView imageView;
    public ItemClickListener listener;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

        txtProductName = (TextView) itemView.findViewById(R.id.product_name_2);
        imageView = (ImageView) itemView.findViewById(R.id.product_image_2);
        txtProductPrice = (TextView) itemView.findViewById(R.id.product_price_2);
        txtProductDescription = (TextView) itemView.findViewById(R.id.product_description_2);
    }


    public void setItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onClick(View view) {
        listener.onClick(view, getAdapterPosition(), false);
    }

    //
    //
    //
}
