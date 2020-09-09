package com.smartkirana.aims.aimsshop.views.fragments.FeaturedProduct;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeFeaturedModel {
    @SerializedName("featured")
    @Expose
    private List<Featured> featured = null;

    public List<Featured> getFeatured() {
        return featured;
    }

    public HomeFeaturedModel(List<Featured> featured) {
        this.featured = featured;
    }

    public void setFeatured(List<Featured> featured) {
        this.featured = featured;
    }

    public static class Featured {


        public Featured(String thumb, String name, String price, String special) {
            this.thumb = thumb;
            this.name = name;
            this.price = price;
            this.special = special;
        }

        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("thumb")
        @Expose
        private String thumb;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("special")
        @Expose
        private String special;
        @SerializedName("tax")
        @Expose
        private Boolean tax;
        @SerializedName("rating")
        @Expose
        private Integer rating;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSpecial() {
            return special;
        }

        public void setSpecial(String special) {
            this.special = special;
        }

        public Boolean getTax() {
            return tax;
        }

        public void setTax(Boolean tax) {
            this.tax = tax;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }
    }
}