package com.sreejithsnair.instacart.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sreejithsnair.instacart.model.ProductModel;
import com.sreejithsnair.instacart.repositories.ProductListRepository;

import java.util.List;

public class ProductListViewModel extends ViewModel {

    ProductListRepository productListRepository = new ProductListRepository();

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

}
