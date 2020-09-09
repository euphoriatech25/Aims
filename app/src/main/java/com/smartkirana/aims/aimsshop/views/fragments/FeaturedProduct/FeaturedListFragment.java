package com.smartkirana.aims.aimsshop.views.fragments.FeaturedProduct;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.utils.AppUtils;
import com.smartkirana.aims.aimsshop.utils.Constants;
import com.smartkirana.aims.aimsshop.views.activities.ProductDetails.ProductDetails;
import com.smartkirana.aims.aimsshop.views.activities.base.BaseFragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FeaturedListFragment extends BaseFragment implements IFeature.View, FeaturedCategoriesAdapter.OnItemClickListener {

    private FeaturedPresenterImpl presenter;
    private GridLayoutManager layoutManager;
    RecyclerView recyclerviewFeature;
    FeaturedCategoriesAdapter featuredCategoriesAdapter;
    List<HomeFeaturedModel.Featured> homeFeatured;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new FeaturedPresenterImpl(this, new FeaturedControllerImpl());
        presenter.getFeaturedList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View viewItem = inflater.inflate(R.layout.fragment_product_list, parent, false);
        recyclerviewFeature = viewItem.findViewById(R.id.recyclerviewFeature);
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerviewFeature.setLayoutManager(layoutManager);
        recyclerviewFeature.setHasFixedSize(true);
        recyclerviewFeature.setFocusable(false);
        recyclerviewFeature.setAdapter(featuredCategoriesAdapter);
        return viewItem;
    }

    @Override
    public void onSuccess(@NotNull HomeFeaturedModel homeFeaturedModel) {
        AppUtils.freezeUi(getActivity(), false);
        homeFeatured = homeFeaturedModel.getFeatured();
        featuredCategoriesAdapter = new FeaturedCategoriesAdapter(getContext(), homeFeatured);
        recyclerviewFeature.setAdapter(featuredCategoriesAdapter);
        featuredCategoriesAdapter.setOnItemClickListener(this);

    }


    @Override
    public void showStub(boolean show) {
        Toast.makeText(getContext(), R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(@Nullable String message) {
        AppUtils.showSnackBar(getView(), message);
    }

    @Override
    public void noInternetConnection() {
        AppUtils.showSnackBar(getView(), "No Internet Connection");
    }

    @Override
    public void connectionTimeOut() {
        AppUtils.showSnackBar(getView(), "something went gone");
    }

    @Override
    public void unKnownError() {
        AppUtils.showSnackBar(getView(), "something went gone");
    }

    @Override
    public void onItemClick(int position, View itemView) {
        if (homeFeatured.get(position).getProductId() != null) {
            Intent intent = new Intent(getContext(), ProductDetails.class);
            intent.putExtra(Constants.PRODUCT_NAME, homeFeatured.get(position).getName());
            intent.putExtra(Constants.PRODUCT_ID, homeFeatured.get(position).getProductId());
            startActivity(intent);
        }
    }

    @Override
    public void onSuccessAddToCart(@Nullable String message) {

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar(boolean showpBar) {
    }

    @Override
    public void onAddSuccess(@Nullable String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSuccessAWishList(@Nullable String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
