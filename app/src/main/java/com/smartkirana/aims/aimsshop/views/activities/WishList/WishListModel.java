package com.smartkirana.aims.aimsshop.views.activities.WishList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WishListModel {

    @SerializedName("wishlist")
    @Expose
    private List<Wishlist> wishlist = null;

    public List<Wishlist> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<Wishlist> wishlist) {
        this.wishlist = wishlist;
    }

    public class Wishlist {

        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("thumb")
        @Expose
        private String thumb;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("model")
        @Expose
        private String model;
        @SerializedName("stock")
        @Expose
        private String stock;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("special")
        @Expose
        private String special;

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

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
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

    }
}