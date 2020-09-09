package com.smartkirana.aims.aimsshop.views.activities.base;

import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.interfaces.BaseView;


public class BaseFragment extends Fragment implements BaseView {
    @Override
    public void noInternetConnection() {
        showSnackBar(getString(R.string.no_internet_connection));
    }

    @Override
    public void connectionTimeOut() {
        showSnackBar(getString(R.string.connection_time_out));
    }

    @Override
    public void showProgressBar(boolean showpBar) {

    }

    @Override
    public void unKnownError() {
        showSnackBar(getString(R.string.something_went_wrong));
    }

    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = sbView
                .findViewById(R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(getActivity(), android.R.color.white));
        snackbar.show();
    }
}
