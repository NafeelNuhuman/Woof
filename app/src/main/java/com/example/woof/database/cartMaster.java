package com.example.woof.database;

import android.provider.BaseColumns;

public class cartMaster {
    public cartMaster() {
    }
    public static class cart implements BaseColumns {
        public static final String TABLE_NAME = "Cart";
        public static final String ITEM_ID ="itemID";
        public static final String NAME ="Name";
        public static final String IMAGE ="Image";
        public static final String PRODUCT_QUANTITY = "ProductQuantity";
        public static final String PER_UNIT = "PerUnit";
        public static final String TOTAL = "Total";
        public static final String USER_ID = "UserID" ;

    }

}
