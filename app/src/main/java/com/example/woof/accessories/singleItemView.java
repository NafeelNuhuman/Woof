package com.example.woof.accessories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.woof.R;

public class singleItemView extends AppCompatActivity {

    private TextView name,description,price;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_view);

        name = findViewById(R.id.singleItemprodName);
        description = findViewById(R.id.singleItemProdDesc);
        price = findViewById(R.id.singleItemProdPrice);
        image = findViewById(R.id.singleItemProdImage);


        Intent intent = getIntent();
        int prodID = intent.getIntExtra("prodID",0);
        String id = String.valueOf(prodID);
        String prodName = intent.getStringExtra("prodName");
        String prodDesc = intent.getStringExtra("prodDesc");
        float prodPrice = intent.getFloatExtra("prodPrice",0);
        byte[] prodImageInBytes = intent.getByteArrayExtra("prodImage");
        Bitmap prodImage = BitmapFactory.decodeByteArray(prodImageInBytes,0,prodImageInBytes.length);



    }
}