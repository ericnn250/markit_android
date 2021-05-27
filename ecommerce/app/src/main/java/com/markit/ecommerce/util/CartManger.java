package com.markit.ecommerce.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.markit.ecommerce.models.CartItem;
import com.markit.ecommerce.models.Product;
import com.markit.ecommerce.resources.Products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CartManger {

    private static final String TAG = "CartManger";

    static final String SHOPPING_CART = "shopping_cart";
    static final String CART_ITEMS = "cart_items";
    Context mContext;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;

    public CartManger(Context context) {
        mContext = context;
        mSharedPreferences =PreferenceManager.getDefaultSharedPreferences(context);
                //mContext.getSharedPreferences(SHOPPING_CART, 0);
        mEditor = mSharedPreferences.edit();
    }

    public void addItemToCart(Product product,int quantity) {

        //add the new products serial number (if it hasn't already been added)
        Set<String> product_ids = mSharedPreferences.getStringSet(CART_ITEMS, new HashSet<String>());
        product_ids.add(String.valueOf(product.getSerial_number()));
        mEditor.putStringSet(CART_ITEMS, product_ids);
        mEditor.commit();

        //add the quantity
        int currentQuantity = mSharedPreferences.getInt(String.valueOf(product.getSerial_number()), 0);

        //commit the updated quantity
        mEditor.putInt(String.valueOf(product.getSerial_number()), (currentQuantity + quantity));
        mEditor.commit();
    }

    public ArrayList<CartItem> getCartItems() {
        Products products=new Products();
        HashMap<String, Product> productMap =products.getProducts();
       Set<String> product_ids = mSharedPreferences.getStringSet(CART_ITEMS, new HashSet<String>());

        // Retrieve the quantities of each item from the cart
        ArrayList<CartItem> cartItems = new ArrayList<>();
        for(String product_id : product_ids){
            int quantity = mSharedPreferences.getInt(product_id, 0);

            cartItems.add(new CartItem(productMap.get(product_id), quantity));
        }

        return cartItems;
    }

    public void removeItemFromCart(Product product) {
        Set<String> cartItems = mSharedPreferences.getStringSet(CART_ITEMS, new HashSet<String>());
        if (cartItems.size()==1){
            mEditor.remove(CART_ITEMS);
            mEditor.commit();
            //commit the updated quantity
            mEditor.putInt(String.valueOf(product.getSerial_number()), 0);
            mEditor.commit();
        }
        else{
            cartItems.remove(String.valueOf(product.getSerial_number()));
            mEditor.putStringSet(CART_ITEMS, cartItems);
            mEditor.commit();
            //commit the updated quantity
            mEditor.putInt(String.valueOf(product.getSerial_number()), 0);
            mEditor.commit();
        }

    }
    public void increase(Product product,int quantity){
        //add the quantity
        int currentQuantity = mSharedPreferences.getInt(String.valueOf(product.getSerial_number()), 0);

        //commit the updated quantity
        mEditor.putInt(String.valueOf(product.getSerial_number()), (currentQuantity + quantity));
        mEditor.commit();
    }
    public void decrease(Product product,int quantity){
        //add the quantity
        int currentQuantity = mSharedPreferences.getInt(String.valueOf(product.getSerial_number()), 0);
        if (currentQuantity==1){
            removeItemFromCart(product);
        }
        //commit the updated quantity
        mEditor.putInt(String.valueOf(product.getSerial_number()), (currentQuantity - quantity));
        mEditor.commit();
    }

}