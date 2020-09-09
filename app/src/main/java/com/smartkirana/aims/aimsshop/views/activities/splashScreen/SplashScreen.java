package com.smartkirana.aims.aimsshop.views.activities.splashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.views.activities.Home.HomeActivity;
import com.smartkirana.aims.aimsshop.views.activities.base.BaseActivity;

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
                Intent homeIntent = new Intent(SplashScreen.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }

        }, SPLASH_TIME_OUT);
    }

    @Override
    public void showProgressBar(boolean showpBar) {
    }
}
