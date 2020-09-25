package com.example.woof.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Woof.db";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Pet Owner table
        String CREATE_TABLE_PET_OWNER =
                "CREATE TABLE " + petOwnerMaster.petOwner.TABLE_NAME + "(" +
                        petOwnerMaster.petOwner.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        petOwnerMaster.petOwner.COLUMN_FNAME + " TEXT, " +
                        petOwnerMaster.petOwner.COLUMN_LNAME + " TEXT, " +
                        petOwnerMaster.petOwner.COLUMN_EMAIL + " TEXT, " +
                        petOwnerMaster.petOwner.COLUMN_CONTACT + " INTEGER, " +
                        petOwnerMaster.petOwner.COLUMN_DOB + " TEXT, " +
                        petOwnerMaster.petOwner.COLUMN_PWD + " TEXT)";

        db.execSQL(CREATE_TABLE_PET_OWNER);

        //Seller table
        String CREATE_SELLER_TABLE =
                "CREATE TABLE " + sellerMaster.seller.TABLE_NAME + "(" +
                        sellerMaster.seller.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        sellerMaster.seller.COLUMN_NAME + " TEXT, " +
                        sellerMaster.seller.COLUMN_EMAIL + " TEXT, " +
                        sellerMaster.seller.COLUMN_CONTACT + " INTEGER, " +
                        sellerMaster.seller.COLUMN_ADDRESS + " TEXT, " +
                        sellerMaster.seller.COLUMN_PWD + " TEXT)";

        db.execSQL(CREATE_SELLER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + petOwnerMaster.petOwner.TABLE_NAME);

    }

    //Register pet owner
    public boolean addPetOwner(petOwnerModel petOwnerModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(petOwnerMaster.petOwner.COLUMN_FNAME, petOwnerModel.getfName());
        cv.put(petOwnerMaster.petOwner.COLUMN_LNAME, petOwnerModel.getlName());
        cv.put(petOwnerMaster.petOwner.COLUMN_EMAIL, petOwnerModel.getEmail());
        cv.put(petOwnerMaster.petOwner.COLUMN_CONTACT, petOwnerModel.getContact());
        cv.put(petOwnerMaster.petOwner.COLUMN_DOB, petOwnerModel.getDateOfBirth());
        cv.put(petOwnerMaster.petOwner.COLUMN_PWD, petOwnerModel.getPwd());

        long insert = db.insert(petOwnerMaster.petOwner.TABLE_NAME, null, cv);

        return insert != -1;
    }

    //getting emails and passwords for pet owner sign in
    public List readEmailorPwd(String req) {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                petOwnerMaster.petOwner.COLUMN_EMAIL,
                petOwnerMaster.petOwner.COLUMN_PWD
        };

        Cursor cursor = db.query(
                petOwnerMaster.petOwner.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List emails = new ArrayList();
        List passwords = new ArrayList();

        while (cursor.moveToNext()) {
            String email = cursor.getString(cursor.getColumnIndexOrThrow(petOwnerMaster.petOwner.COLUMN_EMAIL));
            String pwd = cursor.getString(cursor.getColumnIndexOrThrow(petOwnerMaster.petOwner.COLUMN_PWD));
            emails.add(email);
            passwords.add(pwd);
        }
        cursor.close();
        if (req == "email")
            return emails;
        else if (req == "password")
            return passwords;
        else
            return null;
    }

    //Register seller
    public boolean addSeller(sellerModel sm){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(sellerMaster.seller.COLUMN_NAME,sm.getName());
        cv.put(sellerMaster.seller.COLUMN_EMAIL,sm.getEmail());
        cv.put(sellerMaster.seller.COLUMN_CONTACT,sm.getContact());
        cv.put(sellerMaster.seller.COLUMN_ADDRESS,sm.getAddress());
        cv.put(sellerMaster.seller.COLUMN_PWD,sm.getPwd());

        long insert = db.insert(sellerMaster.seller.TABLE_NAME,null,cv);

        return insert != -1;
    }


    //checking if email exists in seller table
    public boolean checkmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + sellerMaster.seller.TABLE_NAME +
                " WHERE " + sellerMaster.seller.COLUMN_EMAIL + " LIKE ?", new String[]{email});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }

    //checking if email exists in pet owner table
    public boolean checkpetownermail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + petOwnerMaster.petOwner.TABLE_NAME +
                " WHERE " + petOwnerMaster.petOwner.COLUMN_EMAIL + " LIKE ?", new String[]{email});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }

    //check email and password
    public boolean checkEmailPwd(String email, String pwd,String table){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (table == "pet owner") {
            cursor = db.rawQuery("SELECT * FROM " + petOwnerMaster.petOwner.TABLE_NAME +
                    " WHERE " + petOwnerMaster.petOwner.COLUMN_EMAIL + "=? AND " + petOwnerMaster.petOwner.COLUMN_PWD + "=?", new String[]{email, pwd});
        }else if (table == "seller"){
            cursor = db.rawQuery("SELECT * FROM " + sellerMaster.seller.TABLE_NAME +
                    " WHERE " + sellerMaster.seller.COLUMN_EMAIL + "=? AND " + sellerMaster.seller.COLUMN_PWD + "=?", new String[]{email, pwd});
        }
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

}
