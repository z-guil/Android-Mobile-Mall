package com.example.shopping.database;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.shopping.dao.GoodDao;
import com.example.shopping.entity.Cart;
import com.example.shopping.entity.Good;

@Database(entities = {Good.class, Cart.class},version = 1,exportSchema = false)
public abstract class GoodDatabase extends RoomDatabase {
    public abstract GoodDao goodDao();

    private static GoodDatabase goodDatabase = null;
    public static GoodDatabase getInstance(Application application){
        if(goodDatabase==null){
            goodDatabase = Room.databaseBuilder(application,GoodDatabase.class,"db_goods")
                    .addMigrations()
                    .allowMainThreadQueries()
                    .build();
        }
        return goodDatabase;
    }
}
