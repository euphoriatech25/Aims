package com.smartkirana.aims.aimsshop.views.activities.base;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.smartkirana.aims.aimsshop.R;

public class BaseActivity extends AppCompatActivity implements BaseMvpView{
        private Toolbar mToolbar;

        protected Toolbar setUpToolbar(String title, boolean enableBack) {
            if (mToolbar == null) {
                mToolbar = findViewById(R.id.toolbar);
                TextView tvTitle = mToolbar.findViewById(R.id.tvToolbarTitle);
                if (mToolbar != null) {
                    tvTitle.setText(title);
                    setSupportActionBar(mToolbar);
                    getSupportActionBar().setDisplayShowTitleEnabled(false);
                    if (enableBack) {
                        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
                    }
                }
            }
            return mToolbar;
        }

        @Override
        public void noInternetConnection() {
            showSnackBar(getString(R.string.no_internet_connection));
        }

        @Override
        public void connectionTimeOut() {
            showSnackBar(getString(R.string.connection_time_out));
        }

        @Override
        public void unKnownError() {
            showSnackBar(getString(R.string.something_went_wrong));
        }

        private void showSnackBar(String message) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                    message, Snackbar.LENGTH_SHORT);
            View sbView = snackbar.getView();
            TextView textView = sbView
                    .findViewById(R.id.snackbar_text);
            textView.setTextColor(ContextCompat.getColor(this, android.R.color.white));
            snackbar.show();
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == android.R.id.home) {
                finish();
            }
            return super.onOptionsItemSelected(item);
        }

    @Override
    public void showProgressBar(boolean showpBar) {
        
    }
}

