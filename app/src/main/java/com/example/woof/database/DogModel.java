package com.example.woof.database;

import android.graphics.Bitmap;

public class DogModel {
    private int ID;
    private String name;
    private int age;
    private String size;
    private String gender;
    private String breed;
    private String vacc;
    private Bitmap img;
    private int UserID;

    public DogModel(int ID, String name, int age, String size, String gender, String breed, String vacc, Bitmap img, int userID) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.size = size;
        this.gender = gender;
        this.breed = breed;
        this.vacc = vacc;
        this.img = img;
        UserID = userID;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getVacc() {
        return vacc;
    }

    public void setVacc(String vacc) {
        this.vacc = vacc;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }
}
