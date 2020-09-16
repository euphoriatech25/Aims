package com.smartkirana.aims.aimsshop.views.activities.SubCategories;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.utils.PrefConstants;


import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SubCategoriesAdapter  extends RecyclerView.Adapter<SubCategoriesAdapter.RecyclerViewCartHolder> {
    Context context;
    List<SubCategoriesModel.Product> products;
    boolean isOpen = false;
    SubCategoriesPresenterImpl presenter;
    String api_token,customer_id;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position, View itemView);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public SubCategoriesAdapter(Context context, List<SubCategoriesModel.Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public RecyclerViewCartHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_layout, viewGroup, false);
        return new RecyclerViewCartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewCartHolder holder, int position) {
        holder.product_name.setText(products.get(position).getName());
        String special = products.get(position).getSpecial();
        Glide.with(context).load(products.get(position).getThumb()).into(holder.product_image);
        SharedPreferences prefs = context.getSharedPreferences(PrefConstants.USER_DETAILS_PREF, MODE_PRIVATE);
        api_token = prefs.getString(PrefConstants.API_TOKEN, PrefConstants.DEFAULT_VALUE);
        customer_id = prefs.getString(PrefConstants.CUSTOMER_ID, PrefConstants.DEFAULT_VALUE);


        if(!products.get(position).getDescription().equalsIgnoreCase("..")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.product_description.setText(Html.fromHtml(products.get(position).getDescription(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                holder.product_description.setText(Html.fromHtml(products.get(position).getDescription()));
            }
        }else {
            holder.product_description.setVisibility(View.GONE);
        }
        holder.wishlist_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    holder.wishlist_fav.setColorFilter(Color.parseColor("#FD0505"));
                    if(products.get(position).getProductId()!=null &&  !TextUtils.isEmpty(customer_id)){
                        presenter = new SubCategoriesPresenterImpl((ISubCategories.View) context, new SubCategoriesControllerImpl());
                        presenter.addWishList(products.get(position).getProductId(),api_token,customer_id);
                        isOpen = false;
                    }
                } else {
                    holder.wishlist_fav.setColorFilter(Color.parseColor("#808080"));
                    isOpen = true;
                }

            }
        });
        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(api_token) &&  !TextUtils.isEmpty(customer_id)) {
                    presenter = new SubCategoriesPresenterImpl((ISubCategories.View) context, new SubCategoriesControllerImpl());
                    presenter.addToCart(products.get(position).getProductId(), api_token,customer_id);
                }
            }
        });

        if (special.equalsIgnoreCase("false")) {
            holder.product_unit_price.setText(products.get(position).getPrice());
        } else {
            holder.product_unit_price.setText(products.get(position).getSpecial());
            holder.product_total_price.setText(products.get(position).getPrice());
            holder.product_total_price.setPaintFlags(holder.product_total_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class RecyclerViewCartHolder extends RecyclerView.ViewHolder {
        ImageView product_image, wishlist_fav, addToCart;
        TextView product_name, product_unit_price, product_total_price,product_description;

        public RecyclerViewCartHolder(@NonNull View itemView) {
            super(itemView);

            product_image = itemView.findViewById(R.id.home_product_image);
            product_name = itemView.findViewById(R.id.product_name);
            product_unit_price = itemView.findViewById(R.id.product_actual_price);
            product_total_price = itemView.findViewById(R.id.product_special_price);
            wishlist_fav = itemView.findViewById(R.id.favorite);
            product_description=itemView.findViewById(R.id.product_description);
            addToCart = itemView.findViewById(R.id.addToCart);
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
