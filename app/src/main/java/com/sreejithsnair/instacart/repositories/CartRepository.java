package com.sreejithsnair.instacart.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sreejithsnair.instacart.database.Cart;
import com.sreejithsnair.instacart.database.CartDatabase;
import com.sreejithsnair.instacart.model.CartProductModel;
import com.sreejithsnair.instacart.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class CartRepository {

    private MutableLiveData<List<CartProductModel>> mutableCart = new MutableLiveData<>();
    private MutableLiveData<Double> mutableTotalPrice = new MutableLiveData<>();

    Cart cart;
    CartDatabase db;

    public LiveData<List<CartProductModel>> getCart(){
        if(mutableCart.getValue() == null){
            initializeCart();
        }

        return mutableCart;
    }

    public void initializeCart(){
        mutableCart.setValue(new ArrayList<CartProductModel>());
        cartTotal();
    }

    public boolean addItemToCart(ProductModel productModel){
        if(mutableCart.getValue() == null){
            initializeCart();
        }
        List<CartProductModel> cartProductModelList = new ArrayList<>(mutableCart.getValue());
        for(CartProductModel cartProductModel: cartProductModelList){
            if(cartProductModel.getProductModel().getId().equals(productModel.getId())){
                int index = cartProductModelList.indexOf(cartProductModel);
                cartProductModel.setQuantity(cartProductModel.getQuantity()+1);
                cartProductModelList.set(index, cartProductModel);
                mutableCart.setValue(cartProductModelList);
                cartTotal();
                return true;
            }
        }

        CartProductModel cartProductModel = new CartProductModel(productModel, 1);
        cartProductModelList.add(cartProductModel);
        mutableCart.setValue(cartProductModelList);
        cartTotal();
        return true;
    }

    public void removeItemFromCart(CartProductModel cartProductModel){
        if(mutableCart.getValue()==null){
            return;
        }
        List<CartProductModel> cartProductModelList = new ArrayList<>(mutableCart.getValue());
        cartProductModelList.remove(cartProductModel);
        mutableCart.setValue(cartProductModelList);
        cartTotal();
    }

    public LiveData<Double> getTotalPrice(){
        if(mutableTotalPrice.getValue() == null){
            mutableTotalPrice.setValue(0.0);
        }
        return mutableTotalPrice;
    }


    private void cartTotal(){
        if(mutableCart.getValue() == null) return;
        double total = 0.0;
        List<CartProductModel> cartProductModelList = mutableCart.getValue();
        for(CartProductModel cartProductModel: cartProductModelList){
            total += cartProductModel.getProductModel().getPrice() * cartProductModel.getQuantity();
        }
        mutableTotalPrice.setValue(total);
    }

    public void insertIntoCartBD(Cart cart, CartDatabase db){
        this.cart = cart;
        this.db = db;
    }

    /*

     ****************************   Needs to be Worked on   *******************************

    private void insertToCart(CartProductModel cartProductModel){
        try {
            if(cartProductModel != null){
                cart.setItem_id(cartProductModel.getProductModel().getId());
                cart.setItem_image(cartProductModel.getProductModel().getImage());
                cart.setItem_price(cartProductModel.getProductModel().getPrice());
                cart.setItem_title(cartProductModel.getProductModel().getTitle());
                cart.setItem_quantity(cartProductModel.getQuantity());

                db.getCartDao().insertCart(cart);
                Log.d("Database", cart.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("Database", "cart to string didn't hit");

    }

    private void LoadCartContents(){
        db.getCartDao().getCart();
        Log.d("Database", db.getCartDao().getCart().toString());
    }*/
}
