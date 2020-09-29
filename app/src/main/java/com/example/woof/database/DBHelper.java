package com.example.woof.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Woof.db";
    private ByteArrayOutputStream prodByteArrayOutputStream;
    private byte[] prodImageInByte;

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

        //product table
        String CREATE_PRODUCT_TABLE =
                "CREATE TABLE " + productMaster.product.TABLE_NAME + "(" +
                        productMaster.product.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        productMaster.product.COLUMN_NAME + " TEXT, " +
                        productMaster.product.COLUMN_DESC + " TEXT, " +
                        productMaster.product.COLUMN_PRICE + " REAL, " +
                        productMaster.product.COLUMN_IMAGE + " BLOB, " +
                        productMaster.product.COLUMN_SELLER + " INTEGER)";

        db.execSQL(CREATE_PRODUCT_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + petOwnerMaster.petOwner.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + sellerMaster.seller.TABLE_NAME);
        db.execSQL( "DROP TABLE IF EXISTS " + productMaster.product.TABLE_NAME);

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


    //Register seller
    public boolean addSeller(sellerModel sm) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(sellerMaster.seller.COLUMN_NAME, sm.getName());
        cv.put(sellerMaster.seller.COLUMN_EMAIL, sm.getEmail());
        cv.put(sellerMaster.seller.COLUMN_CONTACT, sm.getContact());
        cv.put(sellerMaster.seller.COLUMN_ADDRESS, sm.getAddress());
        cv.put(sellerMaster.seller.COLUMN_PWD, sm.getPwd());

        long insert = db.insert(sellerMaster.seller.TABLE_NAME, null, cv);

        return insert != -1;
    }


    //checking if email exists in seller table
    public boolean checkmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + sellerMaster.seller.TABLE_NAME +
                " WHERE " + sellerMaster.seller.COLUMN_EMAIL + " LIKE ?", new String[]{email});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }

    //checking if email exists in pet owner table
    public boolean checkpetownermail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + petOwnerMaster.petOwner.TABLE_NAME +
                " WHERE " + petOwnerMaster.petOwner.COLUMN_EMAIL + " LIKE ?", new String[]{email});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }

    //check email and password
    public boolean checkEmailPwd(String email, String pwd, String table) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (table == "pet owner") {
            cursor = db.rawQuery("SELECT * FROM " + petOwnerMaster.petOwner.TABLE_NAME +
                    " WHERE " + petOwnerMaster.petOwner.COLUMN_EMAIL + "=? AND " + petOwnerMaster.petOwner.COLUMN_PWD + "=?", new String[]{email, pwd});
        } else if (table == "seller") {
            cursor = db.rawQuery("SELECT * FROM " + sellerMaster.seller.TABLE_NAME +
                    " WHERE " + sellerMaster.seller.COLUMN_EMAIL + "=? AND " + sellerMaster.seller.COLUMN_PWD + "=?", new String[]{email, pwd});
        }

        assert cursor != null;
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    //get seller name
    public String getSellerName(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + sellerMaster.seller.COLUMN_NAME +
                " FROM " + sellerMaster.seller.TABLE_NAME + " WHERE " + sellerMaster.seller.COLUMN_EMAIL + "=?", new String[]{email});
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    //get seller ID
    public int getSellerID(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + sellerMaster.seller.COLUMN_ID +
                " FROM " + sellerMaster.seller.TABLE_NAME + " WHERE " + sellerMaster.seller.COLUMN_EMAIL + "=?", new String[]{email});
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    //insert product
    public boolean addProduct(productModel pm){
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap imageBitmap = pm.getImage();
        prodByteArrayOutputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,prodByteArrayOutputStream);
        prodImageInByte = prodByteArrayOutputStream.toByteArray();

        ContentValues cv = new ContentValues();
        cv.put(productMaster.product.COLUMN_NAME,pm.getName());
        cv.put(productMaster.product.COLUMN_DESC,pm.getDesc());
        cv.put(productMaster.product.COLUMN_PRICE,pm.getPrice());
        cv.put(productMaster.product.COLUMN_IMAGE,prodImageInByte);
        cv.put(productMaster.product.COLUMN_SELLER,pm.getSellerID());

        long insert = db.insert(productMaster.product.TABLE_NAME,null,cv);
        return insert != -2;
    }

    //get all products
    public Cursor getProducts(){
        String query = "SELECT * FROM " + productMaster.product.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if (db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    //get list of products
    public ArrayList<productModel> getProductList(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<productModel> productModelList = new ArrayList<>();

        Cursor cursor = db.rawQuery(" SELECT * FROM " + productMaster.product.TABLE_NAME, null);
        if(cursor.getCount() != 0){
            while (cursor.moveToNext()){
                int ID = cursor.getInt(0);
                String name = cursor.getString(1);
                String desc = cursor.getString(2);
                float price = cursor.getFloat(3);
                byte[] image = cursor.getBlob(4);
                int sellerID = cursor.getInt(5);

                Bitmap imageBitmap = BitmapFactory.decodeByteArray(image,0,image.length);
                productModelList.add(new productModel(ID,name,desc,price,imageBitmap,sellerID));
            }
            return productModelList;
        }
        else{
            return  null;
        }
    }




}
