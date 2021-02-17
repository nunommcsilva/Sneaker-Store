package com.example.admin.sneakerstoreappl.ViewHolder;

import android.view.View;
import android.widget.TextView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.sneakerstoreappl.Interfaces.ItemClickListener;
import com.example.admin.sneakerstoreappl.R;

public class CartViewHolder
        extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    public TextView txtProductName, txtProductPrice, txtProductQuantity, txtProductSize, txtProductColor;
    private ItemClickListener itemClickListener;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        txtProductName = itemView.findViewById(R.id.cart_product_name);
        txtProductPrice = itemView.findViewById(R.id.cart_product_price);
        txtProductQuantity = itemView.findViewById(R.id.cart_product_quantity);
        txtProductSize = itemView.findViewById(R.id.cart_product_size);
        txtProductColor = itemView.findViewById(R.id.cart_product_color);
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view, getAdapterPosition(), false);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {

        this.itemClickListener = itemClickListener;
    }

    //
    //
    //
}
