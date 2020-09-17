package com.smartkirana.aims.aimsshop.views.activities.Home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.utils.AppUtils;
import com.smartkirana.aims.aimsshop.utils.Constants;
import com.smartkirana.aims.aimsshop.views.activities.Cart.CartActivity;
import com.smartkirana.aims.aimsshop.views.activities.SearchActivity.SearchProductActivity;
import com.smartkirana.aims.aimsshop.views.activities.SubCategories.SubCategories;
import com.smartkirana.aims.aimsshop.views.activities.WishList.WishListActivity;
import com.smartkirana.aims.aimsshop.views.fragments.Account.AccountFragment;
import com.smartkirana.aims.aimsshop.views.fragments.Categories.CategoriesHome;
import com.smartkirana.aims.aimsshop.views.fragments.Home.HomeFragment;
import com.smartkirana.aims.aimsshop.views.fragments.NavCategories.CategoriesControllerImpl;
import com.smartkirana.aims.aimsshop.views.fragments.NavCategories.CategoriesListModel;
import com.smartkirana.aims.aimsshop.views.fragments.NavCategories.CategoriesPresenterImpl;
import com.smartkirana.aims.aimsshop.views.fragments.NavCategories.ICategories;
import com.smartkirana.aims.aimsshop.views.fragments.OrderHistory.OrderHistoryFragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ICategories.View {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    FragmentManager manager1 = getSupportFragmentManager();
                    Fragment fragment1 = new HomeFragment();
                    FragmentTransaction transaction1 = manager1.beginTransaction();
                    transaction1.replace(R.id.container, fragment1).addToBackStack(TAG_FRAGMENT);
                    transaction1.commit();
                    return true;
                case R.id.navigation_dashboard:
                    FragmentManager manager2 = getSupportFragmentManager();
                    Fragment fragment = new CategoriesHome();
                    FragmentTransaction transaction = manager2.beginTransaction();
                    transaction.replace(R.id.container, fragment).addToBackStack(TAG_FRAGMENT);
                    transaction.commit();
                    return true;
                case R.id.navigation_notifications:
                    FragmentManager manager3 = getSupportFragmentManager();
                    Fragment fragment3 = new OrderHistoryFragment();
                    FragmentTransaction transaction3 = manager3.beginTransaction();
                    transaction3.replace(R.id.container, fragment3).addToBackStack(TAG_FRAGMENT);
                    transaction3.commit();
                    return true;
                case R.id.navigation_cart:
                    FragmentManager manager4 = getSupportFragmentManager();
                    Fragment fragment4 = new AccountFragment();
                    FragmentTransaction transaction4 = manager4.beginTransaction();
                    transaction4.replace(R.id.container, fragment4).addToBackStack(TAG_FRAGMENT);
                    transaction4.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = menuItem.getActionView();

//        textCartItemCount = actionView.findViewById(R.id.cart_badge);
//        setupBadge();
//        actionView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onOptionsItemSelected(menuItem);
//            }
//        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_search) {
            startActivity(new Intent(HomeActivity.this, SearchProductActivity.class));
            finish();
            return true;
        }
        if (id == R.id.action_favorite) {
            startActivity(new Intent(HomeActivity.this, WishListActivity.class));
            finish();
            return true;
        }
        if (id == R.id.action_cart) {
            startActivity(new Intent(HomeActivity.this, CartActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupBadge() {

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(mCartItemCount));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    TextView textCartItemCount;
    int mCartItemCount = 0;

    private List<String> categories_listDataGroup;
    private HashMap<String, List<String>> categories_listDataChild;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private ExpandableListView categoriesList;
    private ExpandableListViewAdapter expandableListViewAdapter;
    private CategoriesPresenterImpl presenter;
    List<CategoriesListModel.Category> categoriesListModelCategories;
    private final static String TAG_FRAGMENT = "TAG_FRAGMENT";
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        changeStatusBarColor();
        presenter = new CategoriesPresenterImpl(this, new CategoriesControllerImpl());
        presenter.getCategoriesList();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Aims Shop");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view_side);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        categoriesList = findViewById(R.id.expandable_categories);

        initFragment();
        initListeners();
        initObjects();
    }
    private void initObjects() {
        categories_listDataGroup = new ArrayList<>();
        categories_listDataChild = new HashMap<>();
        expandableListViewAdapter = new ExpandableListViewAdapter(this, categories_listDataGroup, categories_listDataChild);
        categoriesList.setAdapter(expandableListViewAdapter);
    }

    private void initListeners() {
        categoriesList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                if (categoriesListModelCategories.get(groupPosition).getChildren().size() != 0) {
                    Intent intent = new Intent(HomeActivity.this, SubCategories.class);
                    intent.putExtra(Constants.PATH, categoriesListModelCategories.get(groupPosition).getChildren().get(childPosition).getPath());
                    intent.putExtra(Constants.PRODUCT_NAME, categoriesListModelCategories.get(groupPosition).getChildren().get(childPosition).getName());
                    intent.putExtra(Constants.PRODUCT_PATH, categoriesListModelCategories.get(groupPosition).getName() + " > " + categoriesListModelCategories.get(groupPosition).getChildren().get(childPosition).getName());
                    startActivity(intent);
                } else {
                    Toast.makeText(HomeActivity.this, "Category Empty", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        categoriesList.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                if (categoriesListModelCategories.get(groupPosition).getChildren().size() == 0) {
                    Intent intent = new Intent(HomeActivity.this, SubCategories.class);
                    intent.putExtra(Constants.PATH, categoriesListModelCategories.get(groupPosition).getPath());
                    intent.putExtra(Constants.PRODUCT_NAME, categoriesListModelCategories.get(groupPosition).getName());
                    intent.putExtra(Constants.PRODUCT_PATH, categoriesListModelCategories.get(groupPosition).getName());
                    startActivity(intent);
                } else {
                }
            }
        });
    }

    private void initFragment() {
        navigationView.setNavigationItemSelectedListener(this);
        FragmentManager manager1 = getSupportFragmentManager();
        Fragment fragment1 = new HomeFragment();
        FragmentTransaction transaction1 = manager1.beginTransaction();
        transaction1.replace(R.id.container, fragment1).addToBackStack(null);
        transaction1.commit();
    }

    private void changeStatusBarColor() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.account));

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSuccess(@NotNull CategoriesListModel categoriesListModel) {
        categoriesListModelCategories = categoriesListModel.getCategories();
        for (int i = 0; i < categoriesListModelCategories.size(); i++) {
            categories_listDataGroup.add(categoriesListModelCategories.get(i).getName());

            List<String> subCategories = new ArrayList<>();
            if (categoriesListModelCategories.get(i).children.size() != 0) {
                for (int i1 = 0; i1 < categoriesListModelCategories.get(i).children.size(); i1++) {
                    subCategories.add(categoriesListModelCategories.get(i).children.get(i1).getName());
                }
            }
            categories_listDataChild.put(categories_listDataGroup.get(i), subCategories);
            expandableListViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
//        if (getFragmentManager().getBackStackEntryCount() > 0) {
//            getFragmentManager().popBackStack();
//        } else {
//            super.onBackPressed();
//        }
        startPopUp();
    }


    private void startPopUp() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.are_you_sure)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }


    @Override
    public void showStub(boolean show) {
    }

    @Override
    public void onFailure(@Nullable String message) {
        AppUtils.showSnackBar(view, message);
    }

    @Override
    public void noInternetConnection() {
        AppUtils.showSnackBar(view, "No Internet Connection");
    }

    @Override
    public void connectionTimeOut() {
        AppUtils.showSnackBar(view, "something went wrong");

    }

    @Override
    public void showProgressBar(boolean showpBar) {
    }

    @Override
    public void unKnownError() {
        AppUtils.showSnackBar(view, "something went wrong");

    }
}
