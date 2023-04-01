package com.example.shopping.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.shopping.entity.Cart;
import com.example.shopping.entity.Good;

import java.util.List;

@Dao
public interface GoodDao {
    //增
    @Insert
    void insert(Good... good);
    //删
    @Delete
    void delete(Good... good);
    @Query("delete from tb_good")
    void deleteAll();

    //改
    @Update
    int update(Good... good);

    //查
    @Query("select * from tb_good")
    List<Good> queryAll();
    @Query("select * from tb_good where name like '%'|| :name ||'%' order by id desc limit 1")
    Good queryByName(String name);
    @Query("select * from tb_good where id = :id order by id desc limit 1")
    Good queryById(int id);

    //购物车 增
    @Insert(entity = Cart.class)
    void insertIntoCart(Cart cart);
    //购物车 查
    @Query("select * from tb_cart")
    List<Cart> queryAllFromCart();
    //购物车 删
    @Delete
    void deleteCart(Cart cart);
    @Query("delete from tb_cart")
    void deleteAllFromCart();


}
