package com.smartkirana.aims.aimsshop.network;

import com.smartkirana.aims.aimsshop.utils.EndPoints;
import com.smartkirana.aims.aimsshop.views.activities.Cart.CartModel;
import com.smartkirana.aims.aimsshop.views.activities.Comparison.CompareModel;
import com.smartkirana.aims.aimsshop.views.activities.ProductDetails.ProductDetailsModel;
import com.smartkirana.aims.aimsshop.views.activities.Register.CreateAccountModel;
import com.smartkirana.aims.aimsshop.views.activities.SubCategories.SubCategoriesModel;
import com.smartkirana.aims.aimsshop.views.activities.WishList.WishListModel;
import com.smartkirana.aims.aimsshop.views.fragments.Categories.CategoriesModel;
import com.smartkirana.aims.aimsshop.views.fragments.FeaturedProduct.HomeFeaturedModel;
import com.smartkirana.aims.aimsshop.views.fragments.HomeSlider.SliderModel;
import com.smartkirana.aims.aimsshop.views.fragments.NavCategories.CategoriesListModel;
import com.smartkirana.aims.aimsshop.views.fragments.OrderHistory.OrderHistoryModel;
import com.smartkirana.aims.aimsshop.views.activities.SearchActivity.ProductListModel;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @POST("index.php?route=api/register")
    Call<CreateAccountModel> createAccount(@Body CreateAccountModel createAccountModel);

    @POST("index.php?route=api/login")
    Call<CreateAccountModel> loginUser(@Body CreateAccountModel createAccountModel);

    @GET("index.php?route=api/product")
    Call<ProductListModel> getProductList();

    @GET("index.php?route=api/featured")
    Call<HomeFeaturedModel> getFeaturedList();

    @GET("index.php?route=api/featuredcategory")
    Call<CategoriesModel> getCategories();

    @GET("index.php?route=api/slideshow")
    Call<SliderModel> getProductSlider();

    @GET("index.php?route=api/menu")
    Call<CategoriesListModel> getCategoriesList();

    @GET(EndPoints.API)
    Call<ProductDetailsModel> getProductDetails(@Query("route") String route,
                                                @Query("product_id") String product_id);

    @GET(EndPoints.API)
    Call<SubCategoriesModel> getSubCategories(@Query("route") String route,
                                              @Query("path") String path);

    @GET(EndPoints.API)
    Call<WishListModel> getWishList(@Query("route") String route, @Query("api_token") String api_token);
            ;


    @FormUrlEncoded
    @POST(EndPoints.API)
    Call<ResponseBody> addWishList(@Query("route") String route, @Query("api_token") String api_token,
                                   @Field("product_id") String product_id);@FormUrlEncoded
    @POST(EndPoints.API)
    Call<ResponseBody> removeWishList(@Query("route") String route,
                                      @Query("api_token") String api_token,
                                      @Field("product_id") String product_id);

    @FormUrlEncoded
    @POST(EndPoints.API)
    Call<ResponseBody> addAddToCart(@Query("route") String route,
                                    @Query("api_token") String api_token,
                                    @Field("product_id") String product_id);


    @POST(EndPoints.API)
    Call<CartModel> getAddToCart(@Query("route") String route,
                                 @Query("api_token") String api_token);

    @FormUrlEncoded
    @POST(EndPoints.API)
    Call<ResponseBody> removeAddToCart(@Query("route") String route,
                                       @Query("api_token") String api_token,
                                       @Field("key") String key);

    @FormUrlEncoded
    @POST(EndPoints.API)
    Call<ResponseBody> editAddToCart(@Query("route") String route,
                                     @Query("api_token") String api_token,
                                     @Field("key") String key,
                                     @Field("quantity") String quantity);


    @POST(EndPoints.API)
    Call<OrderHistoryModel> getOrderHistory(@Query("route") String route,
                                            @Query("api_token") String api_token);
//                                            @Query("order_id") String order_id);

    @FormUrlEncoded
    @POST(EndPoints.API)
    Call<CompareModel> compareProduct(@Query("route") String route,
                                      @Field("product_id[]") ArrayList<String> product_id);




}