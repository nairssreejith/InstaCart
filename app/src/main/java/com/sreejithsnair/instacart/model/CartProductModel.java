package com.sreejithsnair.instacart.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class CartProductModel {

    private ProductModel productModel;
    private int quantity;

    public CartProductModel(ProductModel productModel, int quantity) {
        this.productModel = productModel;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartProductModel that = (CartProductModel) o;
        return getQuantity() == that.getQuantity() && getProductModel().equals(that.getProductModel());
    }


    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static DiffUtil.ItemCallback<CartProductModel> itemCallback =
            new DiffUtil.ItemCallback<CartProductModel>() {
                @Override
                public boolean areItemsTheSame(@NonNull CartProductModel oldItem, @NonNull CartProductModel newItem) {
                    return oldItem.getProductModel().equals(newItem.getProductModel());
                }

                @Override
                public boolean areContentsTheSame(@NonNull CartProductModel oldItem, @NonNull CartProductModel newItem) {
                    return oldItem.equals(newItem);
                }
            };
}
