package com.example.franklin.hudumu.services.market;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {

    private SharedPreferences mSharedPreferences;
    private Context mContext;

    public MySharedPreference(Context context) {
        this.mContext = context;
        mSharedPreferences = context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);
    }

    public void addProductToCart(String product){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(Constants.PRODUCT_ID, product);
        editor.apply();
    }

    public String retreiveProductFromCart(){
        return  mSharedPreferences.getString(Constants.PRODUCT_ID, "");
    }

    public void addProductCount(int procuctCount){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(Constants.PRODUCT_COUNT, procuctCount);
    }

    public int retrieveProductCount(){
        return mSharedPreferences.getInt(Constants.PRODUCT_COUNT, 0);
    }
}
