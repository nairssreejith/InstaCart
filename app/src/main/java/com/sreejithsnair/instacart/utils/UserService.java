package com.sreejithsnair.instacart.utils;

import com.sreejithsnair.instacart.model.LoginResponse;
import com.sreejithsnair.instacart.model.ProductListModel;
import com.sreejithsnair.instacart.model.ProductModel;
import com.sreejithsnair.instacart.requests.LoginRequest;
import com.sreejithsnair.instacart.requests.ProductDetailsRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @POST("api/auth/login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @POST("api/food/list")
    Call<ProductListModel> getProductList(@Header ("Authorization") String authToken, @Body ProductDetailsRequest productDetailsRequest);

    @POST("api/food/details")
    Call<ProductListModel> getProductDetail(@Header ("Authorization") String authToken, @Query("id") int id);
}
