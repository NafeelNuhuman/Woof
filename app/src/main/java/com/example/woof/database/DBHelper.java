package com.example.woof.database;

import android.app.Activity;
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

import static com.example.woof.database.StoriesMaster.stories.TABLE_NAME;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Woof.db";
    private ByteArrayOutputStream prodByteArrayOutputStream;
    private byte[] prodImageInByte;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    private Context context;
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Cart table
        String CREATE_TABLE_CART =
                "CREATE TABLE " + cartMaster.cart.TABLE_NAME + "(" +
                        cartMaster.cart.ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        cartMaster.cart.NAME + " TEXT, " +
                        cartMaster.cart.PRODUCT_QUANTITY + " INTEGER, " +
                        cartMaster.cart.IMAGE + " BLOB," +
                        cartMaster.cart.PER_UNIT + " DOUBLE, " +
                        cartMaster.cart.TOTAL + " DOUBLE, " +
                        cartMaster.cart.USER_ID + " INTEGER)";
        db.execSQL(CREATE_TABLE_CART);


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


        //review Table
        String CREATE_REVIEW_TABLE =
                "CREATE TABLE " + reviewsMaster.myReviews.TABLE_NAME + "(" +
                        reviewsMaster.myReviews.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        reviewsMaster.myReviews.COLUMN_DESC + " TEXT, " +
                        reviewsMaster.myReviews.COLUMN_RATING + " INTEGER, " +
                        reviewsMaster.myReviews.COLUMN_ACCESSORY_ID + " INTEGER, " +
                        reviewsMaster.myReviews.COLUMN_USER_ID + "INTEGER)";
        db.execSQL(CREATE_REVIEW_TABLE);


        //Stories table
        String CREATE_STORIES_TABLE=
                "CREATE TABLE "+ TABLE_NAME+"("+
                        StoriesMaster.stories.COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        StoriesMaster.stories.COLUMN_NAME+" TEXT,"+
                        StoriesMaster.stories.COLUMN_DESC+" TEXT,"+
                        StoriesMaster.stories.COLUMN_USERID+" INTEGER)";

        db.execSQL(CREATE_STORIES_TABLE);

        //Dog table
        String CREATE_DOGS_TABLE=
                "CREATE TABLE "+DogMaster.Dogs.TABLE_NAME+"("+
                        DogMaster.Dogs.COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        DogMaster.Dogs.COLUMN_NAME+" TEXT,"+
                        DogMaster.Dogs.COLUMN_AGE+" TEXT,"+
                        DogMaster.Dogs.COLUMN_SIZE+" TEXT,"+
                        DogMaster.Dogs.COLUMN_GENDER+" TEXT,"+
                        DogMaster.Dogs.COLUMN_BREED+" TEXT, "+
                        DogMaster.Dogs.COLUMN_VAC+" TEXT, "+
                        DogMaster.Dogs.COLUMN_IMAGE+ " BLOB," +
                        DogMaster.Dogs.COLUMN_USER_ID+" INTEGER)";

        db.execSQL(CREATE_DOGS_TABLE);

        //cart table

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

    //Stories
    //Insert Stories
    public boolean addStory(storyModel stm){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv  =new ContentValues();


        cv.put(StoriesMaster.stories.COLUMN_NAME,stm.getTitle());
        cv.put(StoriesMaster.stories.COLUMN_DESC,stm.getDescrip());
        cv.put(StoriesMaster.stories.COLUMN_USERID,stm.getUserId());


        long insert=db.insert(StoriesMaster.stories.TABLE_NAME,null,cv);
        return insert != -1;

    }
    //Retrieve stories
    public Cursor readAllStories(){
        String query="SELECT * FROM "+ StoriesMaster.stories.TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor =null;
        if(db !=null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }

    //Retrieve my stories
    public Cursor readAllMyStories(String ID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM " + StoriesMaster.stories.TABLE_NAME +" WHERE " +
                StoriesMaster.stories.COLUMN_USERID + " LIKE ? ", new String[]{ID});
        return cursor;
    }
    //Delete My Stories
    public void DeleteOneRowOfStories(String ID){
        SQLiteDatabase db =this.getWritableDatabase();
        long result= db.delete(StoriesMaster.stories.TABLE_NAME, StoriesMaster.stories.COLUMN_ID + "=?",new String[]{ID});
    }




    //Retrieve product details with product ID
    public Cursor readProductWithID(String ID){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor =null;
        cursor= db.rawQuery("SELECT * FROM " + productMaster.product.TABLE_NAME + " WHERE " + productMaster.product.COLUMN_ID + " LIKE ?",new String[]{ID});
        return cursor;
    }



    //checking if email is already registered
    public boolean checkmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + sellerMaster.seller.TABLE_NAME +
                " WHERE " + sellerMaster.seller.COLUMN_EMAIL + " LIKE ?", new String[]{email});

        Cursor cursor1 = db.rawQuery("SELECT * FROM " + petOwnerMaster.petOwner.TABLE_NAME +
                " WHERE " + petOwnerMaster.petOwner.COLUMN_EMAIL + " LIKE ?", new String[]{email});
        if (cursor.getCount() > 0 || cursor1.getCount()> 0)
            return false;
        else
            return true;
    }

    //retrieve dog details with dogID
    public Cursor readDogWithID(String ID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT * FROM " + DogMaster.Dogs.TABLE_NAME +" WHERE " + DogMaster.Dogs.COLUMN_ID +" LIKE ? ", new String[]{ID});
        return cursor;
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

    //get user ID
    public int getUserID(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + petOwnerMaster.petOwner.COLUMN_ID +
                " FROM " + petOwnerMaster.petOwner.TABLE_NAME + " WHERE " + petOwnerMaster.petOwner.COLUMN_EMAIL + " LIKE ? ", new String[]{email});
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    //get username using email
    public String getUserName(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + petOwnerMaster.petOwner.COLUMN_FNAME +
                " FROM " + petOwnerMaster.petOwner.TABLE_NAME + " WHERE " + petOwnerMaster.petOwner.COLUMN_EMAIL + " LIKE ? ", new String[]{email});
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    //get username using ID
    public String getUserNameUsingID(String userID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + petOwnerMaster.petOwner.COLUMN_FNAME +
                " FROM " + petOwnerMaster.petOwner.TABLE_NAME + " WHERE " + petOwnerMaster.petOwner.COLUMN_ID + " LIKE ? ", new String[]{userID});
        cursor.moveToFirst();
        return cursor.getString(0);
    }
    //get user ID using story ID
    public String getUserID(int storyID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + StoriesMaster.stories.COLUMN_USERID +
                " FROM " + StoriesMaster.stories.TABLE_NAME + " WHERE " + StoriesMaster.stories.COLUMN_ID + " LIKE ? ", new String[]{String.valueOf(storyID)});
       cursor.moveToFirst();
        return cursor.getString(0);
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
            return insert != -1;
    }


    //get list of products
    public ArrayList<productModel> getProductList(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<productModel> productModelList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + productMaster.product.TABLE_NAME, null);
        if(cursor.getCount() != 0){
            while (cursor.moveToNext()){
                int ID = cursor.getInt(0);
                String name = cursor.getString(1);
                String desc = cursor.getString(2);
                Double price = cursor.getDouble(3);
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

      //get total of all items in cart
    public double getCartTotal() {
        double totalCost = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT  " + cartMaster.cart.TOTAL + " FROM " + cartMaster.cart.TABLE_NAME, null);
        if (cursor.getCount() != 0 ){
            while (cursor.moveToNext()){
                double itemTotal = cursor.getDouble(0);
                totalCost += itemTotal;
            }
            return totalCost;
        }else {
            return -99;
        }
    }



    //retrieving cart
    public ArrayList<cartModel> getCart(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<cartModel> cartModelList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + cartMaster.cart.TABLE_NAME , null);
        if(cursor.getCount() != 0){
            while (cursor.moveToNext()){
                int ID = cursor.getInt(0);
                String name = cursor.getString(1);
                int qty = cursor.getInt(2);
                byte[] imageInBytes = cursor.getBlob(3);
                double cost = cursor.getDouble(4);
                double total = cursor.getDouble(5);
                int userID = cursor.getInt(6);
                Bitmap image = BitmapFactory.decodeByteArray(imageInBytes,0,imageInBytes.length);
                cartModelList.add(new cartModel(ID,name,qty,image,cost,total,userID));
            }
            return cartModelList;
        }else {
            return null;
        }
    }

    //insert dog
    public boolean addDog(DogModel dm){
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap imageBitmap = dm.getImg();
        prodByteArrayOutputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,prodByteArrayOutputStream);
        prodImageInByte = prodByteArrayOutputStream.toByteArray();

        ContentValues cnv = new ContentValues();
        cnv.put(DogMaster.Dogs.COLUMN_NAME,dm.getName());
        cnv.put(DogMaster.Dogs.COLUMN_AGE,dm.getAge());
        cnv.put(DogMaster.Dogs.COLUMN_SIZE,dm.getSize());
        cnv.put(DogMaster.Dogs.COLUMN_GENDER,dm.getGender());
        cnv.put(DogMaster.Dogs.COLUMN_BREED,dm.getBreed());
        cnv.put(DogMaster.Dogs.COLUMN_VAC,dm.getVacc());
        cnv.put(DogMaster.Dogs.COLUMN_IMAGE,prodImageInByte);
        cnv.put(DogMaster.Dogs.COLUMN_USER_ID,dm.getUserID());

        long insert = db.insert(DogMaster.Dogs.TABLE_NAME,null,cnv);
        return insert != -2;
    }


    //insert product to cart
    public boolean addProductToCart(cartModel cm){
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap imageBitmap = cm.getImage();
        prodByteArrayOutputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,prodByteArrayOutputStream);
        prodImageInByte = prodByteArrayOutputStream.toByteArray();

        ContentValues cv = new ContentValues();
        cv.put(cartMaster.cart.NAME,cm.getName());
        cv.put(cartMaster.cart.IMAGE,prodImageInByte);
        cv.put(cartMaster.cart.PRODUCT_QUANTITY,cm.getQuantity());
        cv.put(cartMaster.cart.PER_UNIT,cm.getCost());
        cv.put(cartMaster.cart.TOTAL,cm.getTotal());
        cv.put(cartMaster.cart.USER_ID,cm.getUserID());

        long insert = db.insert(cartMaster.cart.TABLE_NAME,null,cv);
        return insert != -1;
    }

    //delete accessory
     public void deleteAccessory(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(productMaster.product.TABLE_NAME,productMaster.product.COLUMN_ID + "LIKE ?",new String[]{id});
        if (result == -1){
            Toast.makeText(context, "Delete Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
        }

    }
    //delete item from cart
    public boolean deleteCartItem(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(cartMaster.cart.TABLE_NAME,cartMaster.cart.ITEM_ID + " LIKE ?",new String[]{id});
        return result != -1;

    }

    //retrieve dog
    public ArrayList<DogModel> getDogList(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DogModel> DogModelList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DogMaster.Dogs.TABLE_NAME, null);
        if(cursor.getCount() != 0){
            while (cursor.moveToNext()){
                int ID = cursor.getInt(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                String size = cursor.getString(3);
                String gender = cursor.getString(4);
                String breed = cursor.getString(5);
                String vacc = cursor.getString(6);
                byte[] img = cursor.getBlob(7);
                int UserID = cursor.getInt(8);

                Bitmap imgBitmap = BitmapFactory.decodeByteArray(img, 0,img.length);
                DogModelList.add(new DogModel(ID,name,age,size,gender,breed,vacc,imgBitmap,UserID));
            }
            return DogModelList;
       }
        else {
            return null;
        }
   }

   //delete dog
    public void deleteDog(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long Result = db.delete(DogMaster.Dogs.TABLE_NAME, DogMaster.Dogs.COLUMN_ID + " LIKE ? ", new String[]{id});
        if (Result == -1) {
            Toast.makeText(context, "Delete failed!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Data deleted Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
