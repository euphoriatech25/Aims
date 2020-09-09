package com.smartkirana.aims.aimsshop.views.activities.Comparison;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompareModel {
    @SerializedName("compare")
    @Expose
    private Compare compare;

    public Compare getCompare() {
        return compare;
    }

    public void setCompare(Compare compare) {
        this.compare = compare;
    }

    public class Compare {

        @SerializedName("product")
        @Expose
        private Product product;

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }
    }

    public class Product {

        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("thumb")
        @Expose
        private String thumb;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("special")
        @Expose
        private Boolean special;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("model")
        @Expose
        private String model;
        @SerializedName("manufacturer")
        @Expose
        private String manufacturer;
        @SerializedName("availability")
        @Expose
        private String availability;
        @SerializedName("minimum")
        @Expose
        private String minimum;
        @SerializedName("rating")
        @Expose
        private Integer rating;
        @SerializedName("reviews")
        @Expose
        private String reviews;
        @SerializedName("weight")
        @Expose
        private String weight;
        @SerializedName("length")
        @Expose
        private String length;
        @SerializedName("width")
        @Expose
        private String width;
        @SerializedName("height")
        @Expose
        private String height;
        @SerializedName("attribute")
        @Expose
        private Attribute attribute;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public Boolean getSpecial() {
            return special;
        }

        public void setSpecial(Boolean special) {
            this.special = special;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public String getAvailability() {
            return availability;
        }

        public void setAvailability(String availability) {
            this.availability = availability;
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

        public String getReviews() {
            return reviews;
        }

        public void setReviews(String reviews) {
            this.reviews = reviews;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public Attribute getAttribute() {
            return attribute;
        }

        public void setAttribute(Attribute attribute) {
            this.attribute = attribute;
        }
    }

        public class Attribute {

            @SerializedName("4")
            @Expose
            private String _4;
            @SerializedName("2")
            @Expose
            private String _2;

            public String get4() {
                return _4;
            }

            public void set4(String _4) {
                this._4 = _4;
            }

            public String get2() {
                return _2;
            }

            public void set2(String _2) {
                this._2 = _2;
            }

        }
    }

