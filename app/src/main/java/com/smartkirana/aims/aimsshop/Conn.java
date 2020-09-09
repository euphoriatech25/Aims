package com.smartkirana.aims.aimsshop;

import com.smartkirana.aims.aimsshop.views.activities.Cart.CartModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Conn {

    public static HashMap<String, ArrayList<String>> product_id = new HashMap<String,ArrayList<String>>();
    public static HashMap<String, Double> lat = new HashMap<String,Double>();
    public static HashMap<String, Double> lng = new HashMap<String,Double>();
    public static HashMap<String, List<CartModel.Product>> selectedProduct = new HashMap<String,List<CartModel.Product>>();
}
