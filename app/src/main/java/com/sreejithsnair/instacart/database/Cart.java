package com.sreejithsnair.instacart.database;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.sreejithsnair.instacart.utils.Credentials;

import java.io.Serializable;
import java.util.Objects;

@Entity (tableName = Credentials.TABLE_NAME_CART)
public class Cart implements Serializable {

    @PrimaryKey()
    private int item_id;

    private String item_title;
    private double item_price;
    private int item_quantity;
    private String item_image;

    public Cart(int item_id, String item_title, double item_price, int item_quantity, String item_image) {
        this.item_id = item_id;
        this.item_title = item_title;
        this.item_price = item_price;
        this.item_quantity = item_quantity;
        this.item_image = item_image;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public double getItem_price() {
        return item_price;
    }

    public void setItem_price(double item_price) {
        this.item_price = item_price;
    }

    public int getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(int item_quantity) {
        this.item_quantity = item_quantity;
    }

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if(this == o)  return  true;
        if(!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(item_title, cart.item_title);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "item_id=" + item_id +
                ", item_title='" + item_title + '\'' +
                ", item_price=" + item_price +
                ", item_quantity=" + item_quantity +
                ", item_image='" + item_image + '\'' +
                '}';
    }
}
