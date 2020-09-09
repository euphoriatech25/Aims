package com.smartkirana.aims.aimsshop.views.fragments.Account;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.utils.PrefConstants;
import com.smartkirana.aims.aimsshop.views.activities.Comparison.ProductComparison;
import com.smartkirana.aims.aimsshop.views.activities.Home.HomeActivity;
import com.smartkirana.aims.aimsshop.views.activities.Login.LoginActivity;
import com.smartkirana.aims.aimsshop.views.activities.OnlinePayment.CellPayActivity;

import static android.content.Context.MODE_PRIVATE;

public class AccountFragment extends Fragment implements View.OnClickListener {
    CardView signin_signon;
    private TextView payment_method,items_comparison_id;
    String api_token;
    private ConstraintLayout userProfile;
    CardView login_sign_card;
    private ImageView logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, parent, false);
        signin_signon = view.findViewById(R.id.signin_signon);
        payment_method = view.findViewById(R.id.payment_method);
        userProfile = view.findViewById(R.id.userProfile);
        logout = view.findViewById(R.id.logout);
        items_comparison_id=view.findViewById(R.id.items_comparison_id);
        login_sign_card = view.findViewById(R.id.login_sign_card);
        SharedPreferences prefs = getActivity().getSharedPreferences(PrefConstants.USER_DETAILS_PREF, MODE_PRIVATE);
        api_token = prefs.getString(PrefConstants.API_TOKEN, PrefConstants.DEFAULT_VALUE);

        if (!api_token.equalsIgnoreCase(PrefConstants.DEFAULT_VALUE)) {
            login_sign_card.setVisibility(View.INVISIBLE);
            userProfile.setVisibility(View.VISIBLE);
        } else {
            login_sign_card.setVisibility(View.VISIBLE);
            userProfile.setVisibility(View.INVISIBLE);
        }

        login_sign_card.setOnClickListener(this);
        payment_method.setOnClickListener(this);
        logout.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_sign_card:
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
            case R.id.payment_method:
                startActivity(new Intent(getContext(), CellPayActivity.class));
                break;
            case R.id.logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Logout");
                builder.setMessage("Are you sure you want to logout?");

                builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences prefs = getActivity().getSharedPreferences(PrefConstants.USER_DETAILS_PREF, MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.clear();
                        editor.apply();
                        startActivity(new Intent(getContext(), HomeActivity.class));
                    }
                });
                builder.setNegativeButton("Cancel", null);

                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.items_comparison_id:
                startActivity(new Intent(getContext(), ProductComparison.class));
                break;
        }
    }
}
