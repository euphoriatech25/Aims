package com.smartkirana.aims.aimsshop.network;

import com.smartkirana.aims.aimsshop.utils.EndPoints;
import com.smartkirana.aims.aimsshop.views.activities.Cart.CartModel;
import com.smartkirana.aims.aimsshop.views.activities.Comparison.CompareModel;
import com.smartkirana.aims.aimsshop.views.activities.ProductDetails.ProductDetailsModel;
import com.smartkirana.aims.aimsshop.views.activities.Register.CreateAccountModel;
import com.smartkirana.aims.aimsshop.views.activities.SearchActivity.ProductListModel;
import com.smartkirana.aims.aimsshop.views.activities.SubCategories.SubCategoriesModel;
import com.smartkirana.aims.aimsshop.views.activities.WishList.WishListModel;
import com.smartkirana.aims.aimsshop.views.fragments.Categories.CategoriesModel;
import com.smartkirana.aims.aimsshop.views.fragments.FeaturedProduct.HomeFeaturedModel;
import com.smartkirana.aims.aimsshop.views.fragments.HomeSlider.SliderModel;
import com.smartkirana.aims.aimsshop.views.fragments.NavCategories.CategoriesListModel;
import com.smartkirana.aims.aimsshop.views.fragments.OrderHistory.OrderHistoryModel;
import com.smartkirana.aims.aimsshop.views.fragments.billingAddress.AvailableModel;
import com.smartkirana.aims.aimsshop.views.fragments.billingAddress.BillingAddressModel;
import com.smartkirana.aims.aimsshop.views.fragments.billingAddress.CountryModel;
import com.smartkirana.aims.aimsshop.views.fragments.billingAddress.StateModel;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @FormUrlEncoded
    @POST("index.php?route=api/register")
    Call<CreateAccountModel> createAccount(@Field("firstname") String firstname,
                                           @Field("lastname") String lastname,
                                           @Field("email") String email,
                                           @Field("telephone") String telephone,
                                           @Field("password") String password,
                                           @Field("confirm") String confirm,
                                           @Field("agree") String agree);

    @FormUrlEncoded
    @POST("index.php?route=api/customerlogin")
    Call<CreateAccountModel> loginUser(@Field("email") String email,
                                       @Field("password") String password,
                                       @Field("username") String username,
                                       @Field("key") String key);

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
    Call<WishListModel> getWishList(@Query("route") String route,
                                    @Query("api_token") String api_token,
                                    @Query("customer_id") String customer_id);

    @FormUrlEncoded
    @POST(EndPoints.API)
    Call<ResponseBody> addWishList(@Query("route") String route,
                                   @Query("api_token") String api_token,
                                   @Field("product_id") String product_id,
                                   @Field("customer_id") String customer_id);

    @FormUrlEncoded
    @POST(EndPoints.API)
    Call<ResponseBody> removeWishList(@Query("route") String route,
                                      @Query("api_token") String api_token,
                                      @Field("product_id") String product_id,
                                      @Field("customer_id") String customer_id);

    @FormUrlEncoded
    @POST(EndPoints.API)
    Call<ResponseBody> addAddToCart(@Query("route") String route,
                                    @Query("api_token") String api_token,
                                    @Field("product_id") String product_id,
                                    @Field("customer_id") String customer_id);


    @GET(EndPoints.API)
    Call<CartModel> getAddToCart(@Query("route") String route,
                                 @Query("api_token") String api_token,
                                 @Query("customer_id") String customer_id);

    @FormUrlEncoded
    @POST(EndPoints.API)
    Call<ResponseBody> removeAddToCart(@Query("route") String route,
                                       @Query("api_token") String api_token,
                                       @Field("cart_id") String key,
                                       @Field("customer_id") String customer_id);

    @FormUrlEncoded
    @POST(EndPoints.API)
    Call<ResponseBody> editAddToCart(@Query("route") String route,
                                     @Query("api_token") String api_token,
                                     @Field("customer_id") String customer_id,
                                     @Field("quantity") String quantity,
                                     @Field("cart_id") String cart_id);


    @FormUrlEncoded
    @POST(EndPoints.API)
    Call<CompareModel> compareProduct(@Query("route") String route,
                                      @Field("product_id[]") ArrayList<String> product_id);


    @POST(EndPoints.API)
    Call<OrderHistoryModel> getOrderHistory(@Query("route") String route,
                                            @Query("api_token") String api_token);

    @FormUrlEncoded
    @POST(EndPoints.API)
    Call<BillingAddressModel> addAddress(@Query("route") String route,
                                         @Query("api_token") String api_token,
                                         @Field("customer_id") String customer_id,
                                         @Field("firstname") String firstname,
                                         @Field("lastname") String lastname,
                                         @Field("company") String company,
                                         @Field("address_1") String address_1,
                                         @Field("address_2") String address_2,
                                         @Field("postcode") String postcode,
                                         @Field("city") String city,
                                         @Field("zone_id") String zone_id,
                                         @Field("country_id") String country_id);

    @FormUrlEncoded
    @POST(EndPoints.API)
    Call<ResponseBody> editAddress(@Query("route") String route,
                                   @Field("address_id") String address_id,
                                   @Field("customer_id") String customer_id,
                                   @Field("firstname") String firstname,
                                   @Field("lastname") String lastname,
                                   @Field("company") String company,
                                   @Field("address_1") String address_1,
                                   @Field("address_2") String address_2,
                                   @Field("postcode") String postcode,
                                   @Field("city") String city,
                                   @Field("zone_id") String zone_id,
                                   @Field("country_id") String country_id);

    @FormUrlEncoded
    @POST(EndPoints.API)
    Call<ResponseBody> deleteAddress(@Query("route") String route,
                                     @Query("api_token") String api_token,
                                     @Field("customer_id") String customer_id,
                                     @Field("address_id") String address_id);


    @GET(EndPoints.API)
    Call<AvailableModel> getAddress(@Query("route") String route,
                                    @Query("api_token") String api_token,
                                    @Query("customer_id") String customer_id);


    @GET(EndPoints.API)
    Call<CountryModel> getCountry(@Query("route") String route,
                                  @Query("api_token") String api_token);

    @FormUrlEncoded
    @POST(EndPoints.API)
    Call<StateModel> getState(@Query("route") String route,
                              @Query("api_token") String api_token,
                              @Field("country_id") String country_id);


    @FormUrlEncoded
    @POST(EndPoints.API)
    Call<ResponseBody> addOrder(@Query("route") String route,
                                @Query("api_token") String api_token,
                                @Field("customer_id") String customer_id,
                                @Field("payment_method") String payment_method,
                                @Field("shipping_method") String shipping_method,
                                @Field("address_id") String address_id,
                                @Field("shipping_address_id") String shipping_address_id,
                                @Field("comment") String comment,
                                @Field("affiliate_id") String affiliate_id);


    @FormUrlEncoded
    @POST(EndPoints.API)
    Call<ResponseBody> editOrder(@Query("route") String route,
                                 @Query("api_token") String api_token,
                                 @Field("order_id") String order_id,
                                 @Field("customer_id") String customer_id);

    @FormUrlEncoded
    @POST(EndPoints.API)
    Call<ResponseBody> deleteOrder(@Query("route") String route,
                                   @Query("api_token") String api_token,
                                   @Field("order_id") String order_id,
                                   @Field("customer_id") String customer_id);

    @GET(EndPoints.API)
    Call<ResponseBody> infoOrder(@Query("route") String route,
                                 @Query("api_token") String api_token,
                                 @Query("order_id") String order_id);

    @GET(EndPoints.API)
    Call<ResponseBody> historyOrder(@Query("route") String route,
                                    @Query("api_token") String api_token,
                                    @Query("order_id") String order_id);


    @GET(EndPoints.API)
    Call<ResponseBody> getOutletList(@Query("route") String route);


}