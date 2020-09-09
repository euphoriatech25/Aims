package com.smartkirana.aims.aimsshop.views.fragments.HomeSlider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

    public class SliderModel {

        @SerializedName("slideshow")
        @Expose
        private List<Slideshow> slideshow = null;

        public List<Slideshow> getSlideshow() {
            return slideshow;
        }

        public void setSlideshow(List<Slideshow> slideshow) {
            this.slideshow = slideshow;
        }

        public static class Slideshow {
            @SerializedName("title")
            @Expose
            private String title;
            @SerializedName("image")
            @Expose
            private String image;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }

    }

