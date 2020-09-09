package com.smartkirana.aims.aimsshop.views.activities.Cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CartModel implements Serializable {
    @SerializedName("error")
    @Expose
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("vouchers")
    @Expose
    private List<Object> vouchers = null;
    @SerializedName("totals")
    @Expose
    private List<Total> totals = null;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Object> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<Object> vouchers) {
        this.vouchers = vouchers;
    }

    public List<Total> getTotals() {
        return totals;
    }

    public void setTotals(List<Total> totals) {
        this.totals = totals;
    }


    public class Product {

        @SerializedName("cart_id")
        @Expose
        private String cartId;
        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("model")
        @Expose
        private String model;
        @SerializedName("thumb")
        @Expose
        private String image;
        @SerializedName("option")
        @Expose
        private List<Object> option = null;
        @SerializedName("quantity")
        @Expose
        private String quantity;
        @SerializedName("stock")
        @Expose
        private Boolean stock;
        @SerializedName("shipping")
        @Expose
        private String shipping;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("total")
        @Expose
        private String total;
        @SerializedName("reward")
        @Expose
        private Integer reward;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCartId() {
            return cartId;
        }

        public void setCartId(String cartId) {
            this.cartId = cartId;
        }

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

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public List<Object> getOption() {
            return option;
        }

        public void setOption(List<Object> option) {
            this.option = option;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public Boolean getStock() {
            return stock;
        }

        public void setStock(Boolean stock) {
            this.stock = stock;
        }

        public String getShipping() {
            return shipping;
        }

        public void setShipping(String shipping) {
            this.shipping = shipping;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public Integer getReward() {
            return reward;
        }

        public void setReward(Integer reward) {
            this.reward = reward;
        }
    }

    public class Total {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("text")
        @Expose
        private String text;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}




