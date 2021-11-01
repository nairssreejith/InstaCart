package com.sreejithsnair.instacart.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sreejithsnair.instacart.utils.Credentials;

@Database(entities = {Cart.class}, version = 1)
public abstract class CartDatabase extends RoomDatabase {

    public abstract CartDao getCartDao();
    private static CartDatabase cartDB;

    public static CartDatabase getInstance(Context context){
        if(null == cartDB){
            cartDB = buildDatabaseInstance(context);
        }
        return cartDB;
    }

    private static CartDatabase buildDatabaseInstance(Context context){
        return Room.databaseBuilder(context,
                CartDatabase.class,
                Credentials.DB_NAME_CART).allowMainThreadQueries().build();
    }

    public void cleanUp(){cartDB = null;}
}
