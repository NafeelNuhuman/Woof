package com.example.woof.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addPetOwner(petOwnerModel petOwnerModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(petOwnerMaster.petOwner.COLUMN_FNAME,petOwnerModel.getfName());
        cv.put(petOwnerMaster.petOwner.COLUMN_LNAME,petOwnerModel.getlName());
        cv.put(petOwnerMaster.petOwner.COLUMN_EMAIL,petOwnerModel.getEmail());
        cv.put(petOwnerMaster.petOwner.COLUMN_CONTACT,petOwnerModel.getContact());
        cv.put(petOwnerMaster.petOwner.COLUMN_DOB,petOwnerModel.getDateOfBirth());
        cv.put(petOwnerMaster.petOwner.COLUMN_PWD,petOwnerModel.getPwd());

        long insert = db.insert(petOwnerMaster.petOwner.TABLE_NAME, null, cv);

        return insert != -1;
    }

}
