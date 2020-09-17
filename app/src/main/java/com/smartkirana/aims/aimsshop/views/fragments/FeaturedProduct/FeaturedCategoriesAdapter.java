package com.smartkirana.aims.aimsshop.views.fragments.FeaturedProduct;

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
import com.smartkirana.aims.aimsshop.utils.AppUtils;
import com.smartkirana.aims.aimsshop.utils.PrefConstants;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class FeaturedCategoriesAdapter extends RecyclerView.Adapter<FeaturedCategoriesAdapter.RecyclerViewCartHolder> {
    Context context;
    List<HomeFeaturedModel.Featured> featureds;
    View view;
    boolean isOpen = false;
    FeaturedPresenterImpl presenter;
    String api_token,customer_id;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position, View itemView);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public FeaturedCategoriesAdapter(Context context, List<HomeFeaturedModel.Featured> featureds) {
        this.context = context;
        this.featureds = featureds;
    }

    @NonNull
    @Override
    public RecyclerViewCartHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_layout, viewGroup, false);
        return new RecyclerViewCartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewCartHolder holder, int position) {
        SharedPreferences prefs = context.getSharedPreferences(PrefConstants.USER_DETAILS_PREF, MODE_PRIVATE);
        api_token = prefs.getString(PrefConstants.API_TOKEN, PrefConstants.DEFAULT_VALUE);
        customer_id = prefs.getString(PrefConstants.CUSTOMER_ID, PrefConstants.DEFAULT_VALUE);

        holder.product_name.setText(featureds.get(position).getName());
        String special = featureds.get(position).getSpecial();

        Glide.with(context).load(featureds.get(position).getThumb()).into(holder.product_image);
        if(!featureds.get(position).getDescription().equalsIgnoreCase("..")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.product_description.setText(Html.fromHtml(featureds.get(position).getDescription(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                holder.product_description.setText(Html.fromHtml(featureds.get(position).getDescription()));
            }
        }else {
            holder.product_description.setVisibility(View.GONE);
        }


        String product_id=featureds.get(position).getProductId();
        holder.wishlist_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    if (!api_token.equalsIgnoreCase("No Api Token Found") && !TextUtils.isEmpty(product_id)) {
                        presenter = new FeaturedPresenterImpl(context, new FeaturedControllerImpl());
                        presenter.addWishList(featureds.get(position).getProductId(), api_token,customer_id);
                        isOpen = false;
                        holder.wishlist_fav.setColorFilter(Color.parseColor("#FD0505"));
                    }else {
                        AppUtils.showToast(context,"Please Login/Sign up first");
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

                if (!api_token.equalsIgnoreCase("No Api Token Found") && !TextUtils.isEmpty(product_id)) {
                    presenter = new FeaturedPresenterImpl(context, new FeaturedControllerImpl());
                    presenter.addToCart(product_id, customer_id,api_token);
                }else {
                    AppUtils.showToast(context,"Please Login/Sign up first");
                }
            }
        });


        if (special.equalsIgnoreCase("false")) {
            holder.product_unit_price.setText(featureds.get(position).getPrice());
        } else {
            holder.product_unit_price.setText(featureds.get(position).getSpecial());
            holder.product_total_price.setText(featureds.get(position).getPrice());
            holder.product_total_price.setPaintFlags(holder.product_total_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.itemCardPromotion.setVisibility(View.VISIBLE);
//            float dis=100*((Float.parseFloat(featureds.get(position).getPrice())-Float.parseFloat(featureds.get(position).getSpecial()))/Float.parseFloat(featureds.get(position).getPrice()));
            holder.itemCardPromotion.setText("-"+"20"+"%");

        }
    }

    @Override
    public int getItemCount() {
        return featureds.size();
    }

    class RecyclerViewCartHolder extends RecyclerView.ViewHolder {
        ImageView product_image, wishlist_fav, addToCart;
        TextView product_name, product_unit_price, product_total_price, product_description,itemCardPromotion;

        public RecyclerViewCartHolder(@NonNull View itemView) {
            super(itemView);

            product_image = itemView.findViewById(R.id.home_product_image);
            product_name = itemView.findViewById(R.id.product_name);
            product_unit_price = itemView.findViewById(R.id.product_actual_price);
            product_total_price = itemView.findViewById(R.id.product_special_price);
            product_description = itemView.findViewById(R.id.product_description);
            itemCardPromotion=itemView.findViewById(R.id.itemCardPromotion);
            wishlist_fav = itemView.findViewById(R.id.favorite);
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

