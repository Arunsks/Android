package com.example.programmer.ecommerce.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.programmer.ecommerce.InterfaceClass.ItemClickListener;
import com.example.programmer.ecommerce.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView displayproductname,displayproductdescription,displayproductprice;
    public ImageView displayproductImage;
    ItemClickListener listener;


    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

        displayproductname = (TextView) itemView.findViewById(R.id.display_product_name);
        displayproductprice = (TextView) itemView.findViewById(R.id.display_product_price);
        displayproductdescription = (TextView) itemView.findViewById(R.id.display_product_description);

        displayproductImage = (ImageView) itemView.findViewById(R.id.display_product_image);

    }

    public void setItemClickListener(ItemClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View view)
    {

    listener.onClick(view , getAdapterPosition() ,false);

    }
}
