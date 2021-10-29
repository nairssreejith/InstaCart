package com.sreejithsnair.instacart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductListModel {

    @SerializedName("status")
    @Expose
    public int status;

    @SerializedName("responseArray")
    @Expose
    public List<ProductModel> responseArray = null;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ProductModel> getResponseArray() {
        return responseArray;
    }

    public void setResponseArray(List<ProductModel> responseArray) {
        this.responseArray = responseArray;
    }
}
