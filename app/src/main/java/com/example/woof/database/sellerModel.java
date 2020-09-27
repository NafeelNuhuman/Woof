package com.example.woof.database;

public class sellerModel {
    private int ID;
    private String name;
    private String email;
    private int contact;
    private String address;
    private String pwd;

    public sellerModel(int ID, String name, String email, int contact, String address, String pwd) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.pwd = pwd;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
