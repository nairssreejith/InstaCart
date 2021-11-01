package com.sreejithsnair.instacart.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sreejithsnair.instacart.database.Cart;
import com.sreejithsnair.instacart.database.CartDatabase;
import com.sreejithsnair.instacart.model.CartProductModel;
import com.sreejithsnair.instacart.model.LoginResponse;
import com.sreejithsnair.instacart.model.ProductListModel;
import com.sreejithsnair.instacart.model.ProductModel;
import com.sreejithsnair.instacart.repositories.CartRepository;
import com.sreejithsnair.instacart.repositories.ProductListRepository;
import com.sreejithsnair.instacart.requests.LoginRequest;

import java.util.List;

public class ProductListViewModel extends ViewModel {

    ProductListRepository productListRepository = new ProductListRepository();
    CartRepository cartRepository = new CartRepository();

    MutableLiveData<ProductModel> mutableProductModel = new MutableLiveData<>();

    public LiveData<List<ProductModel>> getProducts(){
        return productListRepository.getProductList();
    }

    public void setProduct(ProductModel productModel){
        mutableProductModel.setValue(productModel);
    }

    public LiveData<ProductModel> getProduct(){
        return mutableProductModel;
    }


    public LiveData<List<CartProductModel>> getCart(){
        return cartRepository.getCart();
    }

    public LiveData<LoginResponse> logIn(LoginRequest loginRequest){
        return productListRepository.logIn(loginRequest);
    }

    public LiveData<List<ProductModel>> getProductDetails(int id){
        return productListRepository.getProductDetails(id);
    }

    public boolean addItemToCart(ProductModel productModel){
        return cartRepository.addItemToCart(productModel);
    }


    public void removeItemFromCart(CartProductModel cartProductModel){
        cartRepository.removeItemFromCart(cartProductModel);
    }

    public void insertIntoCartBD(Cart cart, CartDatabase db){
        cartRepository.insertIntoCartBD(cart, db);
    }

    public LiveData<Double> getTotalPrice(){
        return cartRepository.getTotalPrice();
    }
}
