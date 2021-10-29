package com.sreejithsnair.instacart.model;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class ProductModel {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("price")
    @Expose
    public Integer price;
    @SerializedName("offer")
    @Expose
    public String offer;
    @SerializedName("wishlist")
    @Expose
    public Integer wishlist;

    public ProductModel(Integer id, String title, String description, String image, Integer price, String offer, Integer wishlist) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.price = price;
        this.offer = offer;
        this.wishlist = wishlist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public Integer getWishlist() {
        return wishlist;
    }

    public void setWishlist(Integer wishlist) {
        this.wishlist = wishlist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductModel that = (ProductModel) o;
        return getId().equals(that.getId()) && getTitle().equals(that.getTitle()) && getDescription().equals(that.getDescription()) && getImage().equals(that.getImage()) && getPrice().equals(that.getPrice()) && Objects.equals(getOffer(), that.getOffer()) && Objects.equals(getWishlist(), that.getWishlist());
    }

    public static DiffUtil.ItemCallback<ProductModel> itemCallback = new DiffUtil.ItemCallback<ProductModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull ProductModel oldItem, @NonNull ProductModel newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ProductModel oldItem, @NonNull ProductModel newItem) {
            return oldItem.equals(newItem);
        }
    };

    @BindingAdapter("android:productImage")
    public static void loadImage(ImageView imageView,String image){
        Glide.with(imageView)
                .load(image)
                .fitCenter()
                .into(imageView);
    }
}
