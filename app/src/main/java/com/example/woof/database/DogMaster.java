package com.example.woof.database;

import android.provider.BaseColumns;

public class DogMaster {


    public DogMaster() {
    }

    public static class Dogs implements BaseColumns{
            public static final String TABLE_NAME = "Dogs";
            public static final String COLUMN_ID = "Dog_ID";
            public static final String COLUMN_NAME = "Name";
            public static final String COLUMN_AGE = "AGE";
            public static final String COLUMN_SIZE = "Size";
            public static final String COLUMN_GENDER = "Gender";
            public static final String COLUMN_BREED = "BREED";
            public static final String COLUMN_VAC = "Vaccinated";
            public static final String COLUMN_IMAGE = "Image";
            public static final String COLUMN_USER_ID ="User";


    }

}