package com.example.woof.database;

public class productModel {
    private int ID;
    private String name;
    private String desc;
    private float price;
    private int sellerID;

    public productModel(int ID, String name, String desc, float price, int sellerID) {
        this.ID = ID;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.sellerID = sellerID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }
}
