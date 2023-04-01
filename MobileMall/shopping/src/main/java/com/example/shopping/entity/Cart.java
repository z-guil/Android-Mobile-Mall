package com.example.shopping.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_cart")
public class Cart {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String description;
    public double price;
    public int picPath;

    public Cart(String name, String description, double price, int picPath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.picPath = picPath;
    }

    public int getPicPath() {
        return picPath;
    }

    public void setPicPath(int picPath) {
        this.picPath = picPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", picPath=" + picPath +
                '}';
    }
}
