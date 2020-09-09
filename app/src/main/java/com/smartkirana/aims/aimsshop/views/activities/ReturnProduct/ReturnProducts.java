package com.smartkirana.aims.aimsshop.views.activities.ReturnProduct;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.views.activities.SearchActivity.ISearch;
import com.smartkirana.aims.aimsshop.views.activities.base.BaseActivity;

public class ReturnProducts extends BaseActivity implements ISearch {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_product);
        setUpToolbar("Product Returns", true);

    }
}
