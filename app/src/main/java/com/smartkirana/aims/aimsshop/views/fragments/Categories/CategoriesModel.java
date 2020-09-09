package com.smartkirana.aims.aimsshop.views.fragments.Categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesModel {


    @SerializedName("featuredcategory")
    @Expose
    private List<Featuredcategory> featuredcategory = null;

    public List<Featuredcategory> getFeaturedcategory() {
        return featuredcategory;
    }

    public void setFeaturedcategory(List<Featuredcategory> featuredcategory) {
        this.featuredcategory = featuredcategory;
    }

    public class Featuredcategory {

        @SerializedName("category_id")
        @Expose
        private String categoryId;
        @SerializedName("thumb")
        @Expose
        private String thumb;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("path")
        @Expose
        private String path;

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
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

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

    }
}