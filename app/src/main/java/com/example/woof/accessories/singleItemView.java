package com.example.woof.accessories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.woof.R;
import com.example.woof.database.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class singleItemView extends AppCompatActivity {

    DBHelper db;
    private Button addToCartBtn;
    private ElegantNumberButton numberButton;
    private TextView name, description, price;
    private ImageView image;
    private String productID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_view);

        db = new DBHelper(this);
        productID = getIntent().getStringExtra("productID");

        name = findViewById(R.id.singleItemprodName);
        description = findViewById(R.id.singleItemProdDesc);
        price = findViewById(R.id.singleItemProdPrice);
        image = findViewById(R.id.singleItemProdImage);
        addToCartBtn = (Button) findViewById(R.id.addToCart);

        Intent intent = getIntent();
        int prodID = intent.getIntExtra("prodID", 0);
        String id = String.valueOf(prodID);
        String prodName = intent.getStringExtra("prodName");
        String prodDesc = intent.getStringExtra("prodDesc");
        float prodPrice = intent.getFloatExtra("prodPrice", 0);
        byte[] prodImageInBytes = intent.getByteArrayExtra("prodImage");
        Bitmap prodImage = BitmapFactory.decodeByteArray(prodImageInBytes, 0, prodImageInBytes.length);


        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });
    }

    private void getProductDetails(String productID) {
        Cursor cursor = db.alldata();
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();
        }
    }


}