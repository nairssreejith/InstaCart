package com.sreejithsnair.instacart.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sreejithsnair.instacart.utils.Credentials;

import java.util.List;

@Dao
public interface CartDao {

    //Retrieve all from CartDB
    @Query("SELECT * FROM " + Credentials.TABLE_NAME_CART)
    List<Cart> getCart();

    @Insert
    void insertCart(Cart cart);

    @Update
    void updateCart(Cart cart);

    @Delete
    void deleteCart(Cart cart);
}
