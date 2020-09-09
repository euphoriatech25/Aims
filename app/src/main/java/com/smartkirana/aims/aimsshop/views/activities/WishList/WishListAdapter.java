package com.smartkirana.aims.aimsshop.views.activities.WishList;

import android.content.Context;
import android.graphics.Paint;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.network.RetrofitInterface;
import com.smartkirana.aims.aimsshop.views.fragments.FeaturedProduct.HomeFeaturedModel;

import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.RecyclerViewCartHolder> {
    Context context;
    List<HomeFeaturedModel.Featured> products;
    RetrofitInterface retrofitInterface;


    private WishListAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position, View itemView);
    }

    public void setOnItemClickListener(WishListAdapter.OnItemClickListener listener) {
        mListener = listener;
    }


    public WishListAdapter(Context context, List<HomeFeaturedModel.Featured> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public RecyclerViewCartHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_wishlist, viewGroup, false);
        return new RecyclerViewCartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishListAdapter.RecyclerViewCartHolder holder, int position) {
        holder.wishlist_product_name.setText(products.get(position).getName());
        holder.wishlist_product_model.setText(products.get(position).getName());
//        holder.wishlist_product_avaibility.setText(products.get(position).getPrice());
        Glide.with(context).load(products.get(position).getThumb()).into(holder.wishlist_image);
        String special = products.get(position).getSpecial();
        if (special.equalsIgnoreCase("false")) {
            holder.wishlist_product_price.setText(products.get(position).getPrice());

        } else {
            holder.wishlist_product_price.setText(products.get(position).getSpecial());

            holder.wishlist_product_special.setText(products.get(position).getPrice());
            holder.wishlist_product_special.setPaintFlags(holder.wishlist_product_special.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class RecyclerViewCartHolder extends RecyclerView.ViewHolder {
        ImageView wishlist_image;
        TextView wishlist_product_name, wishlist_product_model, wishlist_product_price,wishlist_product_special, wishlist_product_avaibility;
        LinearLayout wishlist_remove, wishlist_addToCart;

        public RecyclerViewCartHolder(@NonNull View itemView) {
            super(itemView);

            wishlist_image = itemView.findViewById(R.id.wishlist_image);
            wishlist_product_name = itemView.findViewById(R.id.wishlist_product_name);
            wishlist_product_model = itemView.findViewById(R.id.wishlist_product_model);
            wishlist_product_avaibility = itemView.findViewById(R.id.wishlist_product_avaibility);
            wishlist_product_price = itemView.findViewById(R.id.wishlist_product_price);
//            wishlist_remove = itemView.findViewById(R.id.wishlist_remove);
            wishlist_product_special=itemView.findViewById(R.id.wishlist_product_special);
//            wishlist_addToCart = itemView.findViewById(R.id.wishlist_addToCart);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position, view);
                        }
                    }
                }
            });

        }
    }
}
