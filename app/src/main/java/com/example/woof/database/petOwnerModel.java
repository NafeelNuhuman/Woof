package com.example.woof.database;

public class petOwnerModel {
    private int ID;
    private String fName;
    private String lName;
    private String email;
    private String dateOfBirth;
    private int contact;
    private String pwd;

    public petOwnerModel(Integer ID, String fName, String lName, String email, String dateOfBirth, Integer contact, String pwd) {
        this.ID = ID;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.contact = contact;
        this.pwd = pwd;
    }

    public petOwnerModel() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
