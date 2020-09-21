package com.smartkirana.aims.aimsshop.views.activities.Cart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.smartkirana.aims.aimsshop.Conn;
import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.utils.AppUtils;
import com.smartkirana.aims.aimsshop.utils.Constants;
import com.smartkirana.aims.aimsshop.utils.PrefConstants;
import com.smartkirana.aims.aimsshop.views.activities.ProductDetails.ProductDetails;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.MyViewHolder> {
    private Context context;
    private List<CartModel.Product> cartList;
    int productQuantity = 0;
    private CartPresenterImpl presenter;
    private String api_token, customer_id;
    private ImageView product_image;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView product_name,
                product_unit_price,
                total_price,
                product_avaibility, product_model;
        public ImageButton quantity_update,
                decrease_quantity,
                increase_quantity;
        EditText product_quantity;
        CheckBox selectProduct;
        public RelativeLayout viewBackground, viewForeground;

        public MyViewHolder(View view) {
            super(view);
            product_image = view.findViewById(R.id.product_image);
            product_name = view.findViewById(R.id.product_name);
            product_unit_price = view.findViewById(R.id.product_price);
            total_price = view.findViewById(R.id.total_price);
            product_avaibility = view.findViewById(R.id.product_avaibility);
//             removeCart = view.findViewById(R.id.removeCart);
            quantity_update = view.findViewById(R.id.quantity_update);
            decrease_quantity = view.findViewById(R.id.decrease_quantity);
            increase_quantity = view.findViewById(R.id.increase_quantity);
            product_model = view.findViewById(R.id.product_model);
            product_quantity = view.findViewById(R.id.product_quantity);
            selectProduct = view.findViewById(R.id.selectProduct);

            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);


        }
    }


    public CartListAdapter(Context context, List<CartModel.Product> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_items, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int i) {
        SharedPreferences prefs = context.getSharedPreferences(PrefConstants.USER_DETAILS_PREF, MODE_PRIVATE);
        api_token = prefs.getString(PrefConstants.API_TOKEN, PrefConstants.DEFAULT_VALUE);
        customer_id = prefs.getString(PrefConstants.CUSTOMER_ID, PrefConstants.DEFAULT_VALUE);
         String name = cartList.get(i).getName();
        presenter = new CartPresenterImpl((ICart.View)context, new CartControllerImpl());

        List<CartModel.Product> selectedProducts = new ArrayList<CartModel.Product>();

        holder.selectProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedProducts.add(cartList.get(i));
            }
        });
        if (selectedProducts != null) {
            if (!api_token.equalsIgnoreCase("No Api Token Found")) {
                Conn.selectedProduct.put(Constants.SELECTED_PRODUCT, selectedProducts);
                 } else {
                AppUtils.showToast(context, "Please Login/Sign U first ");
                  }
        }


//            Initialization of Value
        holder.product_name.setText(cartList.get(i).getName());
        holder.product_unit_price.setText(cartList.get(i).getPrice());
        holder.total_price.setText(cartList.get(i).getTotal());
        holder.product_model.setText(cartList.get(i).getModel());
        if (cartList.get(i).getStock()) {
            holder.product_avaibility.setText("In stock");
        } else {
            holder.product_avaibility.setText("Not in stock");
        }
        Glide.with(context).load("http://aimsshop.net/image/cache/catalog/spices/bmc-chickmasmasala-228x228.jpg").into(product_image);

        String cart_id = cartList.get(i).getCartId();

//        holder.removeCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                presenter.removeCartProduct(cart_id, customer_id, api_token);
//            }
//        });

        String product_id = cartList.get(i).getProductId();
        holder.product_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetails.class);
                intent.putExtra(Constants.PRODUCT_NAME, name);
                intent.putExtra(Constants.PRODUCT_ID, product_id);

            }
        });


        holder.product_quantity.setText(cartList.get(i).getQuantity());
        holder.increase_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productQuantity = Integer.parseInt(holder.product_quantity.getText().toString()) + 1;
                holder.product_quantity.setText(String.valueOf(productQuantity));
            }
        });
        holder.decrease_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productQuantity > 1) {
                    productQuantity = Integer.parseInt(holder.product_quantity.getText().toString()) - 1;
                    holder.product_quantity.setText(String.valueOf(productQuantity));
                }
            }
        });

        holder.quantity_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(cart_id!=null&& holder.product_quantity.getText().toString()!=null&&customer_id!=null&&api_token!=null) {
                    presenter.editCartProduct(cart_id, holder.product_quantity.getText().toString(), customer_id, api_token);
                }else {
                  }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public void removeItem(int position) {
        cartList.remove(position);
        notifyItemRemoved(position);
    }

}