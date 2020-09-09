package com.smartkirana.aims.aimsshop.views.activities.ProductDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetailsModel {
    @SerializedName("product")
    @Expose
    private List<Product> product = null;

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public class Product {

        @SerializedName("heading_title")
        @Expose
        private String headingTitle;
        @SerializedName("text_minimun")
        @Expose
        private String textMinimun;
        @SerializedName("tab_review")
        @Expose
        private String tabReview;
        @SerializedName("product_id")
        @Expose
        private Integer productId;
        @SerializedName("manufacturer")
        @Expose
        private String manufacturer;
        @SerializedName("manufacturers")
        @Expose
        private String manufacturers;
        @SerializedName("model")
        @Expose
        private String model;
        @SerializedName("reward")
        @Expose
        private String reward;
        @SerializedName("points")
        @Expose
        private String points;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("stock")
        @Expose
        private String stock;
        @SerializedName("main_image_popup")
        @Expose
        private String mainImagePopup;
        @SerializedName("main_image_thumb")
        @Expose
        private String mainImageThumb;
        @SerializedName("sub_image")
        @Expose
        private List<SubImage> subImage = null;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("special")
        @Expose
        private String special;
        @SerializedName("tax")
        @Expose
        private String tax;
        @SerializedName("discounts")
        @Expose
        private List<Object> discounts = null;
        @SerializedName("options")
        @Expose
        private List<Object> options = null;
        @SerializedName("minimum")
        @Expose
        private String minimum;
        @SerializedName("review_status")
        @Expose
        private String reviewStatus;
        @SerializedName("review_guest")
        @Expose
        private Boolean reviewGuest;
        @SerializedName("customer_name")
        @Expose
        private String customerName;
        @SerializedName("reviews")
        @Expose
        private String reviews;
        @SerializedName("rating")
        @Expose
        private Integer rating;
        @SerializedName("captcha")
        @Expose
        private String captcha;
        @SerializedName("share")
        @Expose
        private String share;
        @SerializedName("attribute_groups")
        @Expose
        private List<Object> attributeGroups = null;
        @SerializedName("related_products")
        @Expose
        private List<RelatedProduct> relatedProducts = null;
        @SerializedName("tags")
        @Expose
        private List<Object> tags = null;
        @SerializedName("recurrings")
        @Expose
        private List<Object> recurrings = null;

        public String getHeadingTitle() {
            return headingTitle;
        }

        public void setHeadingTitle(String headingTitle) {
            this.headingTitle = headingTitle;
        }

        public String getTextMinimun() {
            return textMinimun;
        }

        public void setTextMinimun(String textMinimun) {
            this.textMinimun = textMinimun;
        }

        public String getTabReview() {
            return tabReview;
        }

        public void setTabReview(String tabReview) {
            this.tabReview = tabReview;
        }

        public Integer getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public String getManufacturers() {
            return manufacturers;
        }

        public void setManufacturers(String manufacturers) {
            this.manufacturers = manufacturers;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getReward() {
            return reward;
        }

        public void setReward(String reward) {
            this.reward = reward;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getMainImagePopup() {
            return mainImagePopup;
        }

        public void setMainImagePopup(String mainImagePopup) {
            this.mainImagePopup = mainImagePopup;
        }

        public String getMainImageThumb() {
            return mainImageThumb;
        }

        public void setMainImageThumb(String mainImageThumb) {
            this.mainImageThumb = mainImageThumb;
        }

        public List<SubImage> getSubImage() {
            return subImage;
        }

        public void setSubImage(List<SubImage> subImage) {
            this.subImage = subImage;
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

        public String getTax() {
            return tax;
        }

        public void setTax(String tax) {
            this.tax = tax;
        }

        public List<Object> getDiscounts() {
            return discounts;
        }

        public void setDiscounts(List<Object> discounts) {
            this.discounts = discounts;
        }

        public List<Object> getOptions() {
            return options;
        }

        public void setOptions(List<Object> options) {
            this.options = options;
        }

        public String getMinimum() {
            return minimum;
        }

        public void setMinimum(String minimum) {
            this.minimum = minimum;
        }

        public String getReviewStatus() {
            return reviewStatus;
        }

        public void setReviewStatus(String reviewStatus) {
            this.reviewStatus = reviewStatus;
        }

        public Boolean getReviewGuest() {
            return reviewGuest;
        }

        public void setReviewGuest(Boolean reviewGuest) {
            this.reviewGuest = reviewGuest;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getReviews() {
            return reviews;
        }

        public void setReviews(String reviews) {
            this.reviews = reviews;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        public String getCaptcha() {
            return captcha;
        }

        public void setCaptcha(String captcha) {
            this.captcha = captcha;
        }

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public List<Object> getAttributeGroups() {
            return attributeGroups;
        }

        public void setAttributeGroups(List<Object> attributeGroups) {
            this.attributeGroups = attributeGroups;
        }

        public List<RelatedProduct> getRelatedProducts() {
            return relatedProducts;
        }

        public void setRelatedProducts(List<RelatedProduct> relatedProducts) {
            this.relatedProducts = relatedProducts;
        }

        public List<Object> getTags() {
            return tags;
        }

        public void setTags(List<Object> tags) {
            this.tags = tags;
        }

        public List<Object> getRecurrings() {
            return recurrings;
        }

        public void setRecurrings(List<Object> recurrings) {
            this.recurrings = recurrings;
        }

    }


    public class RelatedProduct {

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
        private String tax;
        @SerializedName("minimum")
        @Expose
        private String minimum;
        @SerializedName("rating")
        @Expose
        private Integer rating;
        @SerializedName("href")
        @Expose
        private String href;

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

        public String getTax() {
            return tax;
        }

        public void setTax(String tax) {
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

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

    }


    public class SubImage {

        @SerializedName("popup")
        @Expose
        private String popup;
        @SerializedName("thumb")
        @Expose
        private String thumb;

        public String getPopup() {
            return popup;
        }

        public void setPopup(String popup) {
            this.popup = popup;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }
    }

}