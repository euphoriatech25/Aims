package com.smartkirana.aims.aimsshop.views.activities.SearchActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.utils.AppUtils;
import com.smartkirana.aims.aimsshop.utils.PrefConstants;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.RecyclerViewCartHolder> {
    Context context;
    List<ProductListModel.Product> featureds;
    SearchPresenterImpl presenter;
    private OnItemClickListener mListener;
    boolean isOpen = false;
    String api_token,customer_id;

    public interface OnItemClickListener {
        void onItemClick(int position, View itemView);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public SearchAdapter(Context context, List<ProductListModel.Product> featureds) {
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
        presenter = new SearchPresenterImpl((ISearch.View) context, new SearchControllerImpl());
        SharedPreferences prefs = context.getSharedPreferences(PrefConstants.USER_DETAILS_PREF, MODE_PRIVATE);


        api_token = prefs.getString(PrefConstants.API_TOKEN, PrefConstants.DEFAULT_VALUE);
        customer_id = prefs.getString(PrefConstants.CUSTOMER_ID, PrefConstants.DEFAULT_VALUE);

        holder.product_name.setText(featureds.get(position).getName());
        String special = featureds.get(position).getSpecial();
        Glide.with(context).load(featureds.get(position).getThumb()).into(holder.product_image);
        String product_id = featureds.get(position).getProductId();
        holder.wishlist_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    holder.wishlist_fav.setColorFilter(Color.parseColor("#FD0505"));
                    if (featureds.get(position).getProductId() != null) {
                        presenter.addWishList(product_id, api_token,customer_id);
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
                if (!TextUtils.isEmpty(api_token) && !TextUtils.isEmpty(product_id)&& !TextUtils.isEmpty(customer_id)) {
                    presenter.addToCart(product_id, api_token,customer_id);
                } else {

                }
            }
        });
         holder.productCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.showToast(context, product_id+" "+featureds.get(position).getName());
//                Intent intent = new Intent(context, ProductDetails.class);
//                intent.putExtra(Constants.PRODUCT_NAME, featureds.get(position).getName());
//                intent.putExtra(Constants.PRODUCT_ID, product_id);
//                context.startActivity(intent);
            }
        });
        if (special.equalsIgnoreCase("false")) {
            holder.product_unit_price.setText(featureds.get(position).getPrice());
        } else {
            holder.product_unit_price.setText(featureds.get(position).getSpecial());

            holder.product_total_price.setText(featureds.get(position).getPrice());
            holder.product_total_price.setPaintFlags(holder.product_total_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    @Override
    public int getItemCount() {
        return featureds.size();
    }

    class RecyclerViewCartHolder extends RecyclerView.ViewHolder {
        ImageView product_image, wishlist_fav, addToCart;
        TextView product_name, product_unit_price, product_total_price;
        CardView productCart;

        public RecyclerViewCartHolder(@NonNull View itemView) {
            super(itemView);

            product_image = itemView.findViewById(R.id.home_product_image);
            product_name = itemView.findViewById(R.id.product_name);
            product_unit_price = itemView.findViewById(R.id.product_actual_price);
            addToCart = itemView.findViewById(R.id.addToCart);
            product_total_price = itemView.findViewById(R.id.product_special_price);
            wishlist_fav = itemView.findViewById(R.id.favorite);
            productCart = itemView.findViewById(R.id.productCart);
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
