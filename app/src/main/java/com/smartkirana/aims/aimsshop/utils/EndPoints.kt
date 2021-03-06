package com.smartkirana.aims.aimsshop.utils

class EndPoints {
    companion object {
        const val BASE_URL = "http://www.aimsshop.net/";

//        const val BASE_URL = "http://192.168.100.105:8080/opencart/"
        const val API = "index.php?"
        const val PRODUCTLIST = API + "product";
        const val CATEGORY_GET = "api/category"
        const val ORDER_HISTORY_GET = "api/order"
        const val PRODUCT_DETAILS_GET = "api/product/product"
        const val PRODUCT_COMPARE = "api/compare"


        const val CARD_ADD = "api/cart/add"
        const val CART_PRODUCTS = "api/cart/products"
        const val CART_REMOVE = "api/cart/remove"
        const val CART_EDIT = "api/cart/edit"

        const val WISHLIST_ADD = "api/wishlist/add"
        const val WISHLIST_GET = "api/wishlist"
        const val WISHLIST_REMOVE = "api/wishlist/remove"

        const val ORDER_ADD = "api/order/add"
        const val ORDER_EDIT = "api/order/edit"
        const val ORDER_REMOVE = "api/order/remove"
        const val ORDER_HISTORY = "api/order/history"
        const val ORDER_INFO = "api/order/info"


        const val ADDRESS_ADD = "api/address/add"
        const val ADDRESS_GET = "api/address"
        const val AVAILABLE_COUNTRIES = "api/country/getCountries"
        const val AVAILABLE_STATE = "api/zone/getZoneByCountry"

    }
}