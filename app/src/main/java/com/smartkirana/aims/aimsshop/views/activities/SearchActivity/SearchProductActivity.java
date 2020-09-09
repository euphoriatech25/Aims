package com.smartkirana.aims.aimsshop.views.activities.SearchActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.views.activities.Cart.CartActivity;
import com.smartkirana.aims.aimsshop.views.activities.Home.HomeActivity;
import com.smartkirana.aims.aimsshop.views.activities.WishList.WishListActivity;
import com.smartkirana.aims.aimsshop.views.activities.base.BaseActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchProductActivity extends BaseActivity implements ISearch.View, SearchAdapter.OnItemClickListener {
    private EditText etsearch;
    public List<ProductListModel.Product> movieNamesArrayList;
    public List<ProductListModel.Product> array_sort;
    int textlength = 0;
    private GridLayoutManager layoutManager;
    SearchAdapter searchAdapter;
    RecyclerView searchRecycler;
    ImageButton btnSpeak;
    private static final int REQ_SCAN_RESULT = 200;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView heading;
    boolean searchInProgress = false;
    SearchPresenterImpl presenter;
    List<ProductListModel.Product> featured;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_search);
        searchRecycler = findViewById(R.id.search_list_view);
        heading = findViewById(R.id.txtSpeech_heading);
        presenter = new SearchPresenterImpl(this, new SearchControllerImpl());
        presenter.getProductList();


        layoutManager = new GridLayoutManager(SearchProductActivity.this, 2);
        searchRecycler.setLayoutManager(layoutManager);
        searchRecycler.setHasFixedSize(true);
        searchRecycler.setFocusable(false);
        searchRecycler.setAdapter(searchAdapter);
        setUpToolbar("My Search", true);
        init();
        btnSpeak = findViewById(R.id.btnSpeak);

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

        etsearch = findViewById(R.id.edt_search_input);
        etsearch.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textlength = etsearch.getText().length();
                array_sort.clear();
                for (int i = 0; i < movieNamesArrayList.size(); i++) {
                    if (textlength <= movieNamesArrayList.get(i).getName().length()) {
                        if (movieNamesArrayList.get(i).getName().toLowerCase().trim().contains(
                                etsearch.getText().toString().toLowerCase().trim())) {
                            array_sort.add(movieNamesArrayList.get(i));
                        }
                    }
                }

                searchAdapter = new SearchAdapter(SearchProductActivity.this, array_sort);
                searchRecycler.setAdapter(searchAdapter);
            }
        });

    }

    private void init() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.account));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, HomeActivity.class));
        super.onBackPressed();
    }

    private void promptSpeechInput() {
        searchInProgress = true;
        array_sort.clear();

        heading.setText("Search Products");
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "What do you wish for");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(this,
                    "Voice search not supported by your device ",
                    Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        searchInProgress = false;
        if (resultCode == this.RESULT_OK && null != data) {
            switch (requestCode) {
                case REQ_CODE_SPEECH_INPUT: {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textlength = result.get(0).length();
                    heading.setText("Showing Results for " + result.get(0));
                    for (int i = 0; i < movieNamesArrayList.size(); i++) {
                        if (textlength <= movieNamesArrayList.get(i).getName().length()) {
                            if (movieNamesArrayList.get(i).getName().toLowerCase().trim().contains(
                                    etsearch.getText().toString().toLowerCase().trim())) {
                                array_sort.add(movieNamesArrayList.get(i));
                            }
                        }
                    }
                    searchAdapter = new SearchAdapter(this, array_sort);
                    searchRecycler.setAdapter(searchAdapter);

                    break;
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        int id = item.getItemId();
        if (id == R.id.action_favorite) {
            startActivity(new Intent(SearchProductActivity.this, WishListActivity.class));
            finish();
            return true;
        }
        if (id == R.id.action_cart) {
            startActivity(new Intent(SearchProductActivity.this, CartActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgressBar(boolean showpBar) {

    }
    @Override
    public void onSuccess(@NotNull ProductListModel productListModel) {
       featured = productListModel.getProducts();
        movieNamesArrayList = new ArrayList<>();
        array_sort = new ArrayList<>();

        for (int i = 0; i < featured.size(); i++) {
            ProductListModel.Product product = new ProductListModel.Product(featured.get(i).getThumb(), featured.get(i).getName(), featured.get(i).getPrice(), featured.get(i).getSpecial());
            movieNamesArrayList.add(product);
            array_sort.add(product);
        }
    }

    @Override
    public void showStub(boolean show) {

    }

    @Override
    public void onFailure(@org.jetbrains.annotations.Nullable String message) {
    }

    @Override
    public void onItemClick(int position, View itemView) {
        Toast.makeText(this, " array_sort.get(position).getProductId()", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSuccessAddToCart(@org.jetbrains.annotations.Nullable String message) {

    }

    @Override
    public void onSuccessAWishList(@org.jetbrains.annotations.Nullable String message) {

    }
}