package com.smartkirana.aims.aimsshop.views.activities.SubCategories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubCategoriesModel {

    @SerializedName("categories")
    @Expose
    private Categories categories;

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public class Categories {
        @SerializedName("heading")
        @Expose
        private String heading;
        @SerializedName("thumb")
        @Expose
        private String thumb;
        @SerializedName("categories")
        @Expose
        private List<Object> categories = null;
        @SerializedName("products")
        @Expose
        private List<Product> products = null;

        public String getHeading() {
            return heading;
        }

        public void setHeading(String heading) {
            this.heading = heading;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public List<Object> getCategories() {
            return categories;
        }

        public void setCategories(List<Object> categories) {
            this.categories = categories;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }

    }
    public class Product {

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
        @SerializedName("minimum")
        @Expose
        private String minimum;
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

        public String getMinimum() {
            return minimum;
        }

        public void setMinimum(String minimum) {
            this.minimum = minimum;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

    }
}
