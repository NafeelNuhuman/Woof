package com.example.woof.database;

import android.provider.BaseColumns;

public class cartMaster {
    public cartMaster() {
    }

    public static class cart implements BaseColumns{
        public static final String TABLE_NAME = "Cart";
        public static final String COLUMN_CART_ITEM_ID = "ItemID";
        public static final String COLUMN_CART_ITEM_NAME = "Name";
        public static final String COLUMN_CART_ITEM_QUANTITY = "Quantity";
        public static final String COLUMN_CART_ITEM_IMAGE = "Image";
        public static final String COLUMN_CART_ITEM_COST = "Cost";
        public static final String COLUMN_CART_ITEM_TOTAL = "Total";
        public static final String COLUMN_CART_USER_ID = "UserID";


    }
}
