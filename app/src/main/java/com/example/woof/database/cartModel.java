package com.example.woof.database;

import android.graphics.Bitmap;

public class cartModel {
    private int itemID;
    private String name;
    private int quantity;
    private Bitmap image;
    private double cost;
    private double total;
    private int userID;

    public cartModel(int itemID,String name,int quantity,Bitmap image,double cost, double total, int userID){
        itemID = itemID;
        name = name;
        this.quantity = quantity;
        image = image;
        cost = cost;
        this.total = total;
        userID = userID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
