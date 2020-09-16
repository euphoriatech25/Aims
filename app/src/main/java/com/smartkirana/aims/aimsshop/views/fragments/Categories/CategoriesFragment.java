package com.smartkirana.aims.aimsshop.views.fragments.Categories;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.utils.AppUtils;
import com.smartkirana.aims.aimsshop.utils.Constants;
import com.smartkirana.aims.aimsshop.views.activities.SubCategories.SubCategories;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CategoriesFragment extends Fragment implements ICategories.View, CategoriesAdapter.OnItemClickListener {
    RecyclerView productRecycle;
    CategoriesAdapter categoriesAdapter;
    CategoriesModel featuredModel;
    List<CategoriesModel.Featuredcategory> featuredList;
    private CategoriesPresenterImpl presenter;
    private LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, parent, false);
        productRecycle = view.findViewById(R.id.recyclerviewFeature);
        presenter = new CategoriesPresenterImpl(this, new CategoriesControllerImpl());
        presenter.getFeatureCategoriesList();
         layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

//        layoutManager = new GridLayoutManager(getContext(), 2);
        productRecycle.setLayoutManager(layoutManager);
        productRecycle.setHasFixedSize(true);
        productRecycle.setFocusable(false);
        productRecycle.setAdapter(categoriesAdapter);
        return view;

    }

    @Override
    public void onSuccess(@NotNull CategoriesModel categoriesModel) {
        AppUtils.freezeUi(getActivity(), false);
        featuredList = categoriesModel.getFeaturedcategory();
        categoriesAdapter = new CategoriesAdapter(getContext(), featuredList);
        productRecycle.setAdapter(categoriesAdapter);
        categoriesAdapter.setOnItemClickListener(this);

    }


    @Override
    public void showStub(boolean show) {

    }

    @Override
    public void onFailure(@Nullable String message) {

    }

    @Override
    public void noInternetConnection() {

    }

    @Override
    public void connectionTimeOut() {

    }

    @Override
    public void showProgressBar(boolean showpBar) {

    }

    @Override
    public void unKnownError() {
    }

    @Override
    public void onItemClick(int position, View itemView) {
        if (featuredList.size() != 0) {
            Intent intent = new Intent(getContext(), SubCategories.class);
            intent.putExtra(Constants.PATH, featuredList.get(position).getPath());
            intent.putExtra(Constants.PRODUCT_NAME, featuredList.get(position).getName());
            intent.putExtra(Constants.PRODUCT_PATH, featuredList.get(position).getName());
            startActivity(intent);
        } else {
            AppUtils.showToast(getContext(), "something went wrong");
        }
    }
}
