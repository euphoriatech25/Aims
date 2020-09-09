package com.smartkirana.aims.aimsshop.views.fragments.Home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.views.fragments.Categories.CategoriesFragment;
import com.smartkirana.aims.aimsshop.views.fragments.FeaturedProduct.FeaturedListFragment;
import com.smartkirana.aims.aimsshop.views.fragments.HomeSlider.SliderFragment;

public class HomeFragment extends Fragment {
    SwipeRefreshLayout swipeContainer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_home, parent, false);

        swipeContainer = view.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(getActivity().getIntent());
                        swipeContainer.setRefreshing(false);
                    }
                }, 1000);
            }
        });


        FragmentManager manager2 = getChildFragmentManager();
        Fragment fragment2 = new SliderFragment();
        FragmentTransaction transaction2 = manager2.beginTransaction();
        transaction2.replace(R.id.containerSlider, fragment2).addToBackStack(null);
        transaction2.commit();


        FragmentManager manager4 = getChildFragmentManager();
        Fragment fragment4 = new FeaturedListFragment();
        FragmentTransaction transaction4 = manager4.beginTransaction();
        transaction4.replace(R.id.featureList, fragment4).addToBackStack(null);
        transaction4.commit();

        FragmentManager manager5 = getChildFragmentManager();
        Fragment fragment5 = new CategoriesFragment();
        FragmentTransaction transaction5 = manager5.beginTransaction();
        transaction5.replace(R.id.categoriesDetails, fragment5).addToBackStack(null);
        transaction5.commit();

        return view;
    }


}
