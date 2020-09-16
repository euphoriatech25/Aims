package com.smartkirana.aims.aimsshop.views.fragments.HomeSlider;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.network.RetrofitInterface;
import com.smartkirana.aims.aimsshop.network.ServiceConfig;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SliderFragment extends Fragment {
    SliderView sliderView;
    private SliderAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new SliderAdapter(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_slider, parent, false);
        sliderView = view.findViewById(R.id.imageSlider);
        getSliderDetails();
        return view;

    }

    private void getSliderDetails() {

        RetrofitInterface retrofitInterface = ServiceConfig.createService(RetrofitInterface.class);
        Call<SliderModel> call = retrofitInterface.getProductSlider();
        call.enqueue(new Callback<SliderModel>() {
            @Override
            public void onResponse(Call<SliderModel> call, Response<SliderModel> response) {
                if (response.isSuccessful()) {
                    SliderModel sliderModel = response.body();
                    List<SliderModel.Slideshow> slideshowItem = sliderModel.getSlideshow();

                    adapter.renewItems(slideshowItem);

                    sliderView.setSliderAdapter(adapter);
                    sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                    sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                    sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                    sliderView.setIndicatorSelectedColor(Color.WHITE);
                    sliderView.setIndicatorUnselectedColor(Color.GRAY);
                    sliderView.setScrollTimeInSec(3);
                    sliderView.setAutoCycle(true);
                    sliderView.startAutoCycle();

                    sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
                        @Override
                        public void onIndicatorClicked(int position) {
                            Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
                        }
                    });
                } else {
                }
            }

            @Override
            public void onFailure(Call<SliderModel> call, Throwable t) {
//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}
