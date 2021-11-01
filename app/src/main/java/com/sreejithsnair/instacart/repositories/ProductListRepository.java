package com.sreejithsnair.instacart.repositories;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sreejithsnair.instacart.model.LoginResponse;
import com.sreejithsnair.instacart.model.ProductListModel;
import com.sreejithsnair.instacart.model.ProductModel;
import com.sreejithsnair.instacart.requests.LoginRequest;
import com.sreejithsnair.instacart.requests.ProductDetailsRequest;
import com.sreejithsnair.instacart.utils.Credentials;
import com.sreejithsnair.instacart.utils.UserService;
import com.sreejithsnair.instacart.views.LoginActivity;
import com.sreejithsnair.instacart.views.MainActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductListRepository {
    private MutableLiveData<List<ProductModel>> mutableProductList;
    private MutableLiveData<List<ProductModel>> mutableProductDetail;
    private MutableLiveData<LoginResponse> loginResponseMutableLiveData;
    LoginResponse loginResponse;

    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Credentials.BASE_URL)
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static UserService getUserService(){
        UserService userService = getRetrofit().create(UserService.class);
        return userService;
    }

    public LiveData<List<ProductModel>> getProductList(){
        if(mutableProductList == null){
            mutableProductList = new MutableLiveData<>();
            loadProductList();
        }

        return mutableProductList;
    }


    public LiveData<List<ProductModel>> getProductDetails(int id){
        if(mutableProductDetail == null){
            mutableProductDetail = new MutableLiveData<>();
            loadProductDetail(id);
        }

        return mutableProductDetail;
    }

    public LiveData<LoginResponse> logIn(LoginRequest loginRequest){
        if(loginResponseMutableLiveData == null){
            loginResponseMutableLiveData = new MutableLiveData<>();
        }
        Call<LoginResponse> loginResponseCall = ProductListRepository.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    loginResponse = response.body();
                    Credentials.token= "Bearer "+response.body().getToken();
                    loginResponseMutableLiveData.setValue(loginResponse);
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
            }
        });
        return loginResponseMutableLiveData;
    }



    private void loadProductList(){
        String token = Credentials.token;
        ProductDetailsRequest productDetailsRequest = new ProductDetailsRequest();
        productDetailsRequest.setStart(1);
        productDetailsRequest.setLimit(20);

        Call<ProductListModel> productListModelCall = ProductListRepository.getUserService().getProductList(token,productDetailsRequest);
        productListModelCall.enqueue(new Callback<ProductListModel>() {
            @Override
            public void onResponse(Call<ProductListModel> call, Response<ProductListModel> response1) {
                if(response1.code() == 200){
                    ProductListModel productListModel = response1.body();
                    mutableProductList.setValue(productListModel.getResponseArray());
                }
            }

            @Override
            public void onFailure(Call<ProductListModel> call, Throwable t) {

            }
        });
    }

    private void loadProductDetail(int id){

        String token = Credentials.token;
        Call<ProductListModel> productDetailCall = ProductListRepository.getUserService().getProductDetail(token,id);
        productDetailCall.enqueue(new Callback<ProductListModel>() {
            @Override
            public void onResponse(Call<ProductListModel> call, Response<ProductListModel> response) {
                if(response.code() == 200){
                    ProductListModel productListModel = response.body();
                    mutableProductDetail.setValue(productListModel.getResponseArray());
                    Log.v("Moose", productListModel.getResponseArray().get(0).getTitle());
                }
            }

            @Override
            public void onFailure(Call<ProductListModel> call, Throwable t) {

            }
        });
    }
}
