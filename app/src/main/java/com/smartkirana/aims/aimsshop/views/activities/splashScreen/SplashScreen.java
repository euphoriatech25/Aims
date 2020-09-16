package com.smartkirana.aims.aimsshop.views.activities.splashScreen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.utils.AppUtils;
import com.smartkirana.aims.aimsshop.views.activities.Home.HomeActivity;
import com.smartkirana.aims.aimsshop.views.activities.base.BaseActivity;

import java.util.Objects;

public class SplashScreen extends BaseActivity {

    private static int SPLASH_TIME_OUT = 1500;
    private ImageView imageView;
    TextView slogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        slogin = findViewById(R.id.slogan);
        imageView = findViewById(R.id.companyLogo);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(700);
        AnimationSet as = new AnimationSet(true);
        in.setStartOffset(700);
        as.addAnimation(in);
        slogin.startAnimation(as);
        final Animation zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom);
        imageView.startAnimation(zoomAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isNetworkAvailable()) {
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                } else {
                    new AlertDialog.Builder(SplashScreen.this)
                            .setIcon(R.drawable.error)
                            .setTitle(R.string.Error)
                            .setMessage(R.string.noInternet)
                            .setPositiveButton(R.string.tryAgn, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);
                                    //SplashScreenActivity.this.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                                }
                            }).setNeutralButton(R.string.setting, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivityForResult(new Intent(Settings.ACTION_WIRELESS_SETTINGS),1);
                        }
                    }).show();
                }
            }

        }, SPLASH_TIME_OUT);
    }

    @Override
    public void showProgressBar(boolean showpBar) {
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = Objects.requireNonNull(cm).getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
