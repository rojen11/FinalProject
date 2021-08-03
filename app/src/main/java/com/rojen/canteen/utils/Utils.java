package com.rojen.canteen.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rojen.canteen.model.FoodModel;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {
    private static final String CART_KEY = "CART_KEY";

    private static Utils instance;
    private SharedPreferences sharedPreferences;

    Gson gson = new Gson();

    private Utils(Context context) {
        sharedPreferences = context.getSharedPreferences("local_db", Context.MODE_PRIVATE);
        Log.d("Food", "init");
        this.init();
    }

    public static synchronized  Utils getInstance(Context context) {
        if (instance == null) {
            instance = new Utils(context);
        }

        return instance;
    }

    public static Utils getInstance() {
        return instance;
    }

    private void init() {
        ArrayList<FoodModel> foods = new ArrayList<>();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CART_KEY, gson.toJson(foods));
        editor.commit();
    }

    public ArrayList<FoodModel> getCart() {
        Type type = new TypeToken<ArrayList<FoodModel>>(){}.getType();
        ArrayList<FoodModel> cartList = gson.fromJson(sharedPreferences.getString(CART_KEY, null), type);
        return cartList;
    }

    public boolean addToCart(FoodModel food) {
        ArrayList<FoodModel> cartList = getCart();
        if (cartList != null) {
            if (cartList.add(food)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CART_KEY);
                editor.putString(CART_KEY, gson.toJson(cartList));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean searchCart(int id) {
        ArrayList<FoodModel> cartList = getCart();
        if (cartList != null) {
            for (FoodModel food: cartList) {
                if (food.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeFromCart(int id) {
        ArrayList<FoodModel> cartList = getCart();
        if (cartList != null) {
            for (FoodModel food: cartList) {
                if (food.getId() == id) {
                    cartList.remove(food);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(CART_KEY);
                    editor.putString(CART_KEY, gson.toJson(cartList));
                    editor.commit();
                    return true;
                }
            }
        }
        return false;
    }
}
