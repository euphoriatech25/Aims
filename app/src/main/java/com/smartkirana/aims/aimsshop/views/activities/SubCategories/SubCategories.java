package com.smartkirana.aims.aimsshop.views.activities.SubCategories;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.utils.Constants;
import com.smartkirana.aims.aimsshop.views.activities.ProductDetails.ProductDetails;
import com.smartkirana.aims.aimsshop.views.activities.base.BaseActivity;
import com.smartkirana.aims.aimsshop.views.fragments.FeaturedProduct.FeaturedCategoriesAdapter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SubCategories extends BaseActivity implements ISubCategories.View, SubCategoriesAdapter.OnItemClickListener {
    SubCategoriesPresenterImpl presenter;
    RecyclerView subCategoriesRecycler;
    FeaturedCategoriesAdapter subCategoriesAdapter;
    private GridLayoutManager layoutManager;
    String categoriesName, path, image_path, categories_path;
    TextView product_path;
    ImageView thumbnail_image;
    List<SubCategoriesModel.Product> product;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subcategories_list);

        product_path = findViewById(R.id.product_path);
        thumbnail_image = findViewById(R.id.thumbnail_image);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.account));

        Intent data = getIntent();
        if (data != null) {
            categoriesName = data.getStringExtra(Constants.PRODUCT_PATH);
            path = data.getStringExtra(Constants.PATH);
            categories_path = data.getStringExtra(Constants.PRODUCT_NAME);
            path = data.getStringExtra(Constants.PATH);
//            image_path = data.getStringExtra(Constants.IMAGE_THUMB);
//            if (!image_path.equals("")) {
//                Glide.with(this).load(image_path).into(thumbnail_image);
//            } else {
//                thumbnail_image.setVisibility(View.GONE);
//            }
            presenter = new SubCategoriesPresenterImpl(this, new SubCategoriesControllerImpl());
            presenter.getSubCategoriesList(path);
            product_path.setText(categoriesName);
            setUpToolbar(categories_path, true);

        }

        subCategoriesRecycler = findViewById(R.id.subcategories_recyclerviewFeature);
        layoutManager = new GridLayoutManager(SubCategories.this, 2);
        subCategoriesRecycler.setLayoutManager(layoutManager);
        subCategoriesRecycler.setHasFixedSize(true);
        subCategoriesRecycler.setFocusable(false);
        subCategoriesRecycler.setAdapter(subCategoriesAdapter);
    }

    @Override
    public void onSuccess(@NotNull SubCategoriesModel subCategoriesModel) {
        SubCategoriesAdapter subCategoriesAdapter;
        product = subCategoriesModel.getCategories().getProducts();
        subCategoriesAdapter = new SubCategoriesAdapter(SubCategories.this, product);
        subCategoriesRecycler.setAdapter(subCategoriesAdapter);
        subCategoriesAdapter.setOnItemClickListener(SubCategories.this);
    }

    @Override
    public void showStub(boolean show) {
        if (show) {
            Toast.makeText(this, "No Products Found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(@Nullable String message) {

    }

    @Override
    public void showProgressBar(boolean showpBar) {

    }

    @Override
    public void onItemClick(int position, View itemView) {
        if (product.size() != 0) {
            Intent intent = new Intent(this, ProductDetails.class);
            intent.putExtra(Constants.PRODUCT_NAME, product.get(position).getName());
            intent.putExtra(Constants.PRODUCT_ID, product.get(position).getProductId());
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSuccessAddToCart(@Nullable String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessWishList(@Nullable String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
