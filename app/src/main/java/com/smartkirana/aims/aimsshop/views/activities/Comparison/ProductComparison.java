package com.smartkirana.aims.aimsshop.views.activities.Comparison;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.smartkirana.aims.aimsshop.Conn;
import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.utils.AppUtils;
import com.smartkirana.aims.aimsshop.utils.Constants;
import com.smartkirana.aims.aimsshop.views.activities.base.BaseActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProductComparison extends BaseActivity implements IProductComparison.View {
    View view;
    private ProductComparisonPresenterImpl presenter;
    ArrayList<String> product_id = new ArrayList<>();
    LinearLayout compare_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_product);
        setUpToolbar("Product Returns", true);
        compare_list = findViewById(R.id.compare_list);

        presenter = new ProductComparisonPresenterImpl(this, new ProductComparisonControllerImpl());
        product_id = Conn.product_id.get(Constants.PRODUCT_ID);

        product_id.add("40");
        product_id.add("30");
        presenter.onCompareProductPost(product_id);
//        if (product_id.size() != 0) {
//            for (int i = 0; i < product_id.size(); i++) {
//                Toast.makeText(this, product_id.get(i), Toast.LENGTH_SHORT).show();
//                presenter.onCompareProductPost(product_id.get(i));
//            }
//        } else {
//            Toast.makeText(this, "your compare list is empty", Toast.LENGTH_SHORT).show();
//        }

    }


    @Override
    public void showStub(boolean show) {
        if (show) {
            Toast.makeText(this, "No Products found", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailure(@org.jetbrains.annotations.Nullable String message) {
        AppUtils.showSnackBar(view, message);
    }


    @Override
    public void onCompareProductSuccessPost(@NotNull CompareModel compareModel) {

        if (compareModel.getCompare().getProduct() != null) {
            CompareModel.Product product=compareModel.getCompare().getProduct();
            View view = LayoutInflater.from(this).inflate(R.layout.item_compare_list, compare_list, false);
            TextView product_name,product_price,product_model,product_brand,product_availability
                    ,product_rating,product_rating_detail,product_summary,product_weight,product_dimension;
            ImageView product_image;
            product_image=view.findViewById(R.id.product_image);
            product_name =view.findViewById(R.id.product_name);
            product_price =view.findViewById(R.id.product_price);
            product_model =view.findViewById(R.id.product_model);
            product_brand =view.findViewById(R.id.product_brand);
            product_availability =view.findViewById(R.id.product_availability);
            product_rating =view.findViewById(R.id.product_rating);
            product_rating_detail =view.findViewById(R.id.product_rating_detail);
            product_summary =view.findViewById(R.id.product_summary);
            product_weight =view.findViewById(R.id.product_weight);
            product_dimension =view.findViewById(R.id.product_dimension);

            product_name.setText(product.getName());
            product_price.setText(product.getPrice());
            product_model.setText(product.getModel());
//            product_brand.setText(product.get());
            product_availability.setText(product.getAvailability());
            product_rating.setText(String.valueOf(product.getRating()));
            product_rating_detail.setText(product.getReviews());
            product_summary.setText(product.getDescription());
            product_weight.setText(product.getWeight());
            product_dimension.setText(product.getWeight()+product.getHeight()+product.getLength());
            Glide.with(this).load(product.getThumb()).into(product_image);
            compare_list.addView(view);
        }

    }
}
