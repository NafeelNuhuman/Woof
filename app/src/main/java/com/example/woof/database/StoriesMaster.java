package com.example.woof.database;

import android.provider.BaseColumns;

public class StoriesMaster {

    public StoriesMaster() {
    }
    public static class stories implements BaseColumns{
        public static final String  TABLE_NAME="Stories";
        public static final String  COLUMN_ID="Story_ID";
        public static final String COLUMN_NAME="Title";
        public static final String COLUMN_DESC="Description";
        public static final String COLUMN_USERID= "UserID";
    }

}
